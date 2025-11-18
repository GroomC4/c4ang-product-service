-- Test data for ProductQueryControllerIntegrationTest
-- Using fixed UUIDs for predictable testing

-- Note: User and Store information is not stored in Product service
-- They are managed by their respective services and accessed via Istio headers/Feign clients

-- ============================================================
-- Product Categories
-- ============================================================

-- Category 1: 전자제품
INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '전자제품', NULL, 0, NOW(), NOW());

-- Category 2: 의류
INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1', '의류', NULL, 0, NOW(), NOW());

-- ============================================================
-- Products
-- ============================================================

-- Product 1 (store_1, 전자제품)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111110001', '11111111-1111-1111-1111-111111111101', '테크 스토어', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1',
        '무선 게이밍 마우스', 'ON_SALE', 79900.00, 150, 'https://example.com/thumbnails/mouse.jpg',
        '반응속도가 뛰어난 초경량 무선 마우스입니다', NOW(), NOW());

-- Product 2 (store_1, 전자제품)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111110002', '11111111-1111-1111-1111-111111111101', '테크 스토어', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1',
        '기계식 키보드', 'ON_SALE', 129900.00, 100, 'https://example.com/thumbnails/keyboard.jpg',
        '청축 스위치 기계식 키보드', NOW(), NOW());

-- Product 3 (store_2, 의류)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('22222222-2222-2222-2222-222222220001', '22222222-2222-2222-2222-222222222201', '패션 스토어', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1',
        '오버핏 티셔츠', 'ON_SALE', 29900.00, 200, 'https://example.com/thumbnails/tshirt.jpg',
        '편안한 오버핏 반팔 티셔츠', NOW(), NOW());

-- ============================================================
-- Product Images
-- ============================================================

-- Images for Product 1 (무선 게이밍 마우스)
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111110a00', '11111111-1111-1111-1111-111111110001',
        'PRIMARY', 'https://example.com/images/mouse_primary.jpg', 0, NOW(), NOW());

INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111110b00', '11111111-1111-1111-1111-111111110001',
        'DETAIL', 'https://example.com/images/mouse_detail1.jpg', 1, NOW(), NOW());

-- Images for Product 2 (기계식 키보드)
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111110c00', '11111111-1111-1111-1111-111111110002',
        'PRIMARY', 'https://example.com/images/keyboard_primary.jpg', 0, NOW(), NOW());

-- Images for Product 3 (오버핏 티셔츠)
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at)
VALUES ('22222222-2222-2222-2222-222222220a00', '22222222-2222-2222-2222-222222220001',
        'PRIMARY', 'https://example.com/images/tshirt_primary.jpg', 0, NOW(), NOW());

-- ============================================================
-- 유사도 검색 테스트용 추가 상품 데이터
-- ============================================================

-- Product 4: "무선 마우쓰" (오타 포함 - 유사도 검색 테스트용)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111110003', '11111111-1111-1111-1111-111111111101', '테크 스토어', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1',
        '무선 마우쓰 프로', 'ON_SALE', 89900.00, 80, 'https://example.com/thumbnails/mouse_pro.jpg',
        '고성능 무선 마우쓰 (오타 테스트용)', NOW(), NOW());

-- Product 5: "게이밍 키보드" (유사 상품)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111110004', '11111111-1111-1111-1111-111111111101', '테크 스토어', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1',
        '게이밍 키보드', 'ON_SALE', 149900.00, 60, 'https://example.com/thumbnails/gaming_keyboard.jpg',
        '게이밍용 기계식 키보드', NOW(), NOW());

-- Product 6: "텍크 스토어" 상품 (스토어명 오타 테스트용)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111110005', '11111111-1111-1111-1111-111111111101', '텍크 스토어', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1',
        'USB 허브', 'ON_SALE', 19900.00, 300, 'https://example.com/thumbnails/usb_hub.jpg',
        'USB 3.0 허브 (스토어명 오타 테스트용)', NOW(), NOW());
