# Product API 명세서 및 챗봇 연동 가이드

## 📋 프로젝트 개요

**저장소**: `github.com/GroomC4/c4ang-product-service`  
**목적**: 챗봇에서 상품 정보를 조회하여 사용자에게 제공  
**아키텍처**: 마이크로서비스 기반 (Kotlin + Spring Boot)  
**제외 기능**: 평점, 리뷰, 주문, 결제

---

## 🎯 챗봇 연동 요구사항

### 1. 사용 시나리오

```
사용자: "노트북 추천해줘"
챗봇: 
  1. Product API에서 카테고리='노트북' 검색
  2. 상위 5개 상품 조회
  3. 상품명, 가격, 재고 정보를 자연어로 응답

사용자: "맥북 프로 16인치 가격이 얼마야?"
챗봇:
  1. Product API에서 키워드 검색
  2. 해당 상품의 상세 정보 조회
  3. 가격 및 할인 정보 제공

사용자: "100만원 이하 게이밍 노트북 보여줘"
챗봇:
  1. 필터링 쿼리: 카테고리=노트북, 가격<=100만원, 태그=게이밍
  2. 검색 결과를 카드 형식으로 제공
```

### 2. 필수 기능

- ✅ **상품 검색**: 키워드, 카테고리, 가격 범위, 속성 필터
- ✅ **상품 상세 조회**: ID 기반 단건 조회
- ✅ **재고 확인**: 실시간 재고 수량
- ✅ **속성 기반 필터링**: 브랜드, 색상, 사이즈 등
- ✅ **카테고리 탐색**: 계층형 카테고리 구조

---

## 🔌 API 엔드포인트 명세

### Base URL

```
Production: https://api.c4ang.com/v1
Development: http://localhost:8080/api/v1
Staging: https://staging-api.c4ang.com/v1
```

### 인증 방식

```http
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json
```

---

## 📡 API 상세 명세

### 1. 상품 목록 조회 (GET /products)

**목적**: 검색 조건에 맞는 상품 목록 조회 (챗봇 검색 기능)

#### Request

```http
GET /api/v1/products?keyword=노트북&category=electronics&minPrice=500000&maxPrice=2000000&attributes={"color":"silver","ram":"16GB"}&sort=newest&page=0&size=10
```

**Query Parameters**:

| Parameter | Type | Required | Description | Example |
|-----------|------|----------|-------------|---------|
| keyword | String | ❌ | 검색 키워드 | "맥북", "게이밍" |
| category | String | ❌ | 카테고리 ID 또는 slug | "electronics", "cat_001" |
| minPrice | Integer | ❌ | 최소 가격 (원) | 500000 |
| maxPrice | Integer | ❌ | 최대 가격 (원) | 2000000 |
| brand | String | ❌ | 브랜드명 | "Apple", "Samsung" |
| attributes | JSON String | ❌ | 속성 필터 (JSON 형식) | {"color":"silver","storage":"512GB"} |
| tags | String[] | ❌ | 태그 필터 (콤마 구분) | "gaming,lightweight" |
| inStock | Boolean | ❌ | 재고 있는 상품만 | true (default: false) |
| sort | String | ❌ | 정렬 기준 | newest, price_asc, price_desc, popular |
| page | Integer | ❌ | 페이지 번호 (0부터 시작) | 0 |
| size | Integer | ❌ | 페이지 크기 | 10 (default), max 100 |

#### Response (200 OK)

