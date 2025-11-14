-- ============================================================
-- Test data for Customer Product View E2E Test
-- 일반고객의 상품 조회 시나리오를 테스트하기 위한 샘플 데이터
-- ============================================================

-- ============================================================
-- 1. 판매자 (OWNER) + 스토어 (간소화)
-- ============================================================

-- Owner 1: 전자제품 스토어
INSERT INTO p_user (id, username, email, password_hash, role, is_active, created_at, updated_at)
VALUES ('22222222-2222-2222-2222-222222222221', 'owner1', 'owner1@test.com',
        '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'OWNER', true, NOW(), NOW());

INSERT INTO p_user_profile (id, user_id, full_name, phone_number, contact_email, default_address, created_at, updated_at)
VALUES ('77777777-7777-7777-7777-777777777771', '22222222-2222-2222-2222-222222222221',
        '전자제품 판매자', '010-4444-4444', 'owner1@test.com', '서울시 용산구', NOW(), NOW());

INSERT INTO p_store (id, owner_user_id, name, description, status, created_at, updated_at)
VALUES ('33333333-3333-3333-3333-333333333331', '22222222-2222-2222-2222-222222222221',
        '테크노 전자', '최신 전자제품 전문 스토어', 'REGISTERED', NOW(), NOW());

INSERT INTO p_store_rating (id, store_id, average_rating, review_count, launched_at, created_at, updated_at)
VALUES ('33333333-3333-3333-3333-33333333331a', '33333333-3333-3333-3333-333333333331',
        4.7, 250, NOW(), NOW(), NOW());

-- ============================================================
-- 2. 상품 카테고리
-- ============================================================

INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES ('44444444-4444-4444-4444-444444444441', '전자제품', NULL, 0, NOW(), NOW());

INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES ('44444444-4444-4444-4444-44444444441b', '주변기기', '44444444-4444-4444-4444-444444444441', 1, NOW(), NOW());

-- ============================================================
-- 3. 상품 (10개 - 다양한 가격대 및 상태)
-- ============================================================

-- Product 1: 무선 마우스 Pro (판매중)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-555555555501', '33333333-3333-3333-3333-333333333331', '테크노 전자', '44444444-4444-4444-4444-44444444441b',
        '무선 마우스 Pro', 'ON_SALE', 29900.00, 150, 'https://example.com/thumbnails/mouse_pro.jpg',
        '편안한 그립감의 무선 마우스', NOW(), NOW());

INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-55555555501a', '55555555-5555-5555-5555-555555555501', 'PRIMARY', 'https://example.com/images/mouse_pro_primary.jpg', 0, NOW(), NOW());

-- Product 2: 기계식 키보드 (판매중)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-555555555502', '33333333-3333-3333-3333-333333333331', '테크노 전자', '44444444-4444-4444-4444-44444444441b',
        '기계식 키보드 RGB', 'ON_SALE', 89900.00, 80, 'https://example.com/thumbnails/keyboard_rgb.jpg',
        '청축 스위치 RGB 키보드', NOW(), NOW());

INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-55555555502a', '55555555-5555-5555-5555-555555555502', 'PRIMARY', 'https://example.com/images/keyboard_rgb_primary.jpg', 0, NOW(), NOW());

-- Product 3: 웹캠 (저가)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-555555555505', '33333333-3333-3333-3333-333333333331', '테크노 전자', '44444444-4444-4444-4444-44444444441b',
        'HD 웹캠', 'ON_SALE', 19900.00, 200, 'https://example.com/thumbnails/webcam.jpg',
        '화상회의용 HD 웹캠', NOW(), NOW());

INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-55555555505a', '55555555-5555-5555-5555-555555555505', 'PRIMARY', 'https://example.com/images/webcam_primary.jpg', 0, NOW(), NOW());

-- Product 4: 노트북 (고가)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-555555555504', '33333333-3333-3333-3333-333333333331', '테크노 전자', '44444444-4444-4444-4444-44444444441b',
        '게이밍 노트북', 'ON_SALE', 1299000.00, 15, 'https://example.com/thumbnails/laptop_gaming.jpg',
        'RTX 4060 탑재 게이밍 노트북', NOW(), NOW());

INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-55555555504a', '55555555-5555-5555-5555-555555555504', 'PRIMARY', 'https://example.com/images/laptop_gaming_primary.jpg', 0, NOW(), NOW());

-- Product 5: 재고 없는 상품 (품절)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-555555555509', '33333333-3333-3333-3333-333333333331', '테크노 전자', '44444444-4444-4444-4444-44444444441b',
        '품절된 마우스', 'OFF_SHELF', 24900.00, 0, 'https://example.com/thumbnails/mouse_soldout.jpg',
        '재고 소진된 상품', NOW(), NOW());

INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-55555555509a', '55555555-5555-5555-5555-555555555509', 'PRIMARY', 'https://example.com/images/mouse_soldout_primary.jpg', 0, NOW(), NOW());

-- Product 6: 삭제된 상품
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at, deleted_at)
VALUES ('55555555-5555-5555-5555-555555555510', '33333333-3333-3333-3333-333333333331', '테크노 전자', '44444444-4444-4444-4444-44444444441b',
        '삭제된 키보드', 'OFF_SHELF', 39900.00, 0, 'https://example.com/thumbnails/keyboard_deleted.jpg',
        '삭제된 상품', NOW(), NOW(), NOW());

-- Product 7: 숨겨진 상품
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at, hidden_at)
VALUES ('55555555-5555-5555-5555-555555555520', '33333333-3333-3333-3333-333333333331', '테크노 전자', '44444444-4444-4444-4444-44444444441b',
        '숨겨진 상품', 'ON_SALE', 79900.00, 50, 'https://example.com/thumbnails/hidden.jpg',
        '숨김 처리된 상품', NOW(), NOW(), NOW());
