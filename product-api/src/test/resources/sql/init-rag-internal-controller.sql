-- RAG Internal Controller 통합 테스트용 데이터
-- UUID 패턴: aa000000 (rag 테스트용)
-- 브랜드/이름에 _TEST 접미사를 붙여 기존 데이터와 충돌 방지

-- 1. 카테고리 데이터
INSERT INTO p_product_category (id, name, parent_category_id, depth) VALUES
    ('aa000000-0000-0000-0000-000000000001', 'RAG_TEST_향수', NULL, 0),
    ('aa000000-0000-0000-0000-000000000002', 'RAG_TEST_여성 향수', 'aa000000-0000-0000-0000-000000000001', 1),
    ('aa000000-0000-0000-0000-000000000003', 'RAG_TEST_남성 향수', 'aa000000-0000-0000-0000-000000000001', 1),
    ('aa000000-0000-0000-0000-000000000004', 'RAG_TEST_유니섹스 향수', 'aa000000-0000-0000-0000-000000000001', 1)
ON CONFLICT (name, parent_category_id) DO NOTHING;

-- 2. 제품(Product) 데이터 - 5개
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, description) VALUES
    ('aa000000-0000-0000-0000-000000000101', 'aa000000-0000-0000-0000-000000000010', 'RAG Test Store', 'aa000000-0000-0000-0000-000000000002', 'RAG_TEST 샤넬 코코 마드모아젤', 'ON_SALE', 200000.00, 50, '여성을 위한 우아한 플로럴 향수'),
    ('aa000000-0000-0000-0000-000000000102', 'aa000000-0000-0000-0000-000000000010', 'RAG Test Store', 'aa000000-0000-0000-0000-000000000002', 'RAG_TEST 디올 미스 디올', 'ON_SALE', 180000.00, 100, '로맨틱한 플로럴 향수'),
    ('aa000000-0000-0000-0000-000000000103', 'aa000000-0000-0000-0000-000000000010', 'RAG Test Store', 'aa000000-0000-0000-0000-000000000003', 'RAG_TEST 디올 소바쥬', 'ON_SALE', 150000.00, 80, '강렬한 남성 향수'),
    ('aa000000-0000-0000-0000-000000000104', 'aa000000-0000-0000-0000-000000000010', 'RAG Test Store', 'aa000000-0000-0000-0000-000000000004', 'RAG_TEST 조말론 라임 바질', 'ON_SALE', 195000.00, 60, '상쾌한 유니섹스 향수'),
    ('aa000000-0000-0000-0000-000000000105', 'aa000000-0000-0000-0000-000000000010', 'RAG Test Store', 'aa000000-0000-0000-0000-000000000002', 'RAG_TEST 샤넬 N°5', 'ON_SALE', 250000.00, 30, '클래식한 여성 향수')
ON CONFLICT (id) DO NOTHING;

