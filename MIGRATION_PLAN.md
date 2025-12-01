# c4ang-product-service 마이그레이션 작업 계획서

## 개요
c4ang-platform-core의 책임 변경에 따른 product-service 마이그레이션 및 MSA 전환 작업

---

## Phase 1: Platform Core 연동 변경
**목표**: platform-core 라이브러리 의존성으로 전환하고, 기존 서브모듈 기반 설정 제거

### 1.1 build.gradle.kts 수정
- [x] 서브모듈 제거 완료 (c4ang-contract-hub, c4ang-platform-core, c4ang-store-service)

**변경 필요 사항:**
- settings.gradle.kts: GitHub Packages 저장소 추가
- build.gradle.kts (root): GitHub Packages 저장소 추가, Kotlin 버전 2.0.21로 맞춤
- product-api/build.gradle.kts:
  - `platform-core` 라이브러리 의존성 추가 (io.github.groomc4:platform-core:2.0.3)
  - `testcontainers-starter` 테스트 의존성 추가 (io.github.groomc4:testcontainers-starter:2.0.3)
  - contract-hub 서브모듈 의존성 제거
  - Spring Cloud Contract 설정 추가

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/build.gradle.kts`
- `/Users/groom/IdeaProjects/c4ang-store-service/settings.gradle.kts`
- `/Users/groom/IdeaProjects/c4ang-store-service/store-api/build.gradle.kts`
- `/Users/groom/IdeaProjects/c4ang-platform-core/README.md`

### 1.2 DataSource 설정 제거/정리
platform-core가 DataSource 라우팅을 자동으로 처리하므로 기존 수동 설정 제거

**삭제할 파일:**
- `product-api/src/main/kotlin/com/groom/product/configuration/jpa/DataSourceConfig.kt`
- `product-api/src/main/kotlin/com/groom/product/configuration/jpa/DataSourceType.kt`
- `product-api/src/main/kotlin/com/groom/product/common/configuration/jpa/CreatedAndUpdatedAtAuditEntity.kt` (중복)

**유지할 파일 (configuration 패키지 정리):**
- `product-api/src/main/kotlin/com/groom/product/configuration/jpa/JpaConfig.kt`
- `product-api/src/main/kotlin/com/groom/product/configuration/jpa/AuditorAwareConfig.kt`
- `product-api/src/main/kotlin/com/groom/product/configuration/jpa/CreatedAndUpdatedAtAuditEntity.kt`

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/main/kotlin/com/groom/store/configuration/jpa/`

### 1.3 테스트 설정 변경
testcontainers-starter를 사용한 통합 테스트 환경 구성

**삭제할 파일:**
- `product-api/src/test/kotlin/com/groom/product/common/config/TestDataSourceConfig.kt`
- `product-api/src/test/kotlin/com/groom/product/common/extension/SharedContainerExtension.kt`

**수정할 파일:**
- `product-api/src/test/kotlin/com/groom/product/common/annotation/IntegrationTest.kt`
  - platform-core의 @IntegrationTest 사용으로 변경

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/test/kotlin/com/groom/store/common/IntegrationTestBase.kt`

---

## Phase 2: 프로필 및 설정 파일 정리
**목표**: dev 프로필 추가, 서버 포트 8083으로 변경

### 2.1 application.yml 수정
- 서버 포트: 8082 → 8083
- spring.application.name: product-api (유지)
- 로깅 패키지명 수정: com.groom.ecommerce → com.groom.product

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/main/resources/application.yml`

### 2.2 application-local.yml 수정
- 포트 일관성 확인
- feign client URL 수정 (store-service: 8082)
- platform-core 기본 설정 활용

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/main/resources/application-local.yml`

### 2.3 application-dev.yml 신규 생성
k3d 개발환경 (Kubernetes) 프로필

**포함 내용:**
- DB 연결 (환경변수 기반)
- Redis 연결 (ExternalName 서비스)
- Kafka 연결
- Feign Client URL (k8s 서비스명 사용)

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/main/resources/application-dev.yml`

### 2.4 application-prod.yml 수정
- 환경변수 기반 설정으로 변경
- HikariCP 풀 설정 추가
- Kafka 토픽 환경변수화

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/main/resources/application-prod.yml`

---

## Phase 3: Dockerfile 생성
**목표**: 컨테이너 이미지 빌드를 위한 Dockerfile 작성

### 3.1 Dockerfile 생성 (루트 경로)
- Multi-stage 빌드 (gradle:8.5-jdk21 → eclipse-temurin:21-jre-alpine)
- GitHub Packages 인증 ARG 전달
- Health check 설정
- 포트: 8080 (컨테이너 내부)

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/Dockerfile`

