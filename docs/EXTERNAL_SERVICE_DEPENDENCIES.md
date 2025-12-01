# Product Service 외부 서비스 의존성

## 개요

Product Service가 다른 도메인 서비스를 호출하는 부분을 정리한 문서입니다.

서비스 간 호출은 **Internal API**를 통해 이루어집니다. Internal API는 외부 공개 API와 분리된 서비스 간 통신 전용 API입니다.

> **인증**: Internal API 호출 시 별도의 인증 과정이 필요하지 않습니다. API Gateway (Istio)를 통해 이미 인증을 거친 후이므로, 서비스 간 호출에는 인증 헤더 없이 요청합니다.

---

## 1. Store Service 의존성

### 호출 목적

상품 등록 시 **스토어 소유자 검증** 및 **스토어 이름 조회**를 위해 Store Service를 호출합니다.

### 사용 위치

| 계층 | 파일 | 용도 |
|------|------|------|
| Domain Port | `domain/port/LoadStorePort.kt` | 스토어 조회 인터페이스 정의 |
| Application | `application/service/RegisterProductService.kt` | 상품 등록 시 스토어 검증 |
| Adapter | `adapter/outbound/client/StoreClientAdapter.kt` | Port 구현체 |
| Adapter | `adapter/outbound/client/StoreServiceFeignClient.kt` | Feign Client |

### 호출 API (Internal API)

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/internal/v1/stores/{storeId}` | 스토어 ID로 조회 |
| GET | `/internal/v1/stores/owner/{ownerId}` | 소유자 ID로 조회 |

### 응답 DTO

```
StoreInternalDto
├── storeId: String
├── ownerUserId: String
├── name: String
├── description: String?
├── status: String (REGISTERED, SUSPENDED, HIDDEN, DELETED)
├── averageRating: Double?
├── reviewCount: Int?
├── launchedAt: LocalDateTime?
├── createdAt: LocalDateTime
└── updatedAt: LocalDateTime
```

### 환경별 설정

| 환경 | 설정값 |
|------|--------|
| 로컬 (default) | `http://localhost:8082` |
| k3d (dev) | `http://store-service:8082` |
| 운영 (prod) | `${STORE_SERVICE_URL}` |
| 테스트 (test) | `MockStoreServiceClient` 사용 |

### 비즈니스 로직

1. 상품 등록 요청 수신
2. `LoadStorePort.loadByIdAndOwnerId(storeId, ownerId)` 호출
3. 스토어 존재 여부 확인
4. 소유자 일치 여부 확인 (ownerUserId == requestUserId)
5. 불일치 시 `StoreException.StoreRegistrationRequired` 발생
6. 일치 시 `StoreInfo(id, name)` 반환 → 상품에 storeName 비정규화 저장

### 에러 처리

| 상황 | 처리 |
|------|------|
| 스토어 미존재 (404) | `null` 반환 → 예외 발생 |
| 소유자 불일치 | `null` 반환 → 예외 발생 |
| 통신 오류 | 로그 기록 후 `null` 반환 |

### Consumer Contract Test

Store Service의 Internal API 계약을 검증하는 Consumer Contract Test가 구현되어 있습니다.

- **Contract 파일**: `src/test/resources/contracts/store-service/`
  - `stores/get/should_get_store_by_id.yml` - 스토어 ID로 조회 성공 케이스
  - `stores/get/should_return_404_when_store_not_found.yml` - 스토어 미존재 시 404 케이스
  - `stores/owner/should_get_store_by_owner_id.yml` - 소유자 ID로 조회 성공 케이스
  - `stores/owner/should_return_null_when_owner_has_no_store.yml` - 소유자 스토어 미존재 시 404 케이스
- **테스트 파일**: `adapter/outbound/client/StoreServiceFeignClientConsumerContractTest.kt`
- **Stub 아티팩트**: `com.groom:store-service-contract-stubs`
- **Stub 저장소**: GitHub Packages (`GroomC4/c4ang-store-service`)

---

## 2. 향후 추가 가능한 의존성

### Customer Service (미구현)

현재는 Istio를 통한 인증 처리로 Customer Service 직접 호출이 불필요하지만,
향후 사용자 정보 조회가 필요할 경우 추가될 수 있습니다.

### Order Service (미구현)

주문 시 재고 차감 등의 연동이 필요할 경우 추가될 수 있습니다.
현재는 이벤트 기반 비동기 처리를 고려 중입니다.

---

## Contract Test 실행 방법

```bash
# Consumer Contract Test 실행 (store-service stub 필요)
./gradlew :product-api:contractTest

# 테스트 실행 전 필요 사항
# - GITHUB_ACTOR, GITHUB_TOKEN 환경변수 설정
# - 또는 ~/.gradle/gradle.properties에 gpr.user, gpr.key 설정
```

---

## 참고

- Store Service 저장소: https://github.com/GroomC4/c4ang-store-service
- Spring Cloud Contract 문서: https://spring.io/projects/spring-cloud-contract