```json
{
  "success": true,
  "data": {
    "products": [
      {
        "id": "prod_123456",
        "name": "MacBook Pro 16 M3",
        "description": "Apple M3 칩 탑재, 16인치 Liquid Retina XDR 디스플레이",
        "shortDescription": "고성능 프리미엄 노트북",
        "category": {
          "id": "cat_001",
          "name": "노트북",
          "slug": "laptops",
          "path": "전자제품 > 컴퓨터 > 노트북",
          "parentId": "cat_parent_001"
        },
        "brand": {
          "id": "brand_001",
          "name": "Apple",
          "logo": "https://cdn.c4ang.com/brands/apple-logo.png"
        },
        "price": {
          "original": 3590000,
          "discounted": 3290000,
          "currency": "KRW",
          "discountRate": 8.4,
          "hasDiscount": true
        },
        "images": {
          "thumbnail": "https://cdn.c4ang.com/products/prod_123456/thumb.jpg",
          "main": "https://cdn.c4ang.com/products/prod_123456/main.jpg",
          "gallery": [
            "https://cdn.c4ang.com/products/prod_123456/img1.jpg",
            "https://cdn.c4ang.com/products/prod_123456/img2.jpg",
            "https://cdn.c4ang.com/products/prod_123456/img3.jpg"
          ]
        },
        "stock": {
          "available": true,
          "quantity": 23,
          "status": "IN_STOCK",
          "lowStockThreshold": 10,
          "isLowStock": false
        },
        "attributes": {
          "color": {
            "name": "색상",
            "value": "스페이스 그레이",
            "code": "space_gray"
          },
          "cpu": {
            "name": "프로세서",
            "value": "Apple M3 Pro"
          },
          "ram": {
            "name": "메모리",
            "value": "18GB"
          },
          "storage": {
            "name": "저장용량",
            "value": "512GB SSD"
          },
          "display": {
            "name": "디스플레이",
            "value": "16.2인치"
          },
          "weight": {
            "name": "무게",
            "value": "2.16kg"
          }
        },
        "tags": ["프리미엄", "고성능", "크리에이터", "M3칩"],
        "seller": {
          "id": "seller_001",
          "name": "Apple 공식스토어",
          "verified": true
        },
        "shipping": {
          "free": true,
          "method": "무료배송",
          "estimatedDays": 2,
          "cost": 0
        },
        "status": "ACTIVE",
        "createdAt": "2024-11-15T10:30:00Z",
        "updatedAt": "2024-12-05T14:20:00Z"
      }
      // ... more products
    ],
    "pagination": {
      "currentPage": 0,
      "totalPages": 15,
      "totalItems": 147,
      "pageSize": 10,
      "hasNext": true,
      "hasPrevious": false
    },
    "filters": {
      "applied": {
        "keyword": "노트북",
        "category": "electronics",
        "priceRange": {
          "min": 500000,
          "max": 2000000
        },
        "attributes": {
          "color": "silver"
        }
      },
      "available": {
        "brands": [
          {
            "id": "brand_001",
            "name": "Apple",
            "count": 45
          },
          {
            "id": "brand_002",
            "name": "Samsung",
            "count": 67
          }
        ],
        "priceRanges": [
          {
            "min": 0,
            "max": 1000000,
            "count": 45
          },
          {
            "min": 1000000,
            "max": 2000000,
            "count": 67
          }
        ],
        "attributes": {
          "color": [
            {"value": "silver", "label": "실버", "count": 23},
            {"value": "space_gray", "label": "스페이스 그레이", "count": 34}
          ],
          "ram": [
            {"value": "16GB", "label": "16GB", "count": 56},
            {"value": "32GB", "label": "32GB", "count": 12}
          ]
        }
      }
    }
  },
  "timestamp": "2024-12-05T15:30:00Z"
}
```

**Status Codes**:
- `IN_STOCK`: 재고 충분 (quantity > lowStockThreshold)
- `LOW_STOCK`: 재고 부족 (0 < quantity <= lowStockThreshold)
- `OUT_OF_STOCK`: 재고 없음 (quantity = 0)
- `DISCONTINUED`: 단종

**Product Status**:
- `ACTIVE`: 판매 중
- `INACTIVE`: 판매 중지
- `DRAFT`: 임시 저장
- `ARCHIVED`: 보관

