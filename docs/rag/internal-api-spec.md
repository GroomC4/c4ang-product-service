# RAG 서비스용 Internal API 명세

> **Base URL**: `/internal/v1/rag/perfumes`
> **버전**: v2
> **최종 수정**: 2024-12-08

---

## 개요

RAG(챗봇) 서비스에서 향수 정보를 조회하기 위한 Internal API입니다.

### 특징
- 향수(Perfume) 전용 API
- 노트명 자동 한국어 변환 (NoteTranslator)
- Consumer-Driven Contract 패턴 적용

---

## API 목록

| 메서드 | 경로 | 설명 |
|--------|------|------|
| POST | `/search` | 향수 검색 |
| GET | `/{id}` | 향수 상세 조회 |
| POST | `/compare` | 향수 비교 (2~4개) |

---

## 1. 향수 검색

조건에 맞는 향수 목록을 검색합니다.

### 요청

```
POST /internal/v1/rag/perfumes/search
Content-Type: application/json
```

#### Request Body

| 필드 | 타입 | 필수 | 설명 |
|------|------|------|------|
| `productIds` | `array[UUID]` | ❌ | 특정 향수 ID 목록 (제공 시 다른 필터 무시) |
| `brand` | `string` | ❌ | 브랜드명 필터 |
| `gender` | `string` | ❌ | 성별 필터 (`Male`, `Female`, `Unisex`) |
| `query` | `string` | ❌ | 검색 쿼리 (미구현) |
| `scentFamily` | `string` | ❌ | 향조 필터 (미구현) |
| `season` | `string` | ❌ | 계절 필터 (미구현) |
| `priceMin` | `number` | ❌ | 최소 가격 (미구현) |
| `priceMax` | `number` | ❌ | 최대 가격 (미구현) |

> **Note**: `productIds`가 제공되면 다른 필터는 무시됩니다. RAG에서 벡터DB로 유사 향수 ID를 찾은 후, 이 파라미터로 상세 정보를 조회합니다.

#### 요청 예시

```json
{
  "productIds": [
    "550e8400-e29b-41d4-a716-446655440001",
    "550e8400-e29b-41d4-a716-446655440002"
  ]
}
```

```json
{
  "brand": "Chanel",
  "gender": "Female"
}
```

### 응답

#### Response Body

| 필드 | 타입 | 설명 |
|------|------|------|
| `totalCount` | `integer` | 검색 결과 수 |
| `results` | `array[ProductSummary]` | 검색 결과 목록 |

#### ProductSummary

| 필드 | 타입 | 설명 |
|------|------|------|
| `id` | `string` | 향수 ID (UUID) |
| `brand` | `string` | 브랜드명 |
| `name` | `string` | 향수명 |
| `concentration` | `string` | 농도 (Eau de Parfum, Eau de Toilette 등) |
| `mainAccords` | `array[string]` | 주요 향조 (한국어) |
| `gender` | `string` | 성별 (`Male`, `Female`, `Unisex`) |
| `sizes` | `array[Size]` | 용량별 가격 |

#### Size

| 필드 | 타입 | 설명 |
|------|------|------|
| `sizeMl` | `integer` | 용량 (ml) |
| `price` | `number` | 가격 (원) |

#### 응답 예시

```json
{
  "totalCount": 2,
  "results": [
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "brand": "Chanel",
      "name": "Coco Mademoiselle",
      "concentration": "Eau de Parfum",
      "mainAccords": ["플로랄", "시트러스", "프레시"],
      "gender": "Female",
      "sizes": [
        { "sizeMl": 50, "price": 200000 },
        { "sizeMl": 100, "price": 320000 }
      ]
    },
    {
      "id": "550e8400-e29b-41d4-a716-446655440002",
      "brand": "Chanel",
      "name": "N°5",
      "concentration": "Eau de Parfum",
      "mainAccords": ["플로랄", "알데히드", "파우더리"],
      "gender": "Female",
      "sizes": [
        { "sizeMl": 50, "price": 220000 },
        { "sizeMl": 100, "price": 350000 }
      ]
    }
  ]
}
```

---

## 2. 향수 상세 조회

특정 향수의 상세 정보를 조회합니다.

### 요청

```
GET /internal/v1/rag/perfumes/{id}
```

#### Path Parameters

| 필드 | 타입 | 필수 | 설명 |
|------|------|------|------|
| `id` | `UUID` | ✅ | 향수 ID |

### 응답

#### Response Body

| 필드 | 타입 | 설명 |
|------|------|------|
| `id` | `string` | 향수 ID (UUID) |
| `brand` | `string` | 브랜드명 |
| `name` | `string` | 향수명 |
| `concentration` | `string` | 농도 |
| `mainAccords` | `array[string]` | 주요 향조 (한국어) |
| `topNotes` | `array[string]` | 탑노트 (한국어) |
| `middleNotes` | `array[string]` | 미들노트 (한국어) |
| `baseNotes` | `array[string]` | 베이스노트 (한국어) |
| `gender` | `string` | 성별 |
| `seasonScore` | `SeasonScore` | 계절별 어울림 점수 |
| `dayNightScore` | `DayNightScore` | 주야간 어울림 점수 |
| `sizes` | `array[Size]` | 용량별 가격 |
| `detailUrl` | `string` | 상세 페이지 URL |

#### SeasonScore

| 필드 | 타입 | 설명 |
|------|------|------|
| `spring` | `number` | 봄 점수 (0-100) |
| `summer` | `number` | 여름 점수 (0-100) |
| `fall` | `number` | 가을 점수 (0-100) |
| `winter` | `number` | 겨울 점수 (0-100) |

#### DayNightScore

