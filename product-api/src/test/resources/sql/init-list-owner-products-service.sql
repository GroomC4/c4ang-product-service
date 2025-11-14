-- Test data for ListOwnerProductsServiceIntegrationTest
-- Using fixed UUIDs for predictable testing

-- ============================================================
-- User & Store Setup
-- ============================================================

-- Test Store 1: 메인 테스트 스토어
INSERT INTO p_user (id, username, email, password_hash, role, is_active, created_at, updated_at)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'owner1', 'owner1@test.com',
        '$2a$10$dummyHashForTesting', 'OWNER', true, NOW(), NOW());

INSERT INTO p_user_profile (id, user_id, full_name, phone_number, contact_email, default_address, created_at, updated_at)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid,
        'Owner 1', '010-1111-1111', 'owner1@test.com', '서울시 강남구', NOW(), NOW());

INSERT INTO p_store (id, owner_user_id, name, description, status, created_at, updated_at)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid,
        '테스트 메인 스토어', '다양한 상품을 판매하는 스토어', 'REGISTERED', NOW(), NOW());

-- Test Store 2: 다른 판매자의 스토어 (격리 테스트용)
INSERT INTO p_user (id, username, email, password_hash, role, is_active, created_at, updated_at)
VALUES ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'::uuid, 'owner2', 'owner2@test.com',
        '$2a$10$dummyHashForTesting', 'OWNER', true, NOW(), NOW());

INSERT INTO p_user_profile (id, user_id, full_name, phone_number, contact_email, default_address, created_at, updated_at)
VALUES ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1'::uuid, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'::uuid,
        'Owner 2', '010-2222-2222', 'owner2@test.com', '서울시 서초구', NOW(), NOW());

INSERT INTO p_store (id, owner_user_id, name, description, status, created_at, updated_at)
VALUES ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2'::uuid, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'::uuid,
        '다른 판매자 스토어', '격리 테스트용', 'REGISTERED', NOW(), NOW());

-- ============================================================
-- Product Categories
-- ============================================================

INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES ('ca000000-0000-0000-0000-000000000001'::uuid, '전자제품', NULL, 0, NOW(), NOW());

INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES ('ca000000-0000-0000-0000-000000000002'::uuid, '의류', NULL, 0, NOW(), NOW());

INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES ('ca000000-0000-0000-0000-000000000011'::uuid, '컴퓨터 주변기기', 'ca000000-0000-0000-0000-000000000001'::uuid, 1, NOW(), NOW());

INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES ('ca000000-0000-0000-0000-000000000021'::uuid, '남성의류', 'ca000000-0000-0000-0000-000000000002'::uuid, 1, NOW(), NOW());

-- ============================================================
-- Test Store 1 Products (메인 테스트 데이터)
-- ============================================================

-- Scenario 1: 정상 판매 중인 상품 (10개) - 한글/영문 혼합
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111101'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '무선 마우스 A', 'ON_SALE', 29900.00, 100, 'https://example.com/mouse-a.jpg', '고급 무선 마우스', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111102'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '무선 키보드 B', 'ON_SALE', 59900.00, 50, 'https://example.com/keyboard-b.jpg', '기계식 키보드', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111103'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '웹캠 C', 'ON_SALE', 89900.00, 30, 'https://example.com/webcam-c.jpg', 'FHD 웹캠', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111104'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '모니터 D', 'ON_SALE', 299000.00, 20, 'https://example.com/monitor-d.jpg', '27인치 모니터', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111105'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000021'::uuid, '티셔츠 E', 'ON_SALE', 19900.00, 200, 'https://example.com/tshirt-e.jpg', '면 100% 티셔츠', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111106'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000021'::uuid, '청바지 F', 'ON_SALE', 49900.00, 150, 'https://example.com/jeans-f.jpg', '데님 청바지', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111107'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000021'::uuid, '후드티 G', 'ON_SALE', 39900.00, 80, 'https://example.com/hoodie-g.jpg', '기모 후드티', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111108'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, 'USB 허브 H', 'ON_SALE', 15900.00, 300, 'https://example.com/usbhub-h.jpg', '4포트 USB 허브', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111109'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '헤드셋 I', 'ON_SALE', 79900.00, 60, 'https://example.com/headset-i.jpg', '게이밍 헤드셋', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111110'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '마우스패드 J', 'ON_SALE', 9900.00, 500, 'https://example.com/mousepad-j.jpg', '대형 마우스패드', NULL, NOW(), NOW(), NULL);

