-- Contract Test Data for Product Service
-- 이 파일은 Contract Test 실행 전에 로드됩니다.

-- 카테고리 데이터
INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES
    ('750e8400-e29b-41d4-a716-446655440301', 'Electronics', NULL, 0, NOW(), NOW()),
    ('750e8400-e29b-41d4-a716-446655440302', 'Mobile', '750e8400-e29b-41d4-a716-446655440301', 1, NOW(), NOW()),
    ('750e8400-e29b-41d4-a716-446655440303', 'Beverage', NULL, 0, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- 상품 데이터
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, description, price, stock_quantity, status, hidden_at, deleted_at, created_at, updated_at)
VALUES
    ('750e8400-e29b-41d4-a716-446655440201', '750e8400-e29b-41d4-a716-446655440101', 'Contract Test Store', '750e8400-e29b-41d4-a716-446655440302', 'Contract Test Product', 'A test product for contract verification', 10000, 100, 'ON_SALE', NULL, NULL, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
    -- Order Service Internal API Contract Test Data
    ('550e8400-e29b-41d4-a716-446655440000', '660e8400-e29b-41d4-a716-446655440001', '스타벅스 강남점', '750e8400-e29b-41d4-a716-446655440303', '아메리카노', '진한 에스프레소에 물을 더해 깔끔하게 즐기는 커피', 4500.00, 100, 'ON_SALE', NULL, NULL, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
    ('550e8400-e29b-41d4-a716-446655440002', '660e8400-e29b-41d4-a716-446655440001', '스타벅스 강남점', '750e8400-e29b-41d4-a716-446655440303', '카페라떼', '에스프레소에 스팀 밀크를 더해 부드럽게 즐기는 커피', 5000.00, 50, 'ON_SALE', NULL, NULL, '2024-01-01 00:00:00', '2024-01-01 00:00:00')
ON CONFLICT (id) DO NOTHING;
