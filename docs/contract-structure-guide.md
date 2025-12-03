# Contract 패키지 구조 가이드

## 개요

이 문서는 Consumer-Driven Contract (CDC) 패턴에 따른 Contract 파일 관리 구조를 정의합니다.
**전략: Consumer별 독립 Contract**를 채택합니다.

**Contract 형식: YAML** - 다양한 언어(Kotlin, Python 등) 기반 서비스 간 호환성을 위해 YAML을 사용합니다.

---

## 디렉토리 구조

```
{service-name}/
└── src/test/resources/contracts/
    ├── internal/                           # Internal API Contracts
    │   ├── {consumer-service-1}/           # Consumer별 디렉토리
    │   │   ├── {api-name}.yml
    │   │   └── {api-name}.yml
    │   ├── {consumer-service-2}/
    │   │   └── {api-name}.yml
    │   └── {consumer-service-n}/
    │       └── {api-name}.yml
    │
    └── external/                           # External API Contracts (Optional)
        └── {consumer-app}/
            └── {api-name}.yml
```

### 예시: Product Service

```
product-service/
└── src/test/resources/contracts.internal/
    └── order-service/
        ├── getProductById_success.yml
        ├── getProductById_notFound.yml
        ├── getProductsByIds_success.yml
        └── getProductsByIds_empty.yml
```

---

## 네이밍 컨벤션

### 디렉토리명
- **Consumer 서비스명**: kebab-case 사용 (예: `order-service`, `product-service`)
- **internal/external 구분**: Internal API와 External API를 최상위에서 분리

### 파일명
- **Contract 파일**: `{동사}{리소스}_{시나리오}.yml` 형식
- 예: `getProductById_success.yml`, `getProductsByIds_empty.yml`
- camelCase + underscore로 시나리오 구분

---

## Contract 작성 규칙

### 1. Consumer가 필요한 필드만 정의

```yaml
# 좋은 예: Order Service가 필요한 필드만
name: order_service_get_product_by_id_success
description: |
  Consumer: order-service
  Purpose: 주문 시 상품 정보 조회

request:
  method: GET
  url: /internal/v1/products/550e8400-e29b-41d4-a716-446655440000

response:
  status: 200
  body:
    id: "550e8400-e29b-41d4-a716-446655440000"
    storeId: "660e8400-e29b-41d4-a716-446655440001"
    name: "아메리카노"
    storeName: "스타벅스 강남점"
    price: 4500.00
```

```yaml
# 나쁜 예: 모든 필드를 포함 (Union Contract)
response:
  body:
    id: "..."
    storeId: "..."
    name: "..."
    storeName: "..."
    price: 4500.00
    description: "..."      # Order Service는 불필요
    stockQuantity: 100      # Order Service는 불필요
    categoryId: "..."       # Order Service는 불필요
```

### 2. Internal API 경로 표준
```
/internal/v1/{resource}/{id}
/internal/v1/{resource}
```

### 3. Matcher 사용

```yaml
response:
  body:
    id: "550e8400-e29b-41d4-a716-446655440000"
    name: "아메리카노"
    price: 4500.00
  matchers:
    body:
      - path: $.id
        type: by_regex
        value: "[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}"
      - path: $.name
        type: by_type
      - path: $.price
        type: by_regex
        value: "[0-9]+\\.?[0-9]*"
```

---

## 역할 및 책임

| 역할 | 책임 |
|------|------|
| **Consumer** | Contract 작성, PR 생성 |
| **Producer** | Contract 검증, Stub 생성/배포 |

### 워크플로우
```
1. Consumer가 contracts.internal/{consumer-name}/ 에 Contract 작성
2. Producer repo에 PR 생성
3. Producer CI에서 Contract 검증 (./gradlew contractTest)
4. 검증 통과 시 Merge → Stub 자동 배포
5. Consumer가 Stub을 사용하여 통합 테스트
```

---

## Contract 파일 템플릿

```yaml
name: {consumer_name}_{api_name}_{scenario}
description: |
  Consumer: {consumer-service-name}
  Purpose: {API 호출 목적}

request:
  method: {HTTP_METHOD}
  url: /internal/v1/{resource}/{id}
  headers:
    Content-Type: application/json
  # queryParameters: (optional)
  #   key: value

response:
  status: {STATUS_CODE}
  headers:
    Content-Type: application/json
  body:
    # Consumer가 필요한 필드만 정의
    field1: "value1"
    field2: 123
  matchers:
    body:
      - path: $.field1
        type: by_regex
        value: ".*"
      - path: $.field2
        type: by_type
```

---

## 에러 응답 Contract

```yaml
name: order_service_get_product_by_id_not_found
description: |
  Consumer: order-service
  Purpose: 존재하지 않는 상품 조회 시 404 응답

request:
  method: GET
  url: /internal/v1/products/99999999-9999-9999-9999-999999999999
  headers:
    Content-Type: application/json

response:
  status: 404
  headers:
    Content-Type: application/json
  body:
    error: "PRODUCT_NOT_FOUND"
    message: "Product not found with id: 99999999-9999-9999-9999-999999999999"
  matchers:
    body:
      - path: $.message
        type: by_regex
        value: "Product not found.*"
```

---

## 주의사항

1. **Union Contract 금지**: 모든 필드를 포함하는 통합 Contract는 생성하지 않음
2. **Consumer 중심**: Contract는 항상 Consumer가 필요로 하는 것만 정의
3. **독립성 유지**: 각 Consumer의 Contract는 다른 Consumer에 영향을 주지 않음
4. **버전 관리**: API 버전이 변경되면 새 Contract 작성 (기존 Contract 유지)
5. **YAML 사용**: 다양한 언어 기반 서비스 간 호환성을 위해 YAML 형식 사용
