-- Contract Test Data for Product Service
-- 이 파일은 Contract Test 실행 전에 로드됩니다.

-- 카테고리 데이터
INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES
    ('750e8400-e29b-41d4-a716-446655440301', 'Electronics', NULL, 0, NOW(), NOW()),
    ('750e8400-e29b-41d4-a716-446655440302', 'Mobile', '750e8400-e29b-41d4-a716-446655440301', 1, NOW(), NOW()),
    ('750e8400-e29b-41d4-a716-446655440303', 'Beverage', NULL, 0, NOW(), NOW()),
    -- RAG Service Contract Test 카테고리
    ('750e8400-e29b-41d4-a716-446655440304', 'Perfume', NULL, 0, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- 상품 데이터
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, description, price, stock_quantity, status, hidden_at, deleted_at, created_at, updated_at)
VALUES
    ('750e8400-e29b-41d4-a716-446655440201', '750e8400-e29b-41d4-a716-446655440101', 'Contract Test Store', '750e8400-e29b-41d4-a716-446655440302', 'Contract Test Product', 'A test product for contract verification', 10000, 100, 'ON_SALE', NULL, NULL, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
    -- Order Service Internal API Contract Test Data
    ('550e8400-e29b-41d4-a716-446655440000', '660e8400-e29b-41d4-a716-446655440001', '스타벅스 강남점', '750e8400-e29b-41d4-a716-446655440303', '아메리카노', '진한 에스프레소에 물을 더해 깔끔하게 즐기는 커피', 4500.00, 100, 'ON_SALE', NULL, NULL, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
    ('550e8400-e29b-41d4-a716-446655440002', '660e8400-e29b-41d4-a716-446655440001', '스타벅스 강남점', '750e8400-e29b-41d4-a716-446655440303', '카페라떼', '에스프레소에 스팀 밀크를 더해 부드럽게 즐기는 커피', 5000.00, 50, 'ON_SALE', NULL, NULL, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
    -- RAG Service Internal API Contract Test Data (향수 관련 상품)
    ('550e8400-e29b-41d4-a716-446655440010', '660e8400-e29b-41d4-a716-446655440002', 'Contract Perfume Store', '750e8400-e29b-41d4-a716-446655440304', '조말론 라임 바질 앤 만다린', '상쾌한 시트러스 향이 특징인 여름 향수', 195000.00, 50, 'ON_SALE', NULL, NULL, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
    ('550e8400-e29b-41d4-a716-446655440011', '660e8400-e29b-41d4-a716-446655440002', 'Contract Perfume Store', '750e8400-e29b-41d4-a716-446655440304', '디올 미스 디올', '우아한 플로랄 향수로 로맨틱한 분위기를 연출합니다.', 180000.00, 100, 'ON_SALE', NULL, NULL, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
    ('550e8400-e29b-41d4-a716-446655440012', '660e8400-e29b-41d4-a716-446655440002', 'Contract Perfume Store', '750e8400-e29b-41d4-a716-446655440304', '샤넬 코코 마드모아젤', '여성을 위한 우아한 플로럴 향수', 200000.00, 80, 'ON_SALE', NULL, NULL, '2024-01-01 00:00:00', '2024-01-01 00:00:00')
ON CONFLICT (id) DO NOTHING;

-- RAG Service Internal API Contract Test Data (향수 데이터)
-- Contract YAML 파일에서 사용하는 UUID와 일치해야 함
INSERT INTO p_perfume (id, product_id, brand, name, concentration, main_accords, top_notes, middle_notes, base_notes, notes_score, season_score, day_night_score, gender, sizes, detail_url) VALUES
    -- searchProducts_success.yml, searchProducts_byProductIds.yml에서 사용
    (
        '550e8400-e29b-41d4-a716-446655440000',
        '550e8400-e29b-41d4-a716-446655440010',
        '조말론',
        '라임 바질 앤 만다린_CONTRACT',
        'Eau de Cologne',
        '["시트러스", "아로마틱"]',
        '["라임", "만다린 오렌지", "베르가못"]',
        '["바질", "타임"]',
        '["베티버", "앰버그리스"]',
        '{"citrus": 92.0, "aromatic": 80.0}',
        '{"spring": 70.0, "summer": 90.0, "fall": 40.0, "winter": 25.0}',
        '{"day": 95.0, "night": 35.0}',
        'Unisex',
        '[{"sizeMl": 30, "price": 95000}, {"sizeMl": 100, "price": 195000}]',
        'https://example.com/images/lime-basil.jpg'
    ),
    -- compareProducts_success.yml, searchProducts_byProductIds.yml에서 사용
    (
        '550e8400-e29b-41d4-a716-446655440001',
        '550e8400-e29b-41d4-a716-446655440011',
        '아쿠아 디 파르마',
        '콜로니아_CONTRACT',
        'Eau de Cologne',
        '["시트러스", "우디"]',
        '["레몬", "베르가못", "오렌지"]',
        '["라벤더", "로즈마리"]',
        '["베티버", "샌달우드", "파촐리"]',
        '{"citrus": 88.0, "woody": 70.0}',
        '{"spring": 65.0, "summer": 85.0, "fall": 45.0, "winter": 30.0}',
        '{"day": 90.0, "night": 40.0}',
        'Unisex',
        '[{"sizeMl": 50, "price": 120000}, {"sizeMl": 100, "price": 180000}]',
        'https://example.com/images/colonia.jpg'
    ),
    -- getProductDetail_success.yml에서 사용 (상세 정보 조회용)
    (
        '550e8400-e29b-41d4-a716-446655440002',
        '550e8400-e29b-41d4-a716-446655440012',
        '디올',
        '미스 디올_CONTRACT',
        'Eau de Parfum',
        '["플로랄", "로즈"]',
        '["만다린", "베르가못"]',
        '["로즈", "피오니"]',
        '["머스크", "샌달우드"]',
        '{"floral": 95.0, "rose": 88.0}',
        '{"spring": 35.0, "summer": 10.0, "fall": 40.0, "winter": 15.0}',
        '{"day": 70.0, "night": 30.0}',
        'Female',
        '[{"sizeMl": 30, "price": 120000}, {"sizeMl": 50, "price": 180000}, {"sizeMl": 100, "price": 280000}]',
        'https://example.com/products/miss-dior'
    )
ON CONFLICT (brand, name) DO NOTHING;
