# Product Service 파일 인덱스

> 각 파일의 위치, 책임, 핵심 로직을 설명하는 문서입니다.

## 목차

- [아키텍처 개요](#아키텍처-개요)
- [Adapter Layer](#adapter-layer)
  - [Inbound Adapters](#inbound-adapters)
  - [Outbound Adapters](#outbound-adapters)
- [Application Layer](#application-layer)
- [Domain Layer](#domain-layer)
- [Configuration](#configuration)
- [Common](#common)

---

## 아키텍처 개요

```
product-api/src/main/kotlin/com/groom/product/
├── adapter/                    # 외부 시스템 연동 (Hexagonal Architecture)
│   ├── inbound/               # 외부 → 내부 (Controller, Consumer)
│   │   ├── web/              # REST API Controller
│   │   ├── internal/         # Internal API (타 서비스용)
│   │   └── event/            # Kafka Consumer
│   └── outbound/              # 내부 → 외부 (Repository, Client)
│       ├── persistence/      # JPA Repository
│       ├── client/           # Feign Client (store-service)
│       ├── storage/          # S3 Uploader
│       ├── ai/               # Gemini AI Client
│       ├── redis/            # Redis Adapter
│       └── event/            # Kafka Producer
│
├── application/               # 애플리케이션 서비스
│   ├── service/              # Use Case 구현
│   ├── dto/                  # Command, Query, Result DTO
│   └── event/                # 도메인 이벤트 핸들러
│
├── domain/                    # 도메인 계층 (비즈니스 로직)
│   ├── model/                # Entity, Value Object
│   ├── service/              # 도메인 서비스
│   ├── port/                 # 포트 인터페이스
│   └── event/                # 도메인 이벤트
│
├── common/                    # 공통 유틸리티
│   ├── exception/            # 예외 처리
│   └── util/                 # 유틸리티
│
└── configuration/             # 설정 클래스
    ├── jpa/                  # JPA 설정
    ├── kafka/                # Kafka 설정
    ├── event/                # 이벤트 설정
    ├── filter/               # 필터 설정
    └── properties/           # 설정 프로퍼티
```

---

## Adapter Layer

### Inbound Adapters

#### REST API Controllers

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `adapter/inbound/web/ProductCommandController.kt` | 상품 명령(CUD) API | 상품 등록/수정/삭제/숨김 토글. Istio Gateway에서 전달받은 X-User-Id 헤더로 사용자 식별 |
| `adapter/inbound/web/ProductQueryController.kt` | 상품 조회(R) API | 상품 상세 조회, 목록 검색, 내 상품 목록 조회. 페이징 및 필터링 지원 |
| `adapter/inbound/web/ProductImageController.kt` | 상품 이미지 API | 이미지 S3 업로드, AI 설명 생성 |
| `adapter/inbound/web/dto/` | Web Layer DTO | Request/Response DTO. 각 DTO는 `toCommand()`, `from()` 메서드로 변환 |

#### Internal API

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `adapter/inbound/internal/ProductInternalController.kt` | 타 서비스용 Internal API | Order/Payment 서비스가 사용. 단건/다건 상품 조회 (`/internal/v1/products`) |
| `adapter/inbound/internal/InternalApiExceptionHandler.kt` | Internal API 예외 처리 | Internal API 전용 에러 응답 포맷 |
| `adapter/inbound/internal/dto/` | Internal API DTO | Consumer-Driven Contract 패턴. Consumer가 필요한 필드만 포함 |

#### Kafka Consumers

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `adapter/inbound/event/OrderCreatedConsumer.kt` | 주문 생성 이벤트 처리 | `order.created` 토픽 소비 → 재고 예약 → `stock.reserved`/`stock.reservation.failed` 발행. 멱등성 보장 (ProcessedEvent) |
| `adapter/inbound/event/PaymentCompletedConsumer.kt` | 결제 완료 이벤트 처리 | `payment.completed` 토픽 소비 → DB 재고 확정 (Redis 예약 → DB 차감) |
| `adapter/inbound/event/StoreDeletedConsumer.kt` | 스토어 삭제 이벤트 처리 | `store.deleted` 토픽 소비 → 해당 스토어 상품 전체 soft delete |
| `adapter/inbound/event/StoreInfoUpdatedConsumer.kt` | 스토어 정보 변경 이벤트 처리 | `store.info.updated` 토픽 소비 → 상품의 비정규화된 storeName 업데이트 |

### Outbound Adapters

#### Persistence

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `adapter/outbound/persistence/ProductPersistenceAdapter.kt` | Product 영속성 Adapter | `LoadProductPort`, `SaveProductPort`, `SearchProductsPort` 구현. Domain↔JPA 연결 |
| `adapter/outbound/persistence/ProductJpaRepository.kt` | Product JPA Repository | Spring Data JPA. `@EntityGraph`로 N+1 방지, Custom Query 포함 |
| `adapter/outbound/persistence/ProductSpecifications.kt` | 검색 Specification | JPA Criteria API 기반 동적 쿼리 빌더 (이름, 가격, 카테고리, 스토어 필터링) |
| `adapter/outbound/persistence/CategoryPersistenceAdapter.kt` | Category 영속성 Adapter | 카테고리 조회 포트 구현 |
| `adapter/outbound/persistence/ProcessedEventRepository.kt` | 이벤트 멱등성 Repository | 처리된 이벤트 ID 저장으로 중복 처리 방지 |
| `adapter/outbound/persistence/ProductImageJpaRepository.kt` | ProductImage Repository | 상품 이미지 영속화 |
| `adapter/outbound/persistence/ProductAuditJpaRepository.kt` | 상품 감사 Repository | 상품 변경 이력 저장 |

#### External Clients

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `adapter/outbound/client/StoreServiceFeignClient.kt` | Store Service Feign Client | Store Service Internal API 호출 (`/internal/v1/stores`) |
| `adapter/outbound/client/StoreServiceClient.kt` | Store Client 인터페이스 | 테스트 Mock 교체 가능하도록 인터페이스 분리 |
| `adapter/outbound/client/StoreClientAdapter.kt` | Store Client Adapter | `LoadStorePort` 구현. Feign → Port 연결 |
| `adapter/outbound/client/dto/StoreInternalDto.kt` | Store 응답 DTO | Store Service 응답 매핑 |

#### AI Integration

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `adapter/outbound/ai/GeminiProductDescriptionGenerator.kt` | AI 설명 생성 구현체 | Google Gemini API로 상품 설명 자동 생성. `ProductDescriptionGenerator` 포트 구현 |
| `adapter/outbound/ai/GeminiFeignClient.kt` | Gemini Feign Client | Google Gemini REST API 호출 |
| `adapter/outbound/ai/GeminiFeignConfig.kt` | Feign 설정 | API Key 헤더 설정, 타임아웃 등 |
| `adapter/outbound/ai/AiPromptAuditService.kt` | AI 프롬프트 감사 | AI 요청/응답 로깅 (비용 추적, 디버깅) |
| `adapter/outbound/ai/GeminiProperties.kt` | Gemini 설정 프로퍼티 | API Key, 모델명 등 설정값 |

#### Storage

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `adapter/outbound/storage/S3ImageUploader.kt` | S3 이미지 업로더 | MultipartFile → S3 업로드. UUID 기반 파일명 생성, Content-Type 설정 |

#### Redis

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `adapter/outbound/redis/RedisStockReservationAdapter.kt` | Redis 재고 예약 Adapter | `StockReservationPort` 구현. Redisson AtomicLong으로 원자적 재고 연산. TTL 기반 예약 만료 |

#### Event Producer

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `adapter/outbound/event/StockEventProducer.kt` | 재고 이벤트 발행 | `stock.reserved`, `stock.reservation.failed` 이벤트 Kafka 발행 |

---

## Application Layer

### Services

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `application/service/RegisterProductService.kt` | 상품 등록 Use Case | 스토어 검증 → AI 설명 생성(옵션) → Product 생성 → 저장 → 이벤트 발행 |
| `application/service/UpdateProductService.kt` | 상품 수정 Use Case | 상품 조회 → 정보 변경 → 저장 → 이벤트 발행 (변경 있을 때만) |
| `application/service/DeleteProductService.kt` | 상품 삭제 Use Case | Soft Delete (deletedAt 설정) → 이벤트 발행 |
| `application/service/ToggleProductHideService.kt` | 상품 숨김/복원 Use Case | hiddenAt 토글 → 숨김/복원 이벤트 발행 |
| `application/service/GetProductDetailService.kt` | 상품 상세 조회 Use Case | 상품 + 이미지 + 카테고리 경로 조회 |
| `application/service/SearchProductsService.kt` | 상품 검색 Use Case | 동적 Specification 기반 검색, 페이징 |
| `application/service/ListOwnerProductsService.kt` | 내 상품 목록 조회 Use Case | 스토어 소유자의 상품 목록 반환 |
| `application/service/GetProductForOrderService.kt` | Internal API 조회 서비스 | Order/Payment 서비스용 상품 정보 조회 |
| `application/service/GenerateProductDescriptionService.kt` | AI 설명 생성 서비스 | Gemini API로 상품 설명 생성 |
| `application/service/UploadProductImageService.kt` | 이미지 업로드 서비스 | S3 업로드 후 URL 반환 |
| `application/service/CategoryPathService.kt` | 카테고리 경로 서비스 | 카테고리 ID → 전체 경로 문자열 변환 |

### DTOs

| 디렉토리/패턴 | 설명 |
|--------------|------|
| `application/dto/*Command.kt` | 명령 DTO (CUD 요청) |
| `application/dto/*Query.kt` | 조회 DTO (R 요청) |
| `application/dto/*Result.kt` | 결과 DTO (응답) |

### Event Handlers

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `application/event/ProductRegisteredEventHandler.kt` | 상품 등록 이벤트 핸들러 | Kafka `product.registered` 토픽으로 이벤트 발행 |
| `application/event/ProductInfoUpdatedEventHandler.kt` | 상품 수정 이벤트 핸들러 | Kafka `product.info.updated` 토픽으로 이벤트 발행 |
| `application/event/ProductDeletedEventHandler.kt` | 상품 삭제 이벤트 핸들러 | Kafka `product.deleted` 토픽으로 이벤트 발행 |
| `application/event/ProductHiddenEventHandler.kt` | 상품 숨김 이벤트 핸들러 | Kafka `product.hidden` 토픽으로 이벤트 발행 |
| `application/event/ProductRestoredEventHandler.kt` | 상품 복원 이벤트 핸들러 | Kafka `product.restored` 토픽으로 이벤트 발행 |

---

## Domain Layer

### Models (Entities & Value Objects)

| 파일 | 타입 | 책임 | 핵심 로직 |
|------|------|------|-----------|
| `domain/model/Product.kt` | Aggregate Root | 상품 엔티티 | `updateInfo()`, `decreaseStock()`, `increaseStock()`, `delete()`, `toggleHide()` 비즈니스 메서드 포함 |
| `domain/model/ProductImage.kt` | Entity | 상품 이미지 | Product와 1:N 관계, displayOrder로 정렬 |
| `domain/model/ProductCategory.kt` | Entity | 상품 카테고리 | 자기참조 (parentId)로 계층 구조 |
| `domain/model/ProductAudit.kt` | Entity | 상품 변경 이력 | 감사 로깅용 |
| `domain/model/ProcessedEvent.kt` | Entity | 처리된 이벤트 | 이벤트 멱등성 보장 |
| `domain/model/Price.kt` | Value Object | 가격 | 음수 검증, BigDecimal 래핑 |
| `domain/model/ProductName.kt` | Value Object | 상품명 | 빈 값/길이 검증 |
| `domain/model/ProductDescription.kt` | Value Object | 상품 설명 | 길이 제한 검증 |
| `domain/model/StockQuantity.kt` | Value Object | 재고 수량 | 음수 검증 |
| `domain/model/ThumbnailUrl.kt` | Value Object | 썸네일 URL | URL 형식 검증 |
| `domain/model/StoreInfo.kt` | Value Object | 스토어 정보 | 비정규화된 스토어 정보 |

### Domain Services

| 파일 | 책임 | 핵심 로직 |
|------|------|-----------|
| `domain/service/ProductFactory.kt` | Product 생성 팩토리 | 복잡한 Product 생성 로직 캡슐화. 이미지 포함 생성 지원 |
| `domain/service/StockReservationService.kt` | 재고 예약 서비스 | **핵심 비즈니스 로직**. Redis 원자적 예약 → 성공/실패 결과 반환 → 결제 완료 시 DB 확정. Saga 패턴 지원 |
| `domain/service/CategoryPathBuilder.kt` | 카테고리 경로 빌더 | 카테고리 계층 → "전자제품 > 스마트폰 > 아이폰" 형식 변환 |
| `domain/service/ImageUploadPolicy.kt` | 이미지 업로드 정책 | 허용 확장자, 최대 크기 등 검증 |
| `domain/service/ProductDescriptionValidator.kt` | 설명 검증 | AI 생성 설명 검증 |

### Ports (Interfaces)

| 파일 | 방향 | 설명 |
|------|------|------|
| `domain/port/LoadProductPort.kt` | Outbound | 상품 조회 포트 |
| `domain/port/SaveProductPort.kt` | Outbound | 상품 저장 포트 |
| `domain/port/SearchProductsPort.kt` | Outbound | 상품 검색 포트 |
| `domain/port/LoadStorePort.kt` | Outbound | 스토어 조회 포트 (외부 서비스) |
| `domain/port/LoadCategoryPort.kt` | Outbound | 카테고리 조회 포트 |
| `domain/port/StockReservationPort.kt` | Outbound | Redis 재고 예약 포트 |
| `domain/port/ProductDescriptionGenerator.kt` | Outbound | AI 설명 생성 포트 |
| `domain/port/ProductReader.kt` | Inbound | 상품 읽기 포트 |
| `domain/port/ProductSearcher.kt` | Inbound | 상품 검색 포트 |
| `domain/port/ProductStoreReader.kt` | Inbound | 스토어별 상품 읽기 포트 |
| `domain/port/CategoryReader.kt` | Inbound | 카테고리 읽기 포트 |

### Domain Events

| 파일 | 설명 |
|------|------|
| `domain/event/DomainEvent.kt` | 도메인 이벤트 마커 인터페이스 |
| `domain/event/DomainEventPublisher.kt` | 이벤트 발행자 인터페이스 |
| `domain/event/ProductRegisteredEvent.kt` | 상품 등록 이벤트 |
| `domain/event/ProductInfoUpdatedEvent.kt` | 상품 수정 이벤트 |
| `domain/event/ProductDeletedEvent.kt` | 상품 삭제 이벤트 |
| `domain/event/ProductHiddenEvent.kt` | 상품 숨김 이벤트 |
| `domain/event/ProductRestoredEvent.kt` | 상품 복원 이벤트 |

---

## Configuration

| 파일 | 책임 |
|------|------|
| `configuration/RedissonConfig.kt` | Redisson 클라이언트 설정 |
| `configuration/TimeZoneConfig.kt` | 서버 타임존 설정 (Asia/Seoul) |
| `configuration/jpa/JpaConfig.kt` | JPA 설정 (Auditing 등) |
| `configuration/jpa/AuditorAwareConfig.kt` | 생성자/수정자 자동 설정 |
| `configuration/jpa/CreatedAndUpdatedAtAuditEntity.kt` | 공통 Audit 엔티티 (createdAt, updatedAt) |
| `configuration/kafka/KafkaProducerConfig.kt` | Kafka Producer 설정 (Avro 직렬화) |
| `configuration/kafka/KafkaConsumerConfig.kt` | Kafka Consumer 설정 (Avro 역직렬화) |
| `configuration/event/SpringDomainEventPublisher.kt` | Spring ApplicationEventPublisher 래퍼 |
| `configuration/filter/LocalDevAuthFilter.kt` | 로컬 개발용 인증 필터 (X-User-Id 헤더 주입) |
| `configuration/properties/S3Properties.kt` | S3 설정 프로퍼티 |
| `configuration/properties/PropertiesConfig.kt` | @ConfigurationProperties 활성화 |

---

## Common

| 파일 | 책임 |
|------|------|
| `common/exception/DomainException.kt` | 도메인 예외 기본 클래스 |
| `common/exception/ErrorCode.kt` | 에러 코드 Enum |
| `common/exception/handler/GlobalExceptionHandler.kt` | 전역 예외 핸들러 (@RestControllerAdvice) |
| `common/exception/handler/ErrorResponse.kt` | 에러 응답 DTO |
| `common/util/IstioHeaderExtractor.kt` | Istio Gateway 헤더 추출 유틸 (X-User-Id, X-User-Role) |

---

## 핵심 플로우

### 1. 상품 등록 플로우

```
Client → ProductCommandController.registerProduct()
       → IstioHeaderExtractor.extractUserId()
       → RegisterProductService.register()
         → Value Objects 생성 (ProductName, Price, ...)
         → LoadStorePort.loadByIdAndOwnerId() (스토어 검증)
         → [Optional] GenerateProductDescriptionService (AI 설명)
         → ProductFactory.createNewProduct()
         → SaveProductPort.save()
         → DomainEventPublisher.publish(ProductRegisteredEvent)
       → ProductRegisteredEventHandler (Kafka 발행)
```

### 2. 재고 예약 플로우 (Saga Pattern)

```
Order Service → Kafka "order.created"
             → OrderCreatedConsumer.consume()
               → 멱등성 체크 (ProcessedEventRepository)
               → StockReservationService.reserveStock()
                 → LoadProductPort.loadAllById() (DB 재고 확인)
                 → StockReservationPort.getOrInitializeStock() (Redis 초기화)
                 → StockReservationPort.decrementStock() (원자적 차감)
                 → [실패 시] rollbackReservation()
               → StockEventProducer.publishStockReserved() or publishStockReservationFailed()
               → ProcessedEventRepository.save() (멱등성 기록)

Payment Service → Kafka "payment.completed"
               → PaymentCompletedConsumer.consume()
                 → StockReservationService.confirmStock()
                   → LoadProductPort.loadById()
                   → Product.decreaseStock() (DB 차감)
                   → SaveProductPort.save()
                   → StockReservationPort.deleteReservation() (Redis 정리)
```

### 3. Internal API 플로우

```
Order/Payment Service → GET /internal/v1/products/{id}
                      → ProductInternalController.getProductById()
                        → GetProductForOrderService.getProductById()
                        → InternalProductResponse (필요한 필드만)

Order/Payment Service → POST /internal/v1/products/search
                      → ProductInternalController.getProductsByIds()
                        → GetProductForOrderService.getProductsByIds()
                        → List<InternalProductResponse>
```

---

## 테스트 구조

```
product-api/src/test/kotlin/com/groom/product/
├── common/
│   ├── IntegrationTestBase.kt    # 통합 테스트 베이스 (Testcontainers)
│   ├── ContractTestBase.kt       # Contract 테스트 베이스
│   └── fixture/                  # 테스트 픽스처
├── domain/
│   ├── model/                    # 엔티티 단위 테스트
│   └── service/                  # 도메인 서비스 단위 테스트
└── adapter/
    ├── inbound/web/              # Controller 통합 테스트
    └── outbound/client/          # Consumer Contract 테스트
```
