-- Contract Test Data for Product Service
-- 이 파일은 Contract Test 실행 전에 로드됩니다.

-- 카테고리 데이터
INSERT INTO p_category (id, name, parent_id, created_at, updated_at)
VALUES
    ('750e8400-e29b-41d4-a716-446655440301', 'Electronics', NULL, NOW(), NOW()),
    ('750e8400-e29b-41d4-a716-446655440302', 'Mobile', '750e8400-e29b-41d4-a716-446655440301', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- 상품 데이터
INSERT INTO p_product (id, store_id, store_name, category_id, name, description, price, stock_quantity, status, is_hidden, is_deleted, created_at, updated_at)
VALUES
    ('750e8400-e29b-41d4-a716-446655440201', '750e8400-e29b-41d4-a716-446655440101', 'Contract Test Store', '750e8400-e29b-41d4-a716-446655440302', 'Contract Test Product', 'A test product for contract verification', 10000, 100, 'ON_SALE', false, false, '2024-01-01 00:00:00', '2024-01-01 00:00:00')
ON CONFLICT (id) DO NOTHING;