#### Error Response (400 Bad Request)

```json
{
  "success": false,
  "error": {
    "code": "INVALID_PARAMETER",
    "message": "가격 범위가 잘못되었습니다",
    "details": {
      "field": "minPrice",
      "reason": "minPrice는 maxPrice보다 작아야 합니다",
      "received": {
        "minPrice": 2000000,
        "maxPrice": 500000
      }
    }
  },
  "timestamp": "2024-12-05T15:30:00Z"
}
```

---

### 2. 상품 상세 조회 (GET /products/{productId})

**목적**: 특정 상품의 상세 정보 조회 (챗봇 상세 안내)

#### Request

```http
GET /api/v1/products/prod_123456
```

#### Response (200 OK)

```json
{
  "success": true,
  "data": {
    "id": "prod_123456",
    "sku": "MBP16-M3-SG-512",
    "name": "MacBook Pro 16 M3",
    "description": "Apple M3 칩을 탑재한 16인치 MacBook Pro는 강력한 성능과 긴 배터리 수명을 제공합니다. 프로급 작업을 위한 최고의 선택입니다.",
    "shortDescription": "고성능 프리미엄 노트북",
    "fullDescription": "## 주요 특징\n\n### 성능\n- Apple M3 Pro 칩 탑재\n- 최대 22시간 배터리 사용\n- 8K 비디오 편집 지원\n\n### 디스플레이\n- 16.2인치 Liquid Retina XDR\n- ProMotion 기술 (최대 120Hz)\n- 1000 nits 지속 밝기\n\n### 연결성\n- Thunderbolt 4 포트 3개\n- HDMI\n- MagSafe 3\n- 3.5mm 헤드폰 잭",
    "category": {
      "id": "cat_001",
      "name": "노트북",
      "slug": "laptops",
      "path": "전자제품 > 컴퓨터 > 노트북",
      "breadcrumb": [
        {"id": "cat_root", "name": "전자제품", "slug": "electronics"},
        {"id": "cat_comp", "name": "컴퓨터", "slug": "computers"},
        {"id": "cat_001", "name": "노트북", "slug": "laptops"}
      ]
    },
    "brand": {
      "id": "brand_001",
      "name": "Apple",
      "description": "혁신적인 기술을 선도하는 글로벌 브랜드",
      "logo": "https://cdn.c4ang.com/brands/apple-logo.png",
      "website": "https://www.apple.com/kr"
    },
    "price": {
      "original": 3590000,
      "discounted": 3290000,
      "currency": "KRW",
      "discountRate": 8.4,
      "hasDiscount": true,
      "discountStartDate": "2024-12-01T00:00:00Z",
      "discountEndDate": "2024-12-31T23:59:59Z"
    },
    "images": {
      "thumbnail": "https://cdn.c4ang.com/products/prod_123456/thumb.jpg",
      "main": "https://cdn.c4ang.com/products/prod_123456/main.jpg",
      "gallery": [
        {
          "url": "https://cdn.c4ang.com/products/prod_123456/img1.jpg",
          "alt": "MacBook Pro 정면",
          "order": 1
        },
        {
          "url": "https://cdn.c4ang.com/products/prod_123456/img2.jpg",
          "alt": "MacBook Pro 측면",
          "order": 2
        }
      ]
    },
    "stock": {
      "available": true,
      "quantity": 23,
      "status": "IN_STOCK",
      "lowStockThreshold": 10,
      "isLowStock": false,
      "reservedQuantity": 5,
      "availableQuantity": 18,
      "warehouses": [
        {
          "id": "wh_seoul",
          "name": "서울 물류센터",
          "quantity": 15,
          "location": "서울특별시"
        },
        {
          "id": "wh_busan",
          "name": "부산 물류센터",
          "quantity": 8,
          "location": "부산광역시"
        }
      ]
    },
    "attributes": {
      "color": {
        "name": "색상",
        "value": "스페이스 그레이",
        "code": "space_gray",
        "displayOrder": 1
      },
      "cpu": {
        "name": "프로세서",
        "value": "Apple M3 Pro (12코어 CPU, 18코어 GPU)",
        "displayOrder": 2
      },
      "ram": {
        "name": "메모리",
        "value": "18GB 통합 메모리",
        "displayOrder": 3
      },
      "storage": {
        "name": "저장용량",
        "value": "512GB SSD",
        "displayOrder": 4
      },
      "display": {
        "name": "디스플레이",
        "value": "16.2인치 Liquid Retina XDR (3456x2234)",
        "displayOrder": 5
      },
      "graphics": {
        "name": "그래픽",
        "value": "18코어 GPU"
      },
      "battery": {
        "name": "배터리",
        "value": "최대 22시간"
      },
      "weight": {
        "name": "무게",
        "value": "2.16kg",
        "unit": "kg"
      },
      "dimensions": {
        "name": "크기",
        "value": "35.57 x 24.81 x 1.68 cm"
      },
      "os": {
        "name": "운영체제",
        "value": "macOS Sonoma"
      }
    },
    "variants": [
      {
        "id": "prod_123457",
        "name": "MacBook Pro 16 M3 (1TB)",
        "attributes": {
          "storage": "1TB SSD"
        },
        "price": {
          "original": 4190000,
          "discounted": 3890000
        },
        "stock": {
          "available": true,
          "quantity": 12
        }
      }
    ],
    "relatedProducts": [
      {
        "id": "prod_789",
        "name": "Magic Mouse",
        "price": 119000,
        "thumbnail": "https://cdn.c4ang.com/products/prod_789/thumb.jpg",
        "relationship": "accessory"
      },
      {
        "id": "prod_790",
        "name": "MacBook Pro 14 M3",
        "price": 2890000,
        "thumbnail": "https://cdn.c4ang.com/products/prod_790/thumb.jpg",
        "relationship": "similar"
      }
    ],
    "tags": ["프리미엄", "고성능", "크리에이터", "M3칩", "2024신제품"],
    "seller": {
      "id": "seller_001",
      "name": "Apple 공식스토어",
      "verified": true,
      "businessNumber": "120-81-84429",
      "contactEmail": "support@apple.com",
      "contactPhone": "080-330-8877"
    },
    "shipping": {
      "free": true,
      "method": "무료배송",
      "estimatedDays": 2,
      "cost": 0,
      "courier": "CJ대한통운",
      "restrictions": []
    },
    "warranty": {
      "period": "1년",
      "type": "제조사 보증",
      "description": "Apple 제한 보증 1년 포함"
    },
    "certifications": [
      {
        "name": "KC 인증",
        "number": "R-R-App-MBP16-2024",
        "issuedDate": "2024-10-15"
      }
    ],
    "status": "ACTIVE",
    "metadata": {
      "viewCount": 15234,
      "wishlistCount": 892,
      "salesCount": 247
    },
    "seo": {
      "title": "MacBook Pro 16 M3 | 고성능 프리미엄 노트북",
      "description": "Apple M3 칩 탑재 MacBook Pro 16인치. 최대 22시간 배터리, 16.2인치 Liquid Retina XDR 디스플레이",
      "keywords": ["맥북", "맥북프로", "M3", "프리미엄노트북"]
    },
    "createdAt": "2024-11-15T10:30:00Z",
    "updatedAt": "2024-12-05T14:20:00Z",
    "publishedAt": "2024-11-20T09:00:00Z"
  },
  "timestamp": "2024-12-05T15:30:00Z"
}
```