-- 3. 향수(Perfume) 특화 데이터 - 5개
-- 브랜드/이름에 _TEST 접미사를 붙여 UNIQUE(brand, name) 제약조건 충돌 방지
INSERT INTO p_perfume (id, product_id, brand, name, concentration, main_accords, top_notes, middle_notes, base_notes, notes_score, season_score, day_night_score, gender, sizes, detail_url) VALUES
    -- 1. 샤넬 코코 마드모아젤
    (
        'aa000000-0000-0000-0000-000000000201',
        'aa000000-0000-0000-0000-000000000101',
        'Chanel',
        'Coco Mademoiselle_TEST',
        'Eau de Parfum',
        '["Floral", "Citrus", "Fresh"]',
        '["Orange", "Bergamot", "Grapefruit"]',
        '["Rose", "Jasmine", "Lychee"]',
        '["Vanilla", "Musk", "Vetiver", "Patchouli"]',
        '{"floral": 80.0, "citrus": 75.0, "fresh": 70.0}',
        '{"spring": 60.0, "summer": 55.0, "fall": 50.0, "winter": 35.0}',
        '{"day": 75.0, "night": 55.0}',
        'Female',
        '[{"sizeMl": 50, "price": 200000}, {"sizeMl": 100, "price": 320000}]',
        'https://example.com/chanel-coco'
    ),
    -- 2. 디올 미스 디올
    (
        'aa000000-0000-0000-0000-000000000202',
        'aa000000-0000-0000-0000-000000000102',
        'Dior',
        'Miss Dior_TEST',
        'Eau de Parfum',
        '["Floral", "Rose", "Powdery"]',
        '["Mandarin Orange", "Bergamot"]',
        '["Rose", "Peony", "Lily of the Valley"]',
        '["Musk", "Sandalwood", "Patchouli"]',
        '{"floral": 95.0, "rose": 88.0, "powdery": 70.0}',
        '{"spring": 35.0, "summer": 10.0, "fall": 40.0, "winter": 15.0}',
        '{"day": 70.0, "night": 30.0}',
        'Female',
        '[{"sizeMl": 30, "price": 120000}, {"sizeMl": 50, "price": 180000}]',
        'https://example.com/dior-miss-dior'
    ),
    -- 3. 디올 소바쥬
    (
        'aa000000-0000-0000-0000-000000000203',
        'aa000000-0000-0000-0000-000000000103',
        'Dior',
        'Sauvage_TEST',
        'Eau de Parfum',
        '["Woody", "Aromatic", "Fresh Spicy"]',
        '["Bergamot", "Pepper"]',
        '["Lavender", "Sichuan Pepper", "Geranium"]',
        '["Ambroxan", "Cedar", "Labdanum"]',
        '{"woody": 85.0, "aromatic": 78.0, "fresh_spicy": 72.0}',
        '{"spring": 55.0, "summer": 50.0, "fall": 60.0, "winter": 55.0}',
        '{"day": 80.0, "night": 65.0}',
        'Male',
        '[{"sizeMl": 60, "price": 150000}, {"sizeMl": 100, "price": 200000}]',
        'https://example.com/dior-sauvage'
    ),
    -- 4. 조말론 라임 바질
    (
        'aa000000-0000-0000-0000-000000000204',
        'aa000000-0000-0000-0000-000000000104',
        'Jo Malone',
        'Lime Basil & Mandarin_TEST',
        'Eau de Cologne',
        '["Citrus", "Aromatic", "Green"]',
        '["Lime", "Mandarin Orange", "Bergamot"]',
        '["Basil", "Thyme", "Lilac"]',
        '["Vetiver", "Ambergris"]',
        '{"citrus": 92.0, "aromatic": 80.0, "green": 75.0}',
        '{"spring": 70.0, "summer": 90.0, "fall": 40.0, "winter": 25.0}',
        '{"day": 95.0, "night": 35.0}',
        'Unisex',
        '[{"sizeMl": 30, "price": 95000}, {"sizeMl": 100, "price": 195000}]',
        'https://example.com/jo-malone-lime'
    ),
    -- 5. 샤넬 N°5
    (
        'aa000000-0000-0000-0000-000000000205',
        'aa000000-0000-0000-0000-000000000105',
        'Chanel',
        'N°5_TEST',
        'Eau de Parfum',
        '["Floral", "Aldehydic", "Powdery"]',
        '["Aldehydes", "Ylang-Ylang", "Neroli"]',
        '["Rose", "Jasmine", "Iris"]',
        '["Sandalwood", "Vanilla", "Vetiver", "Musk"]',
        '{"floral": 88.0, "aldehydic": 82.0, "powdery": 75.0}',
        '{"spring": 50.0, "summer": 30.0, "fall": 60.0, "winter": 70.0}',
        '{"day": 60.0, "night": 80.0}',
        'Female',
        '[{"sizeMl": 50, "price": 220000}, {"sizeMl": 100, "price": 350000}]',
        'https://example.com/chanel-no5'
    )
ON CONFLICT (brand, name) DO NOTHING;
