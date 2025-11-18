# CQRS 패턴 적용 설계 및 구현 계획서

## 문서 정보
- **작성일**: 2025-11-18
- **대상 서비스**: c4ang-product-service
- **현재 아키텍처**: Hexagonal Architecture (Ports & Adapters)
- **목표**: CQRS (Command Query Responsibility Segregation) 패턴 적용

---

## 목차
1. [현황 분석](#1-현황-분석)
2. [CQRS 적용 목적 및 기대효과](#2-cqrs-적용-목적-및-기대효과)
3. [설계 원칙 및 아키텍처](#3-설계-원칙-및-아키텍처)
4. [단계별 구현 계획](#4-단계별-구현-계획)
5. [기술 스택 및 인프라](#5-기술-스택-및-인프라)
6. [데이터 모델 설계](#6-데이터-모델-설계)
7. [이벤트 설계](#7-이벤트-설계)
8. [성능 및 확장성 고려사항](#8-성능-및-확장성-고려사항)
9. [리스크 및 대응방안](#9-리스크-및-대응방안)
10. [마이그레이션 전략](#10-마이그레이션-전략)

---

## 1. 현황 분석

### 1.1 현재 아키텍처 상태

#### ✅ CQRS-Ready 요소
```
현재 시스템은 부분적으로 CQRS 패턴을 따르고 있음:

1. Controller 레벨 분리
   - ProductCommandController: 쓰기 작업 (CREATE, UPDATE, DELETE)
   - ProductQueryController: 읽기 작업 (GET, SEARCH)

2. Application Layer DTO 분리
   - Command DTOs: RegisterProductCommand, UpdateProductCommand, etc.
   - Query DTOs: SearchProductsQuery, GetProductDetailQuery, etc.
   - Result DTOs: 각 작업에 대한 전용 응답 객체

3. Domain Event 인프라
   - 5개 도메인 이벤트 정의 (Registered, Updated, Deleted, Hidden, Restored)
   - Event Handler: @TransactionalEventListener 기반
   - Audit Log 자동 생성

4. Port 기반 추상화
   - LoadProductPort, SaveProductPort, SearchProductsPort
   - 쉽게 다른 구현체로 교체 가능
```

#### ❌ 미적용 CQRS 요소
```
1. 단일 데이터베이스
   - Read/Write 모두 동일한 PostgreSQL 사용
   - 트랜잭션 모델(정규화)로 조회 수행

2. 동기식 커맨드 처리
   - HTTP Request-Response 패턴
   - 즉시 결과 반환

3. 이벤트 소싱 미적용
   - 상태 변경만 저장, 이벤트 스트림 없음
   - 과거 시점 상태 조회 불가

4. 외부 이벤트 발행 없음
   - Kafka/RabbitMQ 등 메시지 큐 미사용
   - 다른 마이크로서비스와 이벤트 기반 통합 없음

5. Read Model 미구축
   - 조회 최적화된 별도 저장소 없음
   - 정규화된 테이블에서 직접 조회
```

### 1.2 현재 성능 특성

```yaml
현재 조회 패턴:
  - N+1 문제 방지: FETCH JOIN, Batch Loading 사용
  - 비정규화: storeName을 Product에 포함하여 JOIN 감소
  - 인덱스: 검색 컬럼에 GIN, B-Tree 인덱스 적용
  - pg_trgm: PostgreSQL 유사도 검색 확장 사용

현재 한계:
  - 복잡한 조회 시 JOIN 비용 증가
  - 쓰기 작업 시 트랜잭션 락으로 읽기 대기
  - 검색 필터 조합 증가 시 성능 저하
  - 실시간 집계 쿼리 부담
```

### 1.3 도메인 모델 분석

```kotlin
// Aggregate Root
Product(
    storeId: UUID,
    storeName: String,        // 비정규화 (Read 최적화)
    categoryId: UUID,
    name: String,
    status: String,           // ON_SALE, OFF_SHELF
    price: BigDecimal,
    stockQuantity: Int,
    thumbnailUrl: String?,
    description: String?,
    hiddenAt: LocalDateTime?, // Soft hide
    deletedAt: LocalDateTime?, // Soft delete
    images: List<ProductImage>
)

// Child Entity
ProductImage(
    imageType: String,        // PRIMARY, DETAIL
    imageUrl: String,
    displayOrder: Int
)

// Audit Entity (Event Log)
ProductAudit(
    productId: UUID,
    eventType: String,        // PRODUCT_REGISTERED, etc.
    statusSnapshot: String,
    changeSummary: String,
    recordedAt: LocalDateTime,
    metadata: JSONB
)
```

---

## 2. CQRS 적용 목적 및 기대효과

### 2.1 적용 목적

1. **읽기/쓰기 부하 분리**
   - 상품 조회 트래픽이 압도적으로 많은 전자상거래 특성
   - 쓰기 작업(등록/수정/삭제)과 읽기 작업(검색/조회) 독립적 확장

2. **조회 성능 극대화**
   - 비정규화된 Read Model로 JOIN 제거
   - 검색에 최적화된 인덱스 전략
   - 캐시 친화적인 데이터 구조

3. **복잡도 관리**
   - Command: 비즈니스 규칙 검증에 집중
   - Query: 조회 성능에 집중
   - 각각 독립적으로 최적화 가능

4. **확장성 및 유연성**
   - Read Replica 여러 개 구성 가능
   - NoSQL, ElasticSearch 등 다양한 조회 저장소 활용
   - MSA 환경에서 이벤트 기반 통합

### 2.2 기대효과

```yaml
성능 개선:
  - 조회 응답 시간: 30-50% 감소 (JOIN 제거)
  - 검색 쿼리 처리량: 2-3배 향상 (Read Model 최적화)
  - 쓰기 작업 락 대기: 제거 (DB 분리)

확장성:
  - Read Replica: 수평 확장 가능
  - 검색 엔진: ElasticSearch 도입 가능
  - 캐시: Redis 기반 조회 캐싱

유지보수성:
  - 책임 분리: Command/Query 로직 독립
  - 테스트 용이성: 각 계층 독립 테스트
  - 버전 관리: Read Model 스키마 변경 용이

비즈니스 가치:
  - 이벤트 기반 분석: 상품 등록/수정 패턴 분석
  - 시계열 데이터: 가격 변동 추적
  - 외부 연동: 타 서비스에 이벤트 제공
```

---

## 3. 설계 원칙 및 아키텍처

### 3.1 CQRS 아키텍처 다이어그램

```
┌─────────────────────────────────────────────────────────────┐
│                     API Gateway / Client                     │
└───────────────┬─────────────────────────┬───────────────────┘
                │                         │
        ┌───────▼────────┐       ┌────────▼─────────┐
        │  Command Side  │       │   Query Side     │
        │  (Write Model) │       │  (Read Model)    │
        └───────┬────────┘       └────────▲─────────┘
                │                         │
        ┌───────▼────────┐                │
        │  Command Bus   │                │
        │   (Mediator)   │                │
        └───────┬────────┘                │
                │                         │
        ┌───────▼────────┐                │
        │ Command Handler│                │
        │  (Use Cases)   │                │
        └───────┬────────┘                │
                │                         │
        ┌───────▼────────┐         ┌─────┴──────┐
        │ Domain Model   │         │ Event Bus  │
        │  (Aggregate)   │────────▶│  (Kafka)   │
        └───────┬────────┘         └─────┬──────┘
                │                         │
        ┌───────▼────────┐         ┌─────▼──────────┐
        │  Write DB      │         │ Event Handler  │
        │  (PostgreSQL)  │         │  (Projection)  │
        │  Normalized    │         └─────┬──────────┘
        └────────────────┘                │
                                   ┌──────▼─────────┐
                                   │   Read DB      │
                                   │  (PostgreSQL)  │
                                   │  Denormalized  │
                                   └────────────────┘

                                   ┌────────────────┐
                                   │ ElasticSearch  │
                                   │  (Search Index)│
                                   └────────────────┘
```

### 3.2 핵심 설계 원칙

#### 1. **Eventual Consistency (최종 일관성)**
```yaml
원칙:
  - Command 처리 후 즉시 Read Model 업데이트를 보장하지 않음
  - 이벤트를 통해 비동기적으로 Read Model 동기화
  - 일반적으로 수백 ms ~ 수초 내 동기화

구현:
  - Kafka를 통한 이벤트 발행
  - Event Handler가 Read Model 업데이트
  - Retry 및 Dead Letter Queue로 안정성 보장

사용자 경험:
  - 상품 등록 후 "등록 중..." 상태 표시
  - 조회 시 "최근 등록된 상품" 섹션 분리
  - WebSocket으로 실시간 알림
```

#### 2. **Command Validation (커맨드 검증)**
```yaml
검증 계층:
  1. API Layer: 기본 형식 검증 (DTO Validation)
  2. Application Layer: 비즈니스 규칙 검증
  3. Domain Layer: 불변 조건 검증 (Invariants)

예시:
  RegisterProductCommand:
    - API: name 길이, price 양수
    - Application: storeId 존재 여부, 중복 상품명
    - Domain: Product 생성 시 필수값 검증
```

#### 3. **Idempotency (멱등성)**
```yaml
문제:
  - 네트워크 재시도로 중복 커맨드 처리 가능
  - 같은 커맨드 여러 번 실행해도 결과 동일해야 함

해결:
  - Command에 Idempotency Key 포함 (UUID)
  - 처리된 커맨드 ID를 Redis/DB에 저장
  - 중복 요청 시 이전 결과 반환

구현:
  @IdempotentCommand(key = "command.idempotencyKey")
  fun handle(command: RegisterProductCommand): Result
```

#### 4. **Event Versioning (이벤트 버전 관리)**
```yaml
문제:
  - 이벤트 스키마 변경 시 기존 이벤트 파싱 실패
  - Event Handler 버전 호환성 필요

해결:
  - 이벤트에 version 필드 추가
  - Backward-compatible 변경만 허용
  - 필요 시 새 이벤트 타입 생성

예시:
  ProductRegisteredEventV1 → ProductRegisteredEventV2
  Handler는 두 버전 모두 처리
```

---

## 4. 단계별 구현 계획

### Phase 1: Read Model 분리 (2주)

#### 목표
- Write DB와 Read DB 물리적 분리
- 이벤트 기반 Read Model 동기화
- 기존 Query 코드를 Read DB로 마이그레이션

#### 작업 항목

```yaml
1.1 Read Database 구축:
  - PostgreSQL Read Replica 생성
  - 비정규화된 테이블 스키마 설계
    * product_read: 조회 최적화 테이블
    * product_search: 검색 인덱스용 테이블
  - 인덱스 전략 수립

  테이블 예시:
    CREATE TABLE product_read (
        id UUID PRIMARY KEY,
        store_id UUID NOT NULL,
        store_name TEXT NOT NULL,
        category_id UUID NOT NULL,
        category_path TEXT NOT NULL,  -- "전자제품 > 모바일"
        product_name TEXT NOT NULL,
        status TEXT NOT NULL,
        price NUMERIC(12,2) NOT NULL,
        stock_quantity INT NOT NULL,
        thumbnail_url TEXT,
        description TEXT,
        primary_image_url TEXT,       -- 비정규화
        detail_image_urls TEXT[],     -- 비정규화
        hidden_at TIMESTAMPTZ,
        created_at TIMESTAMPTZ NOT NULL,
        updated_at TIMESTAMPTZ NOT NULL,

        -- 검색 최적화 인덱스
        INDEX idx_product_read_name_gin ON product_read USING GIN (product_name gin_trgm_ops),
        INDEX idx_product_read_store ON product_read (store_id),
        INDEX idx_product_read_category ON product_read (category_id),
        INDEX idx_product_read_price ON product_read (price),
        INDEX idx_product_read_created ON product_read (created_at DESC)
    );

1.2 DataSource 설정:
  - Write DataSource: Write DB (별도 PostgreSQL)
  - Read DataSource: Read DB (별도 PostgreSQL)
  - 두 DB는 완전히 독립적 (물리적 복제 없음)
  - 이벤트로만 동기화

  코드 예시:
    @Configuration
    class DataSourceConfig {
        @Bean("writeDataSource")
        @ConfigurationProperties("spring.datasource.write")
        fun writeDataSource(): DataSource {
            return DataSourceBuilder.create().build()
        }

        @Bean("readDataSource")
        @ConfigurationProperties("spring.datasource.read")
        fun readDataSource(): DataSource {
            return DataSourceBuilder.create().build()
        }

        @Bean
        @Primary
        fun routingDataSource(
            @Qualifier("writeDataSource") write: DataSource,
            @Qualifier("readDataSource") read: DataSource
        ): DataSource {
            val routing = ReplicationRoutingDataSource()
            routing.setDefaultTargetDataSource(write)
            routing.setTargetDataSources(mapOf(
                DataSourceType.WRITE to write,
                DataSourceType.READ to read
            ))
            return routing
        }
    }

  application.yml:
    spring:
      datasource:
        write:
          jdbc-url: jdbc:postgresql://write-db:5432/product_write
          username: write_user
          password: xxx
        read:
          jdbc-url: jdbc:postgresql://read-db:5432/product_read
          username: read_user
          password: xxx

1.3 Event Handler 구현:
  - ProductRegisteredEventHandler → product_read INSERT
  - ProductUpdatedEventHandler → product_read UPDATE
  - ProductDeletedEventHandler → product_read 상태 변경

  코드 예시:
    @Component
    class ProductReadModelProjector(
        private val productReadRepository: ProductReadRepository
    ) {
        @TransactionalEventListener(phase = AFTER_COMMIT)
        @Transactional(propagation = REQUIRES_NEW)
        fun project(event: ProductRegisteredEvent) {
            val readModel = ProductRead(
                id = event.productId,
                storeName = event.storeName,
                categoryPath = buildCategoryPath(event.categoryId),
                productName = event.name,
                // ... 모든 필드 매핑
            )
            productReadRepository.save(readModel)
        }
    }

1.4 Query 서비스 마이그레이션:
  - SearchProductsService → ReadDataSource 사용
  - GetProductDetailService → ReadDataSource 사용
  - 기존 코드와 동일한 인터페이스 유지

  코드 예시:
    @Service
    @Transactional(readOnly = true)  // 자동으로 Read DB 사용
    class SearchProductsService(
        private val productReadRepository: ProductReadRepository
    ) {
        fun search(query: SearchProductsQuery): SearchProductsResult {
            // product_read 테이블 조회
            val spec = buildSpecification(query)
            val page = productReadRepository.findAll(spec, pageable)
            return toResult(page)
        }
    }

1.5 테스트 및 검증:
  - Integration Test: Write 후 Read 동기화 확인
  - Performance Test: 조회 성능 개선 측정
  - Monitoring: Event Handler 실패율 추적
```

#### 성공 지표
```yaml
- Read Query 응답 시간: 30% 이상 감소
- Write 작업 시 Read 락 대기: 0
- Event 처리 지연: 평균 500ms 이하
- Read Model 동기화율: 99.9% 이상
```

---

### Phase 2: Command Bus 도입 (2주)

#### 목표
- 커맨드 처리를 비동기화
- Mediator 패턴으로 Command Handler 분리
- 이벤트 발행을 Kafka로 전환

#### 작업 항목

```yaml
2.1 Kafka 인프라 구축:
  - Kafka Cluster 설정 (3 brokers)
  - Topic 생성:
    * product.commands (커맨드 큐)
    * product.events (도메인 이벤트)
  - Schema Registry 설정 (Avro)

  Topic 설정:
    product.events:
      partitions: 10
      replication-factor: 3
      retention.ms: 2592000000  # 30일
      cleanup.policy: delete
      compression.type: snappy

2.2 Command Bus 구현:
  - MediatR 또는 Spring Event 기반 Command Bus
  - Command Handler 등록 및 라우팅
  - Retry 및 Dead Letter Queue

  코드 예시:
    interface Command<R> {
        val commandId: UUID
        val timestamp: LocalDateTime
        val userId: UUID?
    }

    interface CommandHandler<C : Command<R>, R> {
        suspend fun handle(command: C): R
    }

    @Service
    class CommandBus(
        private val handlers: Map<KClass<*>, CommandHandler<*, *>>
    ) {
        suspend fun <R> send(command: Command<R>): R {
            val handler = handlers[command::class]
                ?: throw CommandHandlerNotFoundException()
            return handler.handle(command) as R
        }
    }

2.3 비동기 Command 처리:
  - HTTP POST → Kafka 커맨드 발행
  - Command Consumer가 처리
  - 처리 결과를 Tracking ID로 조회

  API 흐름:
    Client                  API                    Kafka                Handler
      │                      │                      │                     │
      ├─ POST /products ────▶│                      │                     │
      │                      ├─ Publish Command ───▶│                     │
      │◀── 202 Accepted ─────┤                      │                     │
      │    { trackingId }    │                      ├─ Consume ──────────▶│
      │                      │                      │                     ├─ Process
      │                      │                      │◀─── Event ──────────┤
      │                      │◀──── Event ──────────┤                     │
      │                      ├─ Update Status       │                     │
      │                      │                      │                     │
      ├─ GET /commands/{id}─▶│                      │                     │
      │◀── 200 OK ───────────┤                      │                     │
      │    { status: "done" }│                      │                     │

2.4 Event Publishing:
  - Domain Event → Kafka 발행
  - Transactional Outbox Pattern 적용
  - Event Handler Subscribe

  Outbox Pattern:
    CREATE TABLE outbox_events (
        id UUID PRIMARY KEY,
        aggregate_type TEXT NOT NULL,
        aggregate_id UUID NOT NULL,
        event_type TEXT NOT NULL,
        event_data JSONB NOT NULL,
        created_at TIMESTAMPTZ DEFAULT now(),
        published_at TIMESTAMPTZ,

        INDEX idx_outbox_unpublished ON outbox_events (created_at)
        WHERE published_at IS NULL
    );

    -- Event Publisher가 주기적으로 폴링하여 Kafka 발행
    -- 발행 후 published_at 업데이트

2.5 Idempotency 구현:
  - Redis 기반 Command ID 저장
  - 중복 커맨드 필터링
  - 처리 결과 캐싱

  코드 예시:
    @Component
    class IdempotencyFilter(
        private val redis: RedisTemplate<String, String>
    ) {
        fun filterDuplicate(commandId: UUID): Boolean {
            val key = "command:$commandId"
            return redis.opsForValue()
                .setIfAbsent(key, "processing", 1.hours) ?: false
        }
    }
```

#### 성공 지표
```yaml
- Command 처리 응답: 즉시 202 Accepted 반환
- Command 실제 처리: 평균 2초 이하
- Event 발행 신뢰성: 99.99%
- 중복 커맨드 필터링: 100%
```

---

### Phase 3: Event Sourcing (선택, 3주)

#### 목표
- 모든 상태 변경을 이벤트로 저장
- 이벤트 스트림에서 현재 상태 재구성
- 시계열 분석 및 Audit Trail

#### 작업 항목

```yaml
3.1 Event Store 구축:
  - PostgreSQL 기반 Event Store 테이블
  - Aggregate ID 기준 이벤트 순서 보장
  - Snapshot 메커니즘

  테이블:
    CREATE TABLE event_store (
        id BIGSERIAL PRIMARY KEY,
        stream_id UUID NOT NULL,        -- Aggregate ID
        stream_type TEXT NOT NULL,      -- "Product"
        event_type TEXT NOT NULL,       -- "ProductRegistered"
        event_version INT NOT NULL,     -- 이벤트 스키마 버전
        event_data JSONB NOT NULL,      -- 이벤트 페이로드
        metadata JSONB,                 -- 사용자 ID, IP 등
        sequence_number BIGINT NOT NULL,
        occurred_at TIMESTAMPTZ DEFAULT now(),

        UNIQUE(stream_id, sequence_number),
        INDEX idx_event_store_stream ON event_store (stream_id, sequence_number),
        INDEX idx_event_store_type ON event_store (event_type),
        INDEX idx_event_store_occurred ON event_store (occurred_at DESC)
    );

    CREATE TABLE event_snapshots (
        stream_id UUID PRIMARY KEY,
        stream_type TEXT NOT NULL,
        sequence_number BIGINT NOT NULL,
        state JSONB NOT NULL,
        created_at TIMESTAMPTZ DEFAULT now()
    );

3.2 Event Sourced Repository:
  - 이벤트 저장 및 조회
  - Aggregate 상태 재구성
  - Snapshot 생성 및 적용

  코드 예시:
    @Repository
    class EventSourcedProductRepository(
        private val eventStore: EventStore
    ) : SaveProductPort {
        override fun save(product: Product): Product {
            val events = product.getDomainEvents()
            events.forEach { event ->
                eventStore.append(
                    streamId = product.id,
                    streamType = "Product",
                    event = event
                )
            }
            return product
        }

        fun loadFromEvents(productId: UUID): Product {
            val snapshot = eventStore.getSnapshot(productId)
            val events = eventStore.getEvents(
                streamId = productId,
                fromSequence = snapshot?.sequence ?: 0
            )

            var product = snapshot?.state ?: Product.createNew()
            events.forEach { event -> product = product.apply(event) }
            return product
        }
    }

3.3 Projection 관리:
  - Event Handler가 Read Model 업데이트
  - Projection 재구성 (Rebuild)
  - Projection 버전 관리

  Projection Rebuild:
    @Service
    class ProductReadModelRebuilder(
        private val eventStore: EventStore,
        private val productReadRepo: ProductReadRepository
    ) {
        suspend fun rebuild() {
            productReadRepo.deleteAll()

            eventStore.getAllEvents("Product")
                .collect { event ->
                    when (event.type) {
                        "ProductRegistered" -> handleRegistered(event)
                        "ProductUpdated" -> handleUpdated(event)
                        // ...
                    }
                }
        }
    }

3.4 시계열 분석:
  - 특정 시점의 상태 조회
  - 이벤트 스트림 분석
  - 비즈니스 인사이트 도출

  예시:
    // 2024-01-01 시점의 상품 상태
    fun getProductAt(productId: UUID, timestamp: LocalDateTime): Product {
        val events = eventStore.getEvents(
            streamId = productId,
            until = timestamp
        )
        return events.fold(Product.empty()) { state, event ->
            state.apply(event)
        }
    }

    // 가격 변동 히스토리
    fun getPriceHistory(productId: UUID): List<PriceChange> {
        return eventStore.getEvents(productId)
            .filterIsInstance<ProductUpdatedEvent>()
            .filter { it.priceChanged }
            .map { PriceChange(it.newPrice, it.occurredAt) }
    }
```

#### 성공 지표
```yaml
- 이벤트 저장 성공률: 100%
- Aggregate 재구성 시간: < 100ms (100개 이벤트 기준)
- Snapshot 생성 주기: 10개 이벤트마다
- Projection 재구성: 30분 이내 완료
```

---

### Phase 4: 검색 엔진 통합 (2주)

#### 목표
- ElasticSearch를 통한 고성능 검색
- 전문 검색 (Full-text Search)
- 실시간 검색 제안 (Auto-complete)

#### 작업 항목

```yaml
4.1 ElasticSearch 설정:
  - Cluster 구성 (3 nodes)
  - Index 설계
  - Analyzer 설정 (한글 형태소 분석)

  Index Mapping:
    PUT /products
    {
      "settings": {
        "number_of_shards": 5,
        "number_of_replicas": 2,
        "analysis": {
          "analyzer": {
            "korean": {
              "type": "custom",
              "tokenizer": "nori_tokenizer",
              "filter": ["lowercase", "nori_readingform"]
            }
          }
        }
      },
      "mappings": {
        "properties": {
          "id": { "type": "keyword" },
          "storeName": {
            "type": "text",
            "analyzer": "korean",
            "fields": {
              "keyword": { "type": "keyword" }
            }
          },
          "productName": {
            "type": "text",
            "analyzer": "korean",
            "fields": {
              "suggest": { "type": "completion" }
            }
          },
          "categoryPath": { "type": "keyword" },
          "price": { "type": "long" },
          "description": { "type": "text", "analyzer": "korean" },
          "tags": { "type": "keyword" },
          "createdAt": { "type": "date" }
        }
      }
    }

4.2 Event → ElasticSearch 동기화:
  - Kafka Consumer가 ElasticSearch 업데이트
  - Bulk Indexing으로 성능 최적화
  - Retry 및 Dead Letter Queue

  코드 예시:
    @Component
    class ProductSearchIndexer(
        private val esClient: RestHighLevelClient
    ) {
        @KafkaListener(topics = ["product.events"])
        fun indexEvent(event: ProductEvent) {
            when (event) {
                is ProductRegisteredEvent -> {
                    val request = IndexRequest("products")
                        .id(event.productId.toString())
                        .source(mapOf(
                            "storeName" to event.storeName,
                            "productName" to event.name,
                            "price" to event.price,
                            // ...
                        ))
                    esClient.index(request, RequestOptions.DEFAULT)
                }
                // ...
            }
        }
    }

4.3 검색 API 구현:
  - 전문 검색 (Full-text)
  - Fuzzy 검색 (오타 허용)
  - Auto-complete
  - Faceted Search (카테고리별 필터링)

  API 예시:
    GET /api/v1/products/search?q=무선+마우스

    ElasticSearch Query:
    {
      "query": {
        "multi_match": {
          "query": "무선 마우스",
          "fields": ["productName^3", "description"],
          "fuzziness": "AUTO"
        }
      },
      "aggs": {
        "categories": {
          "terms": { "field": "categoryPath" }
        },
        "price_ranges": {
          "range": {
            "field": "price",
            "ranges": [
              { "to": 10000 },
              { "from": 10000, "to": 50000 },
              { "from": 50000 }
            ]
          }
        }
      }
    }

4.4 성능 최적화:
  - 검색 결과 캐싱 (Redis)
  - Pagination with Search After
  - Highlight 적용

  코드 예시:
    @Service
    class ProductSearchService(
        private val esClient: RestHighLevelClient,
        private val cacheManager: CacheManager
    ) {
        @Cacheable("product-search", key = "#query.hashCode()")
        fun search(query: String, filters: SearchFilters): SearchResult {
            val searchRequest = buildSearchRequest(query, filters)
            val response = esClient.search(searchRequest, RequestOptions.DEFAULT)
            return parseResponse(response)
        }
    }
```

#### 성공 지표
```yaml
- 검색 응답 시간: < 50ms (P95)
- 인덱싱 지연: < 1초
- 검색 정확도: > 90% (Precision@10)
- Auto-complete 응답: < 30ms
```

---

## 5. 기술 스택 및 인프라

### 5.1 백엔드 스택

```yaml
언어 및 프레임워크:
  - Kotlin 2.2.20
  - Spring Boot 3.3.4
  - Spring Data JPA
  - Spring Kafka
  - Coroutines (비동기 처리)

데이터베이스:
  Write DB:
    - PostgreSQL 17
    - Primary: 쓰기 전용
    - 정규화된 트랜잭션 모델

  Read DB:
    - PostgreSQL 17 (Read Replica)
    - 비정규화된 조회 모델
    - 인덱스 최적화

  검색 엔진:
    - ElasticSearch 8.x
    - Nori (한글 형태소 분석기)

메시지 큐:
  - Apache Kafka 3.x
  - Schema Registry (Avro)
  - Zookeeper

캐시:
  - Redis 7.x
  - Idempotency Key 저장
  - 검색 결과 캐싱

모니터링:
  - Prometheus (메트릭 수집)
  - Grafana (시각화)
  - Loki (로그 수집)
  - Jaeger (분산 추적)
```

### 5.2 인프라 아키텍처

```yaml
Kubernetes 기반:
  - Deployment: 3 replicas
  - HPA: CPU 70% 기준 Auto-scaling
  - Service Mesh: Istio
  - Ingress: Nginx

Database:
  Write DB:
    - PostgreSQL 1 instance (Primary만)
    - Standby for HA (선택)

  Read DB:
    - PostgreSQL 1 instance (별도 서버)
    - 필요시 Read Replicas 추가 (수평 확장)

  Connection Pool: HikariCP

Kafka:
  - 3 Brokers
  - Replication Factor: 3
  - Min In-Sync Replicas: 2

ElasticSearch:
  - 3 Nodes (Master-eligible)
  - Index Shards: 5
  - Replicas: 2

Redis:
  - Sentinel 모드 (HA)
  - 3 Nodes (1 Master, 2 Replicas)
```

---

## 6. 데이터 모델 설계

### 6.1 Write Model (정규화)

```sql
-- 기존 트랜잭션 모델 유지
CREATE TABLE p_product (
    id UUID PRIMARY KEY,
    store_id UUID NOT NULL,
    store_name TEXT NOT NULL,
    category_id UUID NOT NULL,
    product_name TEXT NOT NULL,
    status TEXT NOT NULL,
    price NUMERIC(12,2) NOT NULL,
    stock_quantity INT NOT NULL,
    thumbnail_url TEXT,
    description TEXT,
    hidden_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at TIMESTAMPTZ
);

CREATE TABLE p_product_image (
    id UUID PRIMARY KEY,
    product_id UUID NOT NULL REFERENCES p_product(id),
    image_type TEXT NOT NULL,
    image_url TEXT NOT NULL,
    display_order INT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at TIMESTAMPTZ
);
```

### 6.2 Read Model (비정규화)

```sql
-- 조회 최적화 모델
CREATE TABLE product_read (
    id UUID PRIMARY KEY,

    -- 기본 정보 (비정규화)
    store_id UUID NOT NULL,
    store_name TEXT NOT NULL,
    store_rating NUMERIC(3,2),           -- 스토어 평점 (비정규화)

    -- 카테고리 (비정규화)
    category_id UUID NOT NULL,
    category_path TEXT NOT NULL,          -- "전자제품 > 모바일 > 스마트폰"
    category_depth INT NOT NULL,

    -- 상품 정보
    product_name TEXT NOT NULL,
    status TEXT NOT NULL,
    price NUMERIC(12,2) NOT NULL,
    original_price NUMERIC(12,2),        -- 할인 전 가격
    discount_rate INT,                   -- 할인율 (%)
    stock_quantity INT NOT NULL,

    -- 미디어 (비정규화)
    thumbnail_url TEXT,
    primary_image_url TEXT,
    detail_image_urls TEXT[],            -- 배열로 저장

    -- 설명
    description TEXT,
    short_description TEXT,              -- 요약 (검색용)

    -- 검색 최적화
    search_keywords TEXT[],              -- 검색 키워드 배열
    tags TEXT[],                         -- 태그

    -- 통계 (비정규화)
    view_count BIGINT DEFAULT 0,
    order_count INT DEFAULT 0,
    review_count INT DEFAULT 0,
    average_rating NUMERIC(3,2),

    -- 상태
    is_new BOOLEAN DEFAULT false,        -- 신상품 여부
    is_best BOOLEAN DEFAULT false,       -- 베스트 상품 여부
    is_sale BOOLEAN DEFAULT false,       -- 할인 중 여부
    hidden_at TIMESTAMPTZ,

    -- 시간
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    last_ordered_at TIMESTAMPTZ,

    -- 인덱스
    INDEX idx_product_read_store ON product_read (store_id),
    INDEX idx_product_read_category ON product_read (category_id),
    INDEX idx_product_read_name_gin ON product_read USING GIN (product_name gin_trgm_ops),
    INDEX idx_product_read_price ON product_read (price),
    INDEX idx_product_read_created ON product_read (created_at DESC),
    INDEX idx_product_read_popularity ON product_read (order_count DESC, view_count DESC),
    INDEX idx_product_read_rating ON product_read (average_rating DESC NULLS LAST),
    INDEX idx_product_read_keywords ON product_read USING GIN (search_keywords),
    INDEX idx_product_read_tags ON product_read USING GIN (tags),
    INDEX idx_product_read_status ON product_read (status, hidden_at) WHERE hidden_at IS NULL
);

-- 검색 히스토리 (인기 검색어)
CREATE TABLE product_search_log (
    id BIGSERIAL PRIMARY KEY,
    user_id UUID,
    search_query TEXT NOT NULL,
    result_count INT NOT NULL,
    clicked_product_id UUID,
    searched_at TIMESTAMPTZ DEFAULT now(),

    INDEX idx_search_log_query ON product_search_log (search_query),
    INDEX idx_search_log_time ON product_search_log (searched_at DESC)
);
```

### 6.3 Event Store (Event Sourcing)

```sql
CREATE TABLE event_store (
    id BIGSERIAL PRIMARY KEY,

    -- Stream 정보
    stream_id UUID NOT NULL,             -- Aggregate ID (Product ID)
    stream_type TEXT NOT NULL,           -- "Product"

    -- Event 정보
    event_id UUID NOT NULL UNIQUE,       -- Event 고유 ID
    event_type TEXT NOT NULL,            -- "ProductRegistered", "ProductUpdated", etc.
    event_version INT NOT NULL DEFAULT 1,-- Event 스키마 버전

    -- Event 데이터
    event_data JSONB NOT NULL,           -- Event payload
    metadata JSONB,                      -- { userId, ip, userAgent, ... }

    -- 순서 보장
    sequence_number BIGINT NOT NULL,     -- Stream 내 순서
    global_sequence BIGSERIAL,           -- 전역 순서

    -- 시간
    occurred_at TIMESTAMPTZ DEFAULT now(),

    -- 제약 조건
    UNIQUE(stream_id, sequence_number),
    INDEX idx_event_store_stream ON event_store (stream_id, sequence_number),
    INDEX idx_event_store_type ON event_store (event_type),
    INDEX idx_event_store_global ON event_store (global_sequence),
    INDEX idx_event_store_occurred ON event_store (occurred_at DESC)
);

-- Snapshot (성능 최적화)
CREATE TABLE event_snapshots (
    stream_id UUID PRIMARY KEY,
    stream_type TEXT NOT NULL,
    sequence_number BIGINT NOT NULL,     -- 어느 시점의 스냅샷인지
    state JSONB NOT NULL,                -- Aggregate 상태
    created_at TIMESTAMPTZ DEFAULT now(),

    INDEX idx_snapshot_created ON event_snapshots (created_at DESC)
);

-- Outbox Pattern (이벤트 발행 보장)
CREATE TABLE outbox_events (
    id UUID PRIMARY KEY,
    aggregate_type TEXT NOT NULL,
    aggregate_id UUID NOT NULL,
    event_type TEXT NOT NULL,
    event_data JSONB NOT NULL,
    created_at TIMESTAMPTZ DEFAULT now(),
    published_at TIMESTAMPTZ,
    retry_count INT DEFAULT 0,

    INDEX idx_outbox_unpublished ON outbox_events (created_at)
    WHERE published_at IS NULL
);
```

---

## 7. 이벤트 설계

### 7.1 도메인 이벤트 정의

```kotlin
sealed interface ProductEvent : DomainEvent {
    val productId: UUID
    val occurredAt: LocalDateTime
    val version: Int  // Event 스키마 버전
}

// 1. 상품 등록
data class ProductRegisteredEvent(
    override val productId: UUID,
    val storeId: UUID,
    val storeName: String,
    val categoryId: UUID,
    val name: String,
    val price: BigDecimal,
    val stockQuantity: Int,
    val thumbnailUrl: String?,
    val description: String?,
    val images: List<ProductImageDto>,
    val actorUserId: UUID,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
    override val version: Int = 1
) : ProductEvent

// 2. 상품 정보 수정
data class ProductInfoUpdatedEvent(
    override val productId: UUID,
    val changes: Map<String, Change<Any>>,  // field -> Change(old, new)
    val actorUserId: UUID,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
    override val version: Int = 1
) : ProductEvent

data class Change<T>(
    val oldValue: T?,
    val newValue: T
)

// 3. 상품 삭제
data class ProductDeletedEvent(
    override val productId: UUID,
    val reason: String?,
    val actorUserId: UUID,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
    override val version: Int = 1
) : ProductEvent

// 4. 상품 숨김
data class ProductHiddenEvent(
    override val productId: UUID,
    val actorUserId: UUID,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
    override val version: Int = 1
) : ProductEvent

// 5. 상품 복원
data class ProductRestoredEvent(
    override val productId: UUID,
    val actorUserId: UUID,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
    override val version: Int = 1
) : ProductEvent

// 6. 재고 변경 (새로 추가)
data class ProductStockChangedEvent(
    override val productId: UUID,
    val previousStock: Int,
    val newStock: Int,
    val changeReason: StockChangeReason,  // ORDER, RETURN, MANUAL_ADJUSTMENT
    val relatedOrderId: UUID?,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
    override val version: Int = 1
) : ProductEvent

enum class StockChangeReason {
    ORDER,              // 주문으로 인한 감소
    RETURN,             // 반품으로 인한 증가
    MANUAL_ADJUSTMENT   // 수동 조정
}
```

### 7.2 Avro 스키마 (Kafka)

```json
{
  "type": "record",
  "name": "ProductRegisteredEvent",
  "namespace": "com.groom.product.event",
  "fields": [
    {"name": "productId", "type": "string"},
    {"name": "storeId", "type": "string"},
    {"name": "storeName", "type": "string"},
    {"name": "categoryId", "type": "string"},
    {"name": "name", "type": "string"},
    {"name": "price", "type": "string"},
    {"name": "stockQuantity", "type": "int"},
    {"name": "thumbnailUrl", "type": ["null", "string"], "default": null},
    {"name": "description", "type": ["null", "string"], "default": null},
    {
      "name": "images",
      "type": {
        "type": "array",
        "items": {
          "type": "record",
          "name": "ProductImage",
          "fields": [
            {"name": "imageType", "type": "string"},
            {"name": "imageUrl", "type": "string"},
            {"name": "displayOrder", "type": "int"}
          ]
        }
      }
    },
    {"name": "actorUserId", "type": "string"},
    {"name": "occurredAt", "type": "long", "logicalType": "timestamp-millis"},
    {"name": "version", "type": "int", "default": 1}
  ]
}
```

### 7.3 Event Versioning 전략

```kotlin
// Version 1 → Version 2 마이그레이션
object ProductEventMigration {
    fun migrate(event: Map<String, Any>): Map<String, Any> {
        val version = event["version"] as Int

        return when (version) {
            1 -> migrateV1toV2(event)
            2 -> event  // 최신 버전
            else -> throw UnsupportedEventVersionException(version)
        }
    }

    private fun migrateV1toV2(event: Map<String, Any>): Map<String, Any> {
        // V1에는 없던 필드를 V2에 추가
        return event + mapOf(
            "metadata" to emptyMap<String, Any>(),
            "version" to 2
        )
    }
}

// Event Handler는 여러 버전 처리
@Component
class ProductEventHandler {
    @KafkaListener(topics = ["product.events"])
    fun handle(rawEvent: Map<String, Any>) {
        val migratedEvent = ProductEventMigration.migrate(rawEvent)

        when (migratedEvent["type"]) {
            "ProductRegistered" -> handleRegistered(migratedEvent)
            // ...
        }
    }
}
```

---

## 8. 성능 및 확장성 고려사항

### 8.1 성능 목표

```yaml
응답 시간 (P95):
  - Command API: < 200ms (비동기는 즉시 202 Accepted)
  - Query API (간단): < 50ms
  - Query API (복잡): < 200ms
  - 검색 API: < 100ms

처리량:
  - Command: 1,000 TPS
  - Query: 10,000 TPS
  - Event Processing: 5,000 events/sec

가용성:
  - SLA: 99.9% (연간 다운타임 < 8.76시간)
  - RPO (Recovery Point Objective): < 1분
  - RTO (Recovery Time Objective): < 5분
```

### 8.2 확장 전략

#### Horizontal Scaling
```yaml
Application:
  - Stateless 설계
  - Kubernetes HPA (CPU 기반)
  - Min Replicas: 3
  - Max Replicas: 20

Database:
  Write DB:
    - Vertical Scaling (CPU, Memory 증가)
    - Standby 추가 (HA 목적)
    - Sharding (Store ID 기준, 향후)

  Read DB:
    - Read Replicas 추가 (수평 확장, 최대 10개)
    - Read DB 자체를 복제하여 부하 분산
    - Connection Pooling (HikariCP)

Kafka:
  - Partition 증가 (초기 10 → 최대 50)
  - Consumer Group 병렬 처리

ElasticSearch:
  - Index Sharding 증가
  - Node 추가 (수평 확장)
```

#### Vertical Scaling
```yaml
Database:
  - Primary: 16 vCPU, 64GB RAM
  - Replica: 8 vCPU, 32GB RAM

Kafka:
  - Broker: 8 vCPU, 32GB RAM

ElasticSearch:
  - Node: 8 vCPU, 32GB RAM
```

### 8.3 캐싱 전략

```yaml
Redis 캐싱:
  1. Idempotency Key:
     - TTL: 24시간
     - Key: "command:{commandId}"

  2. 검색 결과:
     - TTL: 5분
     - Key: "search:{queryHash}"
     - LRU Eviction

  3. 상품 상세:
     - TTL: 10분
     - Key: "product:{productId}"
     - Write-through 전략

CDN 캐싱:
  - 상품 이미지: CloudFront
  - TTL: 7일
  - Invalidation on update
```

---

## 9. 리스크 및 대응방안

### 9.1 주요 리스크

```yaml
1. Eventual Consistency 혼란:
   문제: 사용자가 등록 후 즉시 조회 시 데이터 없음
   대응:
     - 등록 응답에 "처리 중" 상태 포함
     - WebSocket으로 완료 알림
     - UI에 "최근 등록" 섹션 분리
     - SLA 명시: "최대 5초 이내 반영"

2. Event 유실:
   문제: Kafka 장애로 이벤트 유실
   대응:
     - Transactional Outbox Pattern
     - At-least-once delivery 보장
     - Dead Letter Queue
     - Event Replay 기능

3. Read Model 동기화 실패:
   문제: Event Handler 버그로 Read Model 손상
   대응:
     - Read Model Rebuild 기능
     - Event Store에서 재구성
     - Blue-Green Deployment
     - Canary Release (1% → 10% → 100%)

4. 성능 저하:
   문제: Event 처리 지연으로 Read Model 오래된 데이터
   대응:
     - Monitoring: Event Lag 추적
     - Alert: 5초 이상 지연 시 알림
     - Auto-scaling: Consumer 증가
     - Circuit Breaker: 실패 시 차단

5. 데이터 불일치:
   문제: Write DB와 Read DB 데이터 차이
   대응:
     - Reconciliation Job (매 1시간)
     - 차이 발견 시 자동 수정
     - Audit Log 비교
     - Manual Sync API 제공
```

### 9.2 장애 복구 시나리오

```yaml
시나리오 1: Kafka 다운
  1. Outbox 패턴으로 이벤트 임시 저장
  2. Kafka 복구 시 Outbox에서 재발행
  3. 수동 개입 없이 자동 복구

시나리오 2: Read DB 손상
  1. Read DB를 ReadOnly 모드 전환
  2. Write DB에서 임시 조회 제공 (성능 저하)
  3. Read Model Rebuild 실행
  4. 검증 후 트래픽 전환

시나리오 3: ElasticSearch 다운
  1. PostgreSQL Read DB로 Fallback
  2. 검색 성능 저하 (사용자 안내)
  3. ElasticSearch 복구 시 자동 재인덱싱

시나리오 4: Write DB 다운
  1. Command를 Kafka에만 발행 (보관)
  2. Write DB 복구 시 Command Replay
  3. Idempotency로 중복 처리 방지
```

---

## 10. 마이그레이션 전략

### 10.1 마이그레이션 단계

```yaml
Phase 0: 준비 (1주)
  - Kafka, Redis 인프라 구축
  - Read Replica 생성
  - Monitoring 설정
  - Feature Flag 준비

Phase 1: Read Model 분리 (2주)
  - Read DB 테이블 생성
  - Event Handler 구현
  - 데이터 초기 동기화
  - Canary Release (1% 트래픽)

  Rollback Plan:
    - Feature Flag OFF
    - 기존 코드로 즉시 복귀

Phase 2: Command Bus 도입 (2주)
  - Kafka Topic 생성
  - Command Handler 구현
  - Outbox Pattern 적용
  - Canary Release (10% 트래픽)

  Rollback Plan:
    - Command를 동기 처리로 전환
    - Kafka 우회

Phase 3: 전체 전환 (1주)
  - 100% 트래픽 전환
  - 기존 코드 제거
  - 성능 튜닝
  - 문서화

Phase 4: 최적화 (계속)
  - ElasticSearch 통합
  - Event Sourcing 도입
  - 추가 최적화
```

### 10.2 초기 데이터 마이그레이션

```kotlin
@Service
class InitialDataMigration(
    private val productRepository: ProductJpaRepository,
    private val productReadRepository: ProductReadRepository,
    private val categoryPathBuilder: CategoryPathBuilder
) {
    suspend fun migrate() {
        log.info("Starting initial data migration...")

        val totalProducts = productRepository.count()
        val batchSize = 1000
        var processedCount = 0

        productRepository.findAll(PageRequest.of(0, batchSize))
            .asFlow()
            .chunked(100)  // Batch 처리
            .collect { batch ->
                val readModels = batch.map { product ->
                    ProductRead(
                        id = product.id,
                        storeName = product.storeName,
                        categoryPath = categoryPathBuilder.build(product.categoryId),
                        productName = product.name,
                        price = product.price,
                        // ... 모든 필드 매핑
                    )
                }

                productReadRepository.saveAll(readModels)
                processedCount += batch.size

                log.info("Migrated $processedCount / $totalProducts products")
            }

        log.info("Migration completed!")
    }
}
```

### 10.3 Feature Flag 전략

```kotlin
@Configuration
class FeatureFlags {
    @Value("\${feature.cqrs.enabled:false}")
    var cqrsEnabled: Boolean = false

    @Value("\${feature.read-model.enabled:false}")
    var readModelEnabled: Boolean = false

    @Value("\${feature.async-commands.enabled:false}")
    var asyncCommandsEnabled: Boolean = false
}

@Service
class SearchProductsService(
    private val featureFlags: FeatureFlags,
    private val productRepository: ProductJpaRepository,
    private val productReadRepository: ProductReadRepository
) {
    fun search(query: SearchProductsQuery): SearchProductsResult {
        return if (featureFlags.readModelEnabled) {
            searchFromReadModel(query)
        } else {
            searchFromWriteModel(query)
        }
    }

    private fun searchFromReadModel(query: SearchProductsQuery): SearchProductsResult {
        // Read Model 조회
    }

    private fun searchFromWriteModel(query: SearchProductsQuery): SearchProductsResult {
        // 기존 방식 (Fallback)
    }
}
```

---

## 부록

### A. CQRS vs Primary-Replica 비교

```yaml
Primary-Replica 패턴 (기존):
  목적: 읽기 부하 분산
  구조:
    - Primary DB: Write + Read
    - Replica DB: Read only
  동기화: PostgreSQL 물리적 복제
  스키마: 동일
  데이터: 동일 (복제본)
  장점:
    - 설정 간단
    - 즉시 일관성
  단점:
    - 같은 스키마 제약
    - Write 락이 Read에 영향
    - 최적화 전략 제한

CQRS 패턴 (적용 후):
  목적: Command/Query 책임 분리
  구조:
    - Write DB: Command 전용
    - Read DB: Query 전용 (완전히 별개)
  동기화: Kafka Event
  스키마: 완전히 다름
    - Write: 정규화 (3NF)
    - Read: 비정규화 (성능 최적화)
  데이터: 다름
    - Write: 트랜잭션 모델
    - Read: 조회 최적화 모델
  장점:
    - 독립적 최적화
    - 확장성 우수
    - Write/Read 완전 분리
  단점:
    - 복잡도 증가
    - Eventual Consistency

언제 Primary-Replica를 사용하나?
  1. CQRS 미적용 단일 DB 환경
  2. Write DB의 HA (Standby)
  3. Read DB의 수평 확장 (Read DB → Read Replicas)

CQRS에서 Primary-Replica가 필요한 경우:
  - Write DB: Primary + Standby (HA 목적)
  - Read DB: Primary + Replicas (부하 분산)
  - 하지만 Write DB ↔ Read DB 간에는 복제 없음!
```

### B. 용어 사전

```yaml
CQRS: Command Query Responsibility Segregation
  - 명령과 조회의 책임 분리 패턴

Event Sourcing:
  - 상태 변경을 이벤트로 저장하는 패턴
  - 모든 변경 히스토리 보존

Eventual Consistency:
  - 최종적으로 일관성이 보장되는 모델
  - 즉시 일관성을 보장하지 않음

Aggregate:
  - DDD에서 일관성 경계를 나타내는 단위
  - Product가 Aggregate Root

Projection:
  - 이벤트로부터 Read Model을 구성하는 과정
  - Event Handler가 Projection 수행

Outbox Pattern:
  - 트랜잭션과 이벤트 발행을 원자적으로 처리
  - DB 트랜잭션과 함께 Outbox 테이블에 저장

Idempotency:
  - 동일한 작업을 여러 번 실행해도 결과가 같음
  - 중복 처리 방지
```

### B. 참고 자료

```yaml
서적:
  - "Implementing Domain-Driven Design" - Vaughn Vernon
  - "Event Sourcing and CQRS" - Greg Young
  - "Microservices Patterns" - Chris Richardson

아티클:
  - Martin Fowler - CQRS
  - Microsoft - CQRS Pattern
  - AWS - Event-driven Architecture

오픈소스:
  - Axon Framework (Java CQRS/ES)
  - EventStore (Event Sourcing DB)
  - Kafka Streams (Event Processing)
```

### C. 체크리스트

```yaml
Phase 1 완료 기준:
  ☐ Read DB 스키마 생성 완료
  ☐ Event Handler 5개 구현
  ☐ Integration Test 작성
  ☐ Canary Release 성공 (1%)
  ☐ Monitoring Dashboard 구축
  ☐ Rollback Plan 문서화

Phase 2 완료 기준:
  ☐ Kafka Topic 생성
  ☐ Command Handler 구현
  ☐ Outbox Pattern 적용
  ☐ Idempotency 구현
  ☐ Performance Test 통과
  ☐ Canary Release 성공 (10%)

Phase 3 완료 기준:
  ☐ 100% 트래픽 전환
  ☐ 기존 코드 제거
  ☐ 성능 목표 달성
  ☐ 운영 문서 작성
  ☐ 팀 교육 완료
```

---

## 문서 이력

| 버전 | 날짜 | 작성자 | 변경 내용 |
|------|------|--------|-----------|
| 1.0 | 2025-11-18 | Claude Code | 초안 작성 |

---

**END OF DOCUMENT**
