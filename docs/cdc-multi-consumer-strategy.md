# 여러 Consumer가 동일 Producer API를 요구할 때의 Contract 관리 전략

## 시나리오

여러 도메인 서비스(Consumer)가 하나의 도메인 서비스(Producer)의 동일한 Internal API를 요구하는 상황:

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│ Order Svc   │     │ Product Svc │     │ Payment Svc │
│ (Consumer)  │     │ (Consumer)  │     │ (Consumer)  │
└──────┬──────┘     └──────┬──────┘     └──────┬──────┘
       │                   │                   │
       │  GET /stores/{id} │  GET /stores/{id} │  GET /stores/{id}
       │  needs: name,     │  needs: name,     │  needs: name,
       │         address   │         category  │         bankAccount
       └───────────────────┼───────────────────┘
                           ▼
                   ┌───────────────┐
                   │  Store Svc    │
                   │  (Producer)   │
                   └───────────────┘
```

- **동일한 데이터**: 모든 Consumer가 `name` 필요
- **일부 겹침**: Order와 Product가 `name` 공유
- **완전히 다름**: `address`, `category`, `bankAccount`는 각각 다른 Consumer만 필요

---

## 전략 1: Consumer별 독립 Contract 유지

각 Consumer마다 별도의 Contract 파일을 관리합니다.

### 디렉토리 구조

```
store-service/
└── contracts/
    ├── order-service/
    │   └── getStore.groovy      # name, address만 검증
    ├── product-service/
    │   └── getStore.groovy      # name, category만 검증
    └── payment-service/
        └── getStore.groovy      # name, bankAccount만 검증
```

### 장점

- 각 Consumer의 요구사항이 명확히 분리됨
- Consumer별로 독립적인 배포/변경 가능
- 특정 Consumer 요구사항 변경이 다른 Consumer에 영향 없음
- **Postel's Law** 적용 용이 (관대하게 받고, 엄격하게 보냄)

### 단점

- Contract 파일 수가 Consumer 수에 비례해 증가
- 중복 코드 발생 (같은 필드를 여러 Contract에서 정의)
- Producer 입장에서 전체 API 스펙 파악이 어려움

---

## 전략 2: 공통 Contract + Consumer별 확장

공통 필드는 base contract로, Consumer별 추가 요구사항은 확장으로 관리합니다.

### 디렉토리 구조

```
store-service/
└── contracts/
    ├── common/
    │   └── getStore-base.groovy     # 공통: name
    ├── order-service/
    │   └── getStore.groovy          # base + address
    ├── product-service/
    │   └── getStore.groovy          # base + category
    └── payment-service/
        └── getStore.groovy          # base + bankAccount
```

### 코드 예시

```groovy
// common/getStore-base.groovy
Contract.make {
    response {
        body([
            id: $(anyUuid()),
            name: $(anyNonBlankString())
        ])
    }
}

// order-service/getStore.groovy (base 상속)
Contract.make {
    extends("common/getStore-base")
    response {
        body([
            address: $(anyNonBlankString())
        ])
    }
}
```

### 장점

- 공통 필드의 중복 제거
- 공통 필드 변경 시 한 곳만 수정
- 계층 구조로 관리 용이

### 단점

- Contract 간 의존성 발생
- 상속 구조가 복잡해질 수 있음
- 도구에 따라 상속 지원이 제한적 (Pact는 미지원)

---

## 전략 3: Union Contract (합집합 전략)

모든 Consumer의 요구사항을 합쳐 하나의 통합 Contract로 관리합니다.

### 디렉토리 구조

```
store-service/
└── contracts/
    └── getStore.groovy    # name, address, category, bankAccount 모두 포함
