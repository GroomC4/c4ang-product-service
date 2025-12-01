# Product Service

C4ang 이커머스 플랫폼의 **상품 관리 마이크로서비스**입니다.

## 서비스 책임

- 상품 등록, 조회, 수정, 삭제 (CRUD)
- 상품 검색 (상품명, 스토어명, 가격 범위, 유사도 검색)
- 상품 상태 관리 (ON_SALE, SOLD_OUT)
- 상품 숨김/노출 처리
- 상품 이미지 관리 (S3 업로드)
- 상품 설명 AI 자동 생성 (Google Gemini)
- 카테고리 관리 및 카테고리 경로 조회
- 도메인 이벤트 발행 (Kafka)

## 기술 스택

| 구분 | 기술 |
|------|------|
| Language | Kotlin 2.0, JDK 21 |
| Framework | Spring Boot 3.3 |
| Database | PostgreSQL (Primary-Replica) |
| Cache | Redis (Redisson) |
| Message Broker | Apache Kafka |
| Object Storage | AWS S3 |
| AI | Google Gemini API |
| API Documentation | SpringDoc OpenAPI |
| Build Tool | Gradle 8.x (Kotlin DSL) |
| Container | Docker |

## 프로젝트 구조

Hexagonal Architecture (Ports and Adapters) 패턴을 따릅니다.

```
product-api/
└── src/main/kotlin/com/groom/product/
    ├── adapter/                    # 외부 시스템 연동
    │   ├── inbound/web/           # REST API Controller
    │   └── outbound/
    │       ├── persistence/       # JPA Repository
    │       ├── client/            # Feign Client (store-service)
    │       ├── storage/           # S3 Uploader
    │       └── ai/                # Gemini AI Client
    │
    ├── application/               # 애플리케이션 서비스
    │   ├── service/               # Use Case 구현
    │   ├── dto/                   # Command, Query, Result DTO
    │   └── event/                 # 도메인 이벤트 핸들러
    │
    ├── domain/                    # 도메인 계층 (비즈니스 로직)
    │   ├── model/                 # Entity, Value Object
    │   ├── service/               # 도메인 서비스
    │   ├── port/                  # 포트 인터페이스
    │   └── event/                 # 도메인 이벤트
    │
    ├── common/                    # 공통 유틸리티
    │   ├── exception/             # 예외 처리
    │   ├── configuration/         # 설정 프로퍼티
    │   └── util/                  # 유틸리티
    │
    └── configuration/             # 설정 클래스
```

## API 엔드포인트

| Method | Endpoint | 설명 |
|--------|----------|------|
| POST | `/api/v1/products` | 상품 등록 |
| GET | `/api/v1/products/{id}` | 상품 상세 조회 |
| GET | `/api/v1/products` | 상품 목록 검색 |
| GET | `/api/v1/products/owner` | 내 상품 목록 조회 |
| PATCH | `/api/v1/products/{id}` | 상품 수정 |
| DELETE | `/api/v1/products/{id}` | 상품 삭제 |
| PATCH | `/api/v1/products/{id}/hide` | 상품 숨김/노출 토글 |
| POST | `/api/v1/products/images` | 상품 이미지 업로드 |
| POST | `/api/v1/products/description/generate` | AI 상품 설명 생성 |

## 발행 이벤트 (Kafka)

| 이벤트 | Topic | 설명 |
|--------|-------|------|
| ProductRegisteredEvent | `product.registered` | 상품 등록 시 |
| ProductInfoUpdatedEvent | `product.info.updated` | 상품 정보 수정 시 |
| ProductDeletedEvent | `product.deleted` | 상품 삭제 시 |
| ProductHiddenEvent | `product.hidden` | 상품 숨김 처리 시 |
| ProductRestoredEvent | `product.restored` | 상품 노출 복원 시 |

## 외부 서비스 의존성

| 서비스 | 용도 |
|--------|------|
| store-service | 스토어 정보 조회 (소유자 검증) |
| AWS S3 | 상품 이미지 저장 |
| Google Gemini | AI 상품 설명 생성 |

## 환경 설정

### 프로필

| 프로필 | 용도 | 설명 |
|--------|------|------|
| (default) | 로컬 개발 | Docker Compose로 인프라 실행 |
| test | 테스트 | Testcontainers 사용 |
| dev | k3d 개발환경 | Kubernetes 서비스 이름 사용 |
| prod | 운영환경 | 환경변수 기반 설정 |

### 주요 환경변수 (prod)

```
DB_MASTER_URL, DB_REPLICA_URL, DB_USERNAME, DB_PASSWORD
REDIS_HOST, REDIS_PORT, REDIS_PASSWORD
KAFKA_BOOTSTRAP_SERVERS
STORE_SERVICE_URL
AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY, AWS_S3_BUCKET
GEMINI_API_KEY
```

## 빌드 및 실행

```bash
# 빌드 (테스트 포함)
./gradlew build

# 빌드 (테스트 제외)
./gradlew build -x test

# 단위 테스트
./gradlew :product-api:test

# 통합 테스트 (Testcontainers)
./gradlew :product-api:integrationTest

# Contract 테스트
./gradlew :product-api:contractTest

# 로컬 실행 (Docker Compose 자동 시작)
./gradlew bootRun
```

## Docker

```bash
# 빌드
docker build \
  --build-arg GITHUB_ACTOR=<username> \
  --build-arg GITHUB_TOKEN=<token> \
  -t product-service .

# 실행
docker run -p 8083:8083 product-service
```

## Contract 테스트

### Provider Contract (제공하는 API)

- Spring Cloud Contract를 사용하여 API 계약 검증
- Contract 파일: `product-api/src/test/resources/contracts/`
- Stub 발행: GitHub Packages (`com.groom:product-service-contract-stubs`)

### Consumer Contract (소비하는 API)

- store-service의 스토어 조회 API 계약 검증
- Stub Runner를 통한 Consumer Contract Test

```bash
# Contract Stub 발행
./gradlew :product-api:publish
```

## 관련 저장소

- [c4ang-store-service](https://github.com/GroomC4/c4ang-store-service) - 스토어 서비스
- [c4ang-customer-service](https://github.com/GroomC4/c4ang-customer-service) - 고객 서비스
- [c4ang-platform-core](https://github.com/GroomC4/c4ang-platform-core) - 공통 플랫폼 라이브러리