-- Scenario 2: 숨김 처리된 상품 (5개)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('22222222-2222-2222-2222-222222222201'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '숨김 상품 1', 'ON_SALE', 10000.00, 10, 'https://example.com/hidden-1.jpg', '일시적으로 숨김', NOW(), NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('22222222-2222-2222-2222-222222222202'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '숨김 상품 2', 'ON_SALE', 20000.00, 5, 'https://example.com/hidden-2.jpg', '재고 부족으로 숨김', NOW(), NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('22222222-2222-2222-2222-222222222203'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000021'::uuid, '숨김 상품 3', 'ON_SALE', 30000.00, 0, 'https://example.com/hidden-3.jpg', '품절로 숨김', NOW(), NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('22222222-2222-2222-2222-222222222204'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000021'::uuid, '숨김 상품 4', 'OFF_SHELF', 40000.00, 20, 'https://example.com/hidden-4.jpg', '판매 중단', NOW(), NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('22222222-2222-2222-2222-222222222205'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '숨김 상품 5', 'ON_SALE', 50000.00, 15, 'https://example.com/hidden-5.jpg', '가격 조정 중', NOW(), NOW(), NOW(), NULL);

-- Scenario 3: 삭제된 상품 (3개)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('33333333-3333-3333-3333-333333333301'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '삭제된 상품 1', 'OFF_SHELF', 60000.00, 0, 'https://example.com/deleted-1.jpg', '단종된 상품', NULL, NOW(), NOW(), NOW());

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('33333333-3333-3333-3333-333333333302'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000021'::uuid, '삭제된 상품 2', 'OFF_SHELF', 70000.00, 0, 'https://example.com/deleted-2.jpg', '판매 종료', NULL, NOW(), NOW(), NOW());

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('33333333-3333-3333-3333-333333333303'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '삭제된 상품 3', 'OFF_SHELF', 80000.00, 0, NULL, '결함 발견으로 삭제', NOW(), NOW(), NOW(), NOW());

-- Scenario 4: 가격대별 상품 (정렬 테스트용)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('44444444-4444-4444-4444-444444444401'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '저가 상품 A', 'ON_SALE', 5000.00, 1000, 'https://example.com/low-a.jpg', '5천원대', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('44444444-4444-4444-4444-444444444402'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '중가 상품 B', 'ON_SALE', 50000.00, 100, 'https://example.com/mid-b.jpg', '5만원대', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('44444444-4444-4444-4444-444444444403'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '고가 상품 C', 'ON_SALE', 500000.00, 10, 'https://example.com/high-c.jpg', '50만원대', NULL, NOW(), NOW(), NULL);

-- Scenario 5: 재고 상태별 상품
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('55555555-5555-5555-5555-555555555501'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '대량 재고 상품', 'ON_SALE', 25000.00, 9999, 'https://example.com/high-stock.jpg', '대량 재고', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('55555555-5555-5555-5555-555555555502'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '소량 재고 상품', 'ON_SALE', 35000.00, 3, 'https://example.com/low-stock.jpg', '소량 재고', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('55555555-5555-5555-5555-555555555503'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '품절 상품', 'ON_SALE', 45000.00, 0, 'https://example.com/no-stock.jpg', '품절', NULL, NOW(), NOW(), NULL);

-- Scenario 6: 연령 제한 상품
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('66666666-6666-6666-6666-666666666601'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '성인 상품 1', 'ON_SALE', 99000.00, 50, 'https://example.com/adult-1.jpg', '19세 이상 구매 가능', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('66666666-6666-6666-6666-666666666602'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '성인 상품 2', 'ON_SALE', 120000.00, 30, 'https://example.com/adult-2.jpg', '성인용 상품', NULL, NOW(), NOW(), NULL);

-- Scenario 7: 배송 타입별 상품
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('77777777-7777-7777-7777-777777777701'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '로켓배송 상품 1', 'ON_SALE', 33000.00, 200, 'https://example.com/rocket-1.jpg', '빠른 배송', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('77777777-7777-7777-7777-777777777702'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '테스트 메인 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '일반배송 상품 1', 'ON_SALE', 27000.00, 150, 'https://example.com/standard-1.jpg', '일반 배송', NULL, NOW(), NOW(), NULL);

-- ============================================================
-- Test Store 2 Products (다른 스토어 상품 - 격리 테스트용)
-- ============================================================

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('99999999-0000-0000-0000-000000000001'::uuid, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2'::uuid, '다른 판매자 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '다른 스토어 상품 1', 'ON_SALE', 100000.00, 50, 'https://example.com/other-1.jpg', '격리 테스트용', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('99999999-0000-0000-0000-000000000002'::uuid, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2'::uuid, '다른 판매자 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '다른 스토어 상품 2', 'ON_SALE', 200000.00, 30, 'https://example.com/other-2.jpg', '격리 테스트용', NULL, NOW(), NOW(), NULL);

INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('99999999-0000-0000-0000-000000000003'::uuid, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2'::uuid, '다른 판매자 스토어', 'ca000000-0000-0000-0000-000000000011'::uuid, '다른 스토어 삭제 상품', 'OFF_SHELF', 300000.00, 0, 'https://example.com/other-3.jpg', '삭제됨', NULL, NOW(), NOW(), NOW());