```

### 코드 예시

```groovy
Contract.make {
    request {
        method GET()
        url "/internal/v1/stores/123"
    }
    response {
        body([
            id: $(anyUuid()),
            name: $(anyNonBlankString()),
            address: $(anyNonBlankString()),
            category: $(anyNonBlankString()),
            bankAccount: $(anyNonBlankString())
        ])
    }
}
```

### 장점

- Contract 관리가 단순함
- Producer 입장에서 전체 API 스펙이 명확함
- Contract 파일 수 최소화

### 단점

- **Consumer별 요구사항 추적 불가** (누가 어떤 필드를 필요로 하는지 모름)
- 불필요한 필드도 항상 포함되어 응답 크기 증가
- 한 Consumer의 요구사항 변경이 다른 Consumer 테스트에 영향
- CDC의 핵심 가치(Consumer 중심) 훼손

---

## 전략 4: Pact의 Provider States 활용

Consumer별로 다른 상태(state)를 정의하여 같은 API에 대해 다른 응답을 검증합니다.

### 코드 예시

```javascript
// Order Service의 Pact
pact
  .given('store exists for order service')
  .uponReceiving('a request for store details')
  .withRequest({ method: 'GET', path: '/stores/123' })
  .willRespondWith({
    body: { id: '123', name: 'Store A', address: '...' }
  })

// Product Service의 Pact
pact
  .given('store exists for product service')
  .uponReceiving('a request for store details')
  .withRequest({ method: 'GET', path: '/stores/123' })
  .willRespondWith({
    body: { id: '123', name: 'Store A', category: '...' }
  })
```

### 장점

- 동일 API에 대해 Consumer별 기대 응답 정의 가능
- Pact Broker를 통한 중앙 집중식 관리
- Consumer별 요구사항 명확히 추적

### 단점

- Provider States 관리 복잡성 증가
- Producer가 모든 state에 대한 테스트 fixture 준비 필요

---

## 전략 비교표

| 전략 | 관리 복잡도 | 중복 코드 | Consumer 추적 | 독립 배포 | 권장 상황 |
|------|------------|----------|--------------|----------|----------|
| **1. 독립 Contract** | 중간 | 높음 | 명확 | 완벽 | Consumer 수가 적을 때, 요구사항이 많이 다를 때 |
| **2. 공통 + 확장** | 높음 | 낮음 | 명확 | 부분적 | 공통 필드가 많을 때, Spring Cloud Contract 사용 시 |
| **3. Union Contract** | 낮음 | 없음 | 불가 | 불가 | **비권장** (CDC 원칙 위배) |
| **4. Provider States** | 중간 | 중간 | 명확 | 완벽 | Pact 사용 시, 동적 응답이 필요할 때 |

---

## 실무 권장 사항

### 1. 기본 원칙: 전략 1 (독립 Contract) 채택

```
producer-service/
└── src/test/resources/contracts/
    ├── consumer-a/
    │   └── api-name.groovy
    ├── consumer-b/
    │   └── api-name.groovy
    └── consumer-c/
        └── api-name.groovy
```

### 2. 필드 중복이 많으면: 공통 Response DTO + Matcher 활용

```groovy
// 공통 Matcher 정의
def storeBaseMatcher = [
    id: $(anyUuid()),
    name: $(anyNonBlankString())
]

// Consumer A Contract
Contract.make {
    response {
        body(storeBaseMatcher + [address: $(anyNonBlankString())])
    }
}
```

### 3. Contract 변경 관리 프로세스

```
1. Consumer가 새 Contract PR 생성
2. Producer 팀 리뷰
3. Producer CI에서 Contract 검증 자동 실행
4. 검증 실패 시 → Producer가 API 수정 또는 Consumer와 협의
5. 검증 성공 시 → Stub 자동 배포
```

### 4. Backward Compatibility 체크

새 Contract가 기존 Consumer에 영향을 주지 않는지 CI에서 자동 검증:

```yaml
# CI Pipeline
- name: Verify all consumer contracts
  run: ./gradlew contractTest
```

---

## 결론

- **기본적으로 전략 1 (Consumer별 독립 Contract)을 사용**
- 중복이 심하면 공통 Matcher나 Helper를 추출하여 재사용
- Union Contract(전략 3)는 CDC의 핵심 가치를 훼손하므로 피할 것
- Pact 사용 시 Provider States를 활용하면 더 유연한 관리 가능