#### Error Response (404 Not Found)

```json
{
  "success": false,
  "error": {
    "code": "PRODUCT_NOT_FOUND",
    "message": "해당 상품을 찾을 수 없습니다",
    "details": {
      "productId": "prod_999999"
    }
  },
  "timestamp": "2024-12-05T15:30:00Z"
}
```

---

### 3. 재고 확인 (GET /products/{productId}/stock)

**목적**: 실시간 재고 수량 확인 (챗봇이 구매 가능 여부 안내)

#### Request

```http
GET /api/v1/products/prod_123456/stock
```

**Query Parameters**:

| Parameter | Type | Description |
|-----------|------|-------------|
| warehouseId | String | 특정 창고의 재고만 조회 (optional) |

#### Response (200 OK)

```json
{
  "success": true,
  "data": {
    "productId": "prod_123456",
    "sku": "MBP16-M3-SG-512",
    "stock": {
      "available": true,
      "totalQuantity": 23,
      "reservedQuantity": 5,
      "availableQuantity": 18,
      "status": "IN_STOCK",
      "lowStockThreshold": 10,
      "isLowStock": false,
      "estimatedRestockDate": null,
      "lastUpdated": "2024-12-05T15:25:00Z"
    },
    "warehouses": [
      {
        "id": "wh_seoul",
        "name": "서울 물류센터",
        "location": {
          "city": "서울특별시",
          "district": "강남구"
        },
        "quantity": 15,
        "available": true,
        "estimatedDelivery": "2024-12-07"
      },
      {
        "id": "wh_busan",
        "name": "부산 물류센터",
        "location": {
          "city": "부산광역시",
          "district": "해운대구"
        },
        "quantity": 8,
        "available": true,
        "estimatedDelivery": "2024-12-08"
      }
    ]
  },
  "timestamp": "2024-12-05T15:30:00Z"
}
```

