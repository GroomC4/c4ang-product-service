# Product 도메인 패키지 구조

## DDL 기반 테이블 구조
```sql
- p_product_category: 카테고리 계층 구조
  - id (UUID PK)
  - name (TEXT)
  - parent_category_id (UUID, nullable)
  - depth (INTEGER, >= 0)

- p_product: 판매 상품 정보
  - id (UUID PK)
  - store_id (UUID)
  - category_id (UUID, nullable)
  - name (TEXT)
  - status (ON_SALE, OFF_SHELF)
  - price (NUMERIC(12,2), >= 0)
  - stock_quantity (INTEGER, >= 0)
  - thumbnail_url (TEXT)
  - description (TEXT)
  - hidden_at (TIMESTAMPTZ)
  - is_age_restricted (BOOLEAN)
  - shipping_type (STANDARD, ROCKET, SELLER_ROCKET, ROCKET_WOW)

- p_product_image: 상품 이미지
  - id (UUID PK)
  - product_id (UUID)
  - image_type (PRIMARY, DETAIL)
  - image_url (TEXT)
  - display_order (INTEGER)
```

## 패키지 구조 (User 패키지 참고)

### common/enums/
- **ProductStatus.kt**: ON_SALE, OFF_SHELF
- **ShippingType.kt**: STANDARD, ROCKET, SELLER_ROCKET, ROCKET_WOW
- **ImageType.kt**: PRIMARY, DETAIL

### domain/model/
- **Product.kt**: 애그리게이트 루트 (UUID PK)
- **ProductCategory.kt**: 카테고리 엔티티
- **ProductImage.kt**: 상품 이미지 엔티티
- **ProductName.kt**: Value Object (상품명)
- **Price.kt**: Value Object (가격)
- **Stock.kt**: Value Object (재고)

### domain/service/
- **ProductFactory.kt**: Product 생성
- **ProductPolicy.kt**: 재고 검증, 가격 검증
- **ProductReader.kt**: 상품 조회 서비스

### infrastructure/repository/
- **ProductRepositoryImpl.kt**: JpaRepository<Product, UUID>
- **ProductCategoryRepositoryImpl.kt**: JpaRepository<ProductCategory, UUID>
- **ProductImageRepositoryImpl.kt**: JpaRepository<ProductImage, UUID>

### infrastructure/adapter/
- **ProductSearchAdapter.kt**: 검색 엔진 연동 (Elasticsearch, etc.)

### application/dto/
- **RegisterProductCommand.kt**: 상품 등록 커맨드
- **RegisterProductResult.kt**: 상품 등록 결과

### application/service/
- **RegisterProductService.kt**: 상품 등록 유스케이스

### presentation/web/dto/
- **RegisterProductRequest.kt**: API 요청 DTO
- **RegisterProductResponse.kt**: API 응답 DTO

### presentation/web/
- **ProductController.kt**: POST /api/v1/products