---

## Phase 4: Spring Cloud Contract 설정
**목표**: Consumer-Driven Contract 패턴 적용

### 4.1 product-events 모듈 생성 (선택사항)
도메인 이벤트 정의 모듈 (store-events 패턴 참고)

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/store-events/build.gradle.kts`

### 4.2 Contract Test 기반 클래스 생성
Provider Contract Test를 위한 Base 클래스

**생성할 파일:**
- `product-api/src/test/kotlin/com/groom/product/common/ContractTestBase.kt`
- `product-api/src/test/kotlin/com/groom/product/common/IntegrationTestBase.kt`

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/test/kotlin/com/groom/store/common/ContractTestBase.kt`
- `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/test/kotlin/com/groom/store/common/IntegrationTestBase.kt`

### 4.3 Contract 파일 작성
product-service가 제공하는 API에 대한 Contract 정의

**생성할 디렉토리:**
- `product-api/src/test/resources/contracts/product-api/`

**Contract 예시:**
- products/get/should_get_product_by_id.yml
- products/search/should_search_products.yml
- products/register/should_register_product.yml

### 4.4 Consumer Contract Test 작성
store-service API 호출에 대한 Consumer Contract Test

**생성할 파일:**
- `product-api/src/test/kotlin/com/groom/product/contract/StoreServiceFeignClientConsumerContractTest.kt`

**참고 파일:**
- `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/test/kotlin/com/groom/store/contract/UserServiceFeignClientConsumerContractTest.kt`

---

## Phase 5: Store Service 연동 구현
**목표**: product-service에서 store-service 호출 구현

### 5.1 StoreServiceFeignClient 생성
store-service API 호출을 위한 Feign Client

**생성할 파일:**
- `product-api/src/main/kotlin/com/groom/product/adapter/out/client/StoreServiceFeignClient.kt`
- `product-api/src/main/kotlin/com/groom/product/adapter/out/client/dto/StoreResponse.kt`

**Contract 요청 (store-service에 전달):**
product-service가 store-service에 요청할 API에 대한 Contract 정의 필요
- GET /api/v1/stores/{storeId} - 스토어 조회
- GET /api/v1/stores/owner/{ownerId} - 소유자별 스토어 조회

### 5.2 StoreClientAdapter 구현 완료
기존 TODO로 남겨둔 구현 완료

**수정할 파일:**
- `product-api/src/main/kotlin/com/groom/product/adapter/out/client/StoreClientAdapter.kt`

---

## 작업 진행 체크리스트

### Phase 1: Platform Core 연동 변경
- [ ] settings.gradle.kts 수정
- [ ] build.gradle.kts (root) 수정
- [ ] product-api/build.gradle.kts 수정
- [ ] 불필요한 DataSource 설정 파일 삭제
- [ ] 테스트 설정 파일 정리
- [ ] 빌드 확인

### Phase 2: 프로필 및 설정 파일 정리
- [ ] application.yml 수정 (포트 8083)
- [ ] application-local.yml 수정
- [ ] application-dev.yml 신규 생성
- [ ] application-prod.yml 수정
- [ ] 빌드 및 테스트 확인

### Phase 3: Dockerfile 생성
- [ ] Dockerfile 작성
- [ ] 로컬 Docker 빌드 테스트

### Phase 4: Spring Cloud Contract 설정
- [ ] ContractTestBase.kt 생성
- [ ] IntegrationTestBase.kt 생성
- [ ] Contract 파일 작성 (YAML)
- [ ] Consumer Contract Test 작성
- [ ] Contract Test 실행 확인

### Phase 5: Store Service 연동 구현
- [ ] StoreServiceFeignClient 생성
- [ ] StoreClientAdapter 구현 완료
- [ ] Consumer Contract 정의 (store-service에 전달용)

---

## 참고 자료

### Platform Core 문서
- `/Users/groom/IdeaProjects/c4ang-platform-core/README.md`

### Store Service 참고 파일
- 빌드 설정: `/Users/groom/IdeaProjects/c4ang-store-service/store-api/build.gradle.kts`
- 설정 파일: `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/main/resources/`
- 테스트 설정: `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/test/kotlin/com/groom/store/common/`
- Contract: `/Users/groom/IdeaProjects/c4ang-store-service/store-api/src/test/resources/contracts/`
- Dockerfile: `/Users/groom/IdeaProjects/c4ang-store-service/Dockerfile`