| 필드 | 타입 | 설명 |
|------|------|------|
| `day` | `number` | 낮 점수 (0-100) |
| `night` | `number` | 밤 점수 (0-100) |

#### 응답 예시

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440001",
  "brand": "Dior",
  "name": "Sauvage",
  "concentration": "Eau de Parfum",
  "mainAccords": ["우디", "아로마틱", "프레시 스파이시"],
  "topNotes": ["베르가못", "페퍼"],
  "middleNotes": ["라벤더", "시추안 페퍼", "제라늄"],
  "baseNotes": ["암브록산", "시더", "라브다넘"],
  "gender": "Male",
  "seasonScore": {
    "spring": 55.0,
    "summer": 50.0,
    "fall": 60.0,
    "winter": 55.0
  },
  "dayNightScore": {
    "day": 80.0,
    "night": 65.0
  },
  "sizes": [
    { "sizeMl": 60, "price": 150000 },
    { "sizeMl": 100, "price": 200000 }
  ],
  "detailUrl": "https://example.com/dior-sauvage"
}
```

### 에러 응답

#### 404 Not Found

향수를 찾을 수 없는 경우:

```json
{
  "error": "PRODUCT_NOT_FOUND",
  "message": "상품을 찾을 수 없습니다",
  "productId": "550e8400-e29b-41d4-a716-446655440999"
}
```

---

## 3. 향수 비교

2~4개의 향수를 비교합니다.

### 요청

```
POST /internal/v1/rag/perfumes/compare
Content-Type: application/json
```

#### Request Body

| 필드 | 타입 | 필수 | 설명 |
|------|------|------|------|
| `productIds` | `array[UUID]` | ✅ | 비교할 향수 ID 목록 (2~4개) |

#### 요청 예시

```json
{
  "productIds": [
    "550e8400-e29b-41d4-a716-446655440001",
    "550e8400-e29b-41d4-a716-446655440002"
  ]
}
```

### 응답

#### Response Body

| 필드 | 타입 | 설명 |
|------|------|------|
| `comparisonCount` | `integer` | 비교된 향수 수 |
| `products` | `array[CompareProduct]` | 비교 대상 향수 목록 |

#### CompareProduct

| 필드 | 타입 | 설명 |
|------|------|------|
| `id` | `string` | 향수 ID (UUID) |
| `brand` | `string` | 브랜드명 |
| `name` | `string` | 향수명 |
| `concentration` | `string` | 농도 |
| `mainAccords` | `array[string]` | 주요 향조 (한국어) |
| `topNotes` | `array[string]` | 탑노트 (한국어) |
| `middleNotes` | `array[string]` | 미들노트 (한국어) |
| `baseNotes` | `array[string]` | 베이스노트 (한국어) |
| `gender` | `string` | 성별 |
| `sizes` | `array[Size]` | 용량별 가격 |

#### 응답 예시

```json
{
  "comparisonCount": 2,
  "products": [
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "brand": "Chanel",
      "name": "Coco Mademoiselle",
      "concentration": "Eau de Parfum",
      "mainAccords": ["플로랄", "시트러스", "프레시"],
      "topNotes": ["오렌지", "베르가못", "그레이프프루트"],
      "middleNotes": ["로즈", "재스민", "리치"],
      "baseNotes": ["바닐라", "머스크", "베티버", "패출리"],
      "gender": "Female",
      "sizes": [
        { "sizeMl": 50, "price": 200000 },
        { "sizeMl": 100, "price": 320000 }
      ]
    },
    {
      "id": "550e8400-e29b-41d4-a716-446655440002",
      "brand": "Dior",
      "name": "Miss Dior",
      "concentration": "Eau de Parfum",
      "mainAccords": ["플로랄", "로즈", "파우더리"],
      "topNotes": ["만다린 오렌지", "베르가못"],
      "middleNotes": ["로즈", "피오니", "은방울꽃"],
      "baseNotes": ["머스크", "샌달우드", "패출리"],
      "gender": "Female",
      "sizes": [
        { "sizeMl": 30, "price": 120000 },
        { "sizeMl": 50, "price": 180000 }
      ]
    }
  ]
}
```

### 에러 응답

#### 400 Bad Request

향수 개수가 2~4개가 아닌 경우:

```json
{
  "error": "BAD_REQUEST",
  "message": "비교할 향수는 2~4개 사이여야 합니다."
}
```

---

## 부록

### A. 성별 (Gender) 값

| 값 | 설명 |
|---|------|
| `Male` | 남성 |
| `Female` | 여성 |
| `Unisex` | 남녀공용 |

### B. 농도 (Concentration) 값

| 값 | 설명 | 지속시간 |
|---|------|---------|
| `Parfum` | 퍼퓸 | 8-12시간 |
| `Eau de Parfum` | 오 드 퍼퓸 | 6-8시간 |
| `Eau de Toilette` | 오 드 뚜왈렛 | 4-6시간 |
| `Eau de Cologne` | 오 드 콜로뉴 | 2-4시간 |

### C. 노트 한국어 변환

API 응답의 노트명(mainAccords, topNotes, middleNotes, baseNotes)은 자동으로 한국어로 변환됩니다.

| 영어 | 한국어 |
|-----|--------|
| Floral | 플로랄 |
| Woody | 우디 |
| Citrus | 시트러스 |
| Fresh | 프레시 |
| Spicy | 스파이시 |
| Rose | 로즈 |
| Jasmine | 재스민 |
| Musk | 머스크 |
| Vanilla | 바닐라 |
| ... | (400개 이상 지원) |

---

## 변경 이력

| 버전 | 날짜 | 변경 내용 |
|-----|------|----------|
| v2 | 2024-12-08 | 초기 릴리스, API 경로 `/rag/perfumes`로 확정 |
