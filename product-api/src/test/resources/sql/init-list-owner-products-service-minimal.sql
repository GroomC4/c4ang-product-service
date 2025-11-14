-- Minimal Test data for ListOwnerProductsServiceIntegrationTest

-- Test Store 1
INSERT INTO p_user (id, username, email, password_hash, role, is_active, created_at, updated_at)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'owner1', 'owner1@test.com',
        '$2a$10$dummyHashForTesting', 'OWNER', true, NOW(), NOW());

INSERT INTO p_user_profile (id, user_id, full_name, phone_number, contact_email, default_address, created_at, updated_at)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'Owner 1', '010-1111-1111', 'owner1@test.com', '서울시 강남구', NOW(), NOW());

INSERT INTO p_store (id, owner_user_id, name, description, status, created_at, updated_at)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        '테스트 메인 스토어', '다양한 상품을 판매하는 스토어', 'REGISTERED', NOW(), NOW());

-- Test Product (with category required per DDL)
INSERT INTO p_product (id, store_id, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, hidden_at, created_at, updated_at, deleted_at)
VALUES ('00010001-0000-0000-0000-000000000001'::uuid, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'::uuid, '00000000-0000-0000-0000-000000000001'::uuid, '무선 마우스 A', 'ON_SALE', 29900.00, 100, 'https://example.com/mouse-a.jpg', '고급 무선 마우스', NULL, NOW(), NOW(), NULL);