---

### 4. 카테고리 목록 조회 (GET /categories)

**목적**: 챗봇이 카테고리 기반 탐색 지원

#### Request

```http
GET /api/v1/categories?depth=2&includeProductCount=true
```

**Query Parameters**:

| Parameter | Type | Description |
|-----------|------|-------------|
| depth | Integer | 카테고리 깊이 (1: 대분류, 2: 중분류, 3: 소분류) |
| parentId | String | 특정 부모 카테고리의 하위 조회 |
| includeProductCount | Boolean | 상품 개수 포함 여부 (default: false) |

#### Response (200 OK)

```json
{
  "success": true,
  "data": {
    "categories": [
      {
        "id": "cat_001",
        "name": "전자제품",
        "slug": "electronics",
        "description": "최신 전자제품 및 IT기기",
        "icon": "https://cdn.c4ang.com/icons/electronics.png",
        "image": "https://cdn.c4ang.com/categories/electronics-banner.jpg",
        "level": 1,
        "order": 1,
        "productCount": 15420,
        "isActive": true,
        "subcategories": [
          {
            "id": "cat_001_001",
            "name": "컴퓨터",
            "slug": "computers",
            "parentId": "cat_001",
            "level": 2,
            "order": 1,
            "productCount": 5247,
            "subcategories": [
              {
                "id": "cat_001_001_001",
                "name": "노트북",
                "slug": "laptops",
                "parentId": "cat_001_001",
                "level": 3,
                "order": 1,
                "productCount": 1247
              },
              {
                "id": "cat_001_001_002",
                "name": "데스크톱",
                "slug": "desktops",
                "parentId": "cat_001_001",
                "level": 3,
                "order": 2,
                "productCount": 892
              }
            ]
          },
          {
            "id": "cat_001_002",
            "name": "스마트폰",
            "slug": "smartphones",
            "parentId": "cat_001",
            "level": 2,
            "order": 2,
            "productCount": 2891
          }
        ]
      },
      {
        "id": "cat_002",
        "name": "패션",
        "slug": "fashion",
        "productCount": 28934,
        "level": 1,
        "order": 2,
        "subcategories": []
      }
    ],
    "totalCount": 156
  },
  "timestamp": "2024-12-05T15:30:00Z"
}
```

---

### 5. 상품 속성 스키마 조회 (GET /categories/{categoryId}/attributes)

**목적**: 특정 카테고리에서 사용 가능한 속성 목록 및 필터 옵션 조회

#### Request

```http
GET /api/v1/categories/cat_001_001_001/attributes
```

#### Response (200 OK)

```json
{
  "success": true,
  "data": {
    "categoryId": "cat_001_001_001",
    "categoryName": "노트북",
    "attributes": [
      {
        "key": "brand",
        "name": "브랜드",
        "type": "SELECT",
        "required": true,
        "filterable": true,
        "searchable": true,
        "displayOrder": 1,
        "options": [
          {
            "value": "apple",
            "label": "Apple",
            "productCount": 45
          },
          {
            "value": "samsung",
            "label": "Samsung",
            "productCount": 67
          },
          {
            "value": "lg",
            "label": "LG",
            "productCount": 34
          }
        ]
      },
      {
        "key": "cpu",
        "name": "프로세서",
        "type": "SELECT",
        "required": true,
        "filterable": true,
        "searchable": true,
        "displayOrder": 2,
        "options": [
          {
            "value": "m3",
            "label": "Apple M3",
            "productCount": 23
          },
          {
            "value": "i9_13th",
            "label": "Intel Core i9 13세대",
            "productCount": 34
          },
          {
            "value": "ryzen_9",
            "label": "AMD Ryzen 9",
            "productCount": 28
          }
        ]
      },
      {
        "key": "ram",
        "name": "메모리",
        "type": "SELECT",
        "required": true,
        "filterable": true,
        "displayOrder": 3,
        "options": [
          {"value": "8gb", "label": "8GB", "productCount": 89},
          {"value": "16gb", "label": "16GB", "productCount": 156},
          {"value": "32gb", "label": "32GB", "productCount": 67},
          {"value": "64gb", "label": "64GB", "productCount": 23}
        ]
      },
      {
        "key": "storage",
        "name": "저장용량",
        "type": "SELECT",
        "required": true,
        "filterable": true,
        "displayOrder": 4,
        "options": [
          {"value": "256gb", "label": "256GB SSD", "productCount": 112},
          {"value": "512gb", "label": "512GB SSD", "productCount": 178},
          {"value": "1tb", "label": "1TB SSD", "productCount": 89},
          {"value": "2tb", "label": "2TB SSD", "productCount": 34}
        ]
      },
      {
        "key": "display_size",
        "name": "화면 크기",
        "type": "RANGE",
        "unit": "인치",
        "required": false,
        "filterable": true,
        "displayOrder": 5,
        "range": {
          "min": 13,
          "max": 17,
          "step": 0.1
        },
        "commonValues": [
          {"value": 13.3, "label": "13.3인치", "productCount": 67},
          {"value": 14, "label": "14인치", "productCount": 89},
          {"value": 15.6, "label": "15.6인치", "productCount": 123},
          {"value": 16, "label": "16인치", "productCount": 56},
          {"value": 17, "label": "17인치", "productCount": 34}
        ]
      },
      {
        "key": "weight",
        "name": "무게",
        "type": "RANGE",
        "unit": "kg",
        "required": false,
        "filterable": true,
        "displayOrder": 6,
        "range": {
          "min": 1.0,
          "max": 3.5,
          "step": 0.1
        }
      },
      {
        "key": "color",
        "name": "색상",
        "type": "MULTI_SELECT",
        "required": false,
        "filterable": true,
        "displayOrder": 7,
        "options": [
          {
            "value": "silver",
            "label": "실버",
            "code": "#C0C0C0",
            "productCount": 145
          },
          {
            "value": "space_gray",
            "label": "스페이스 그레이",
            "code": "#71797E",
            "productCount": 123
          },
          {
            "value": "black",
            "label": "블랙",
            "code": "#000000",
            "productCount": 98
          }
        ]
      },
      {