-- =====================================================
-- Seed Data for Product Service (고정 UUID)
-- RAG 서비스의 벡터DB에서 product_id를 논리적 FK로 참조
-- =====================================================

-- 트랜잭션 시작
BEGIN;

-- =====================================================
-- 1. 카테고리 데이터
-- =====================================================
INSERT INTO p_product_category (id, name, parent_category_id, depth) VALUES
    ('a0000000-0000-0000-0000-000000000001', '향수', NULL, 0),
    ('a0000000-0000-0000-0000-000000000002', '여성 향수', 'a0000000-0000-0000-0000-000000000001', 1),
    ('a0000000-0000-0000-0000-000000000003', '남성 향수', 'a0000000-0000-0000-0000-000000000001', 1),
    ('a0000000-0000-0000-0000-000000000004', '유니섹스 향수', 'a0000000-0000-0000-0000-000000000001', 1)
ON CONFLICT (name, parent_category_id) DO NOTHING;

-- =====================================================
-- 2. 스토어 데이터 (가상 스토어)
-- =====================================================
-- 스토어 ID (다른 서비스에서 관리, 여기서는 참조용)
-- store_id: b0000000-0000-0000-0000-000000000001 (C4ANG 향수 전문점)

-- =====================================================
-- 3. 제품(Product) 데이터 - 10개 샘플
-- 이 ID가 RAG 벡터DB에서 참조됨
-- =====================================================
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, description) VALUES
    -- 여성 향수
    ('c0000000-0000-0000-0000-000000000001', 'b0000000-0000-0000-0000-000000000001', 'C4ANG 향수 전문점', 'a0000000-0000-0000-0000-000000000002', '크리드 어벤투스 포 허 오 드 퍼퓸', 'ON_SALE', 255000.00, 50, '여성을 위한 과일향과 플로랄이 조화된 럭셔리 향수'),
    ('c0000000-0000-0000-0000-000000000002', 'b0000000-0000-0000-0000-000000000001', 'C4ANG 향수 전문점', 'a0000000-0000-0000-0000-000000000002', '디올 미스 디올 오 드 퍼퓸', 'ON_SALE', 180000.00, 100, '우아한 플로랄 향수로 로맨틱한 분위기를 연출'),
    ('c0000000-0000-0000-0000-000000000003', 'b0000000-0000-0000-0000-000000000001', 'C4ANG 향수 전문점', 'a0000000-0000-0000-0000-000000000002', '샤넬 코코 마드모아젤 오 드 퍼퓸', 'ON_SALE', 220000.00, 80, '시트러스와 플로랄의 조화로운 여성 향수'),
    ('c0000000-0000-0000-0000-000000000004', 'b0000000-0000-0000-0000-000000000001', 'C4ANG 향수 전문점', 'a0000000-0000-0000-0000-000000000002', '겔랑 랭스땅 드 겔랑 오 드 퍼퓸', 'ON_SALE', 195000.00, 40, '강렬한 매혹의 플로랄 허니 향기'),
    -- 남성 향수
    ('c0000000-0000-0000-0000-000000000005', 'b0000000-0000-0000-0000-000000000001', 'C4ANG 향수 전문점', 'a0000000-0000-0000-0000-000000000003', '크리드 어벤투스 오 드 퍼퓸', 'ON_SALE', 399000.00, 30, '용기와 힘, 비전, 그리고 성공을 기원하는 고급진 향'),
    ('c0000000-0000-0000-0000-000000000006', 'b0000000-0000-0000-0000-000000000001', 'C4ANG 향수 전문점', 'a0000000-0000-0000-0000-000000000003', '톰 포드 오드 우드 오 드 퍼퓸', 'ON_SALE', 249000.00, 45, '청량한 소나무 계열의 향과 부드러운 우디 향수'),
    ('c0000000-0000-0000-0000-000000000007', 'b0000000-0000-0000-0000-000000000001', 'C4ANG 향수 전문점', 'a0000000-0000-0000-0000-000000000003', '블루 드 샤넬 오 드 퍼퓸', 'ON_SALE', 185000.00, 90, '자유로운 남성을 위한 우디 아로마틱 향수'),
    -- 유니섹스 향수
    ('c0000000-0000-0000-0000-000000000008', 'b0000000-0000-0000-0000-000000000001', 'C4ANG 향수 전문점', 'a0000000-0000-0000-0000-000000000004', '조말론 라임 바질 앤 만다린', 'ON_SALE', 195000.00, 120, '상쾌한 시트러스 향이 특징인 여름 향수'),
    ('c0000000-0000-0000-0000-000000000009', 'b0000000-0000-0000-0000-000000000001', 'C4ANG 향수 전문점', 'a0000000-0000-0000-0000-000000000004', '이솝 테싯 오 드 퍼퓸', 'ON_SALE', 135000.00, 60, '따뜻하고 생기넘치며 마음을 릴렉싱 시켜주는 향'),
    ('c0000000-0000-0000-0000-000000000010', 'b0000000-0000-0000-0000-000000000001', 'C4ANG 향수 전문점', 'a0000000-0000-0000-0000-000000000004', '르 라보 상탈 33 오 드 퍼퓸', 'ON_SALE', 320000.00, 25, '샌달우드 베이스의 중독적인 유니섹스 향수')
ON CONFLICT (id) DO NOTHING;

-- =====================================================
-- 4. 향수(Perfume) 특화 데이터 - 10개 샘플
-- product_id는 위 p_product의 id를 논리적 FK로 참조
-- 노트 데이터는 영어로 저장 (API 응답에서 NoteTranslator로 한국어 변환)
-- =====================================================
INSERT INTO p_perfume (id, product_id, brand, name, concentration, main_accords, top_notes, middle_notes, base_notes, notes_score, season_score, day_night_score, gender, sizes, detail_url) VALUES
    -- 1. 크리드 어벤투스 포 허
    (
        'd0000000-0000-0000-0000-000000000001',
        'c0000000-0000-0000-0000-000000000001',
        'Creed',
        'Aventus for Her',
        'Eau de Parfum',
        '["Floral", "Fruity", "Musky"]',
        '["Green Apple", "Pink Pepper", "Bergamot", "Lemon"]',
        '["Rose", "Peach", "Styrax", "Ylang-Ylang"]',
        '["Musk", "Sandalwood", "Amber"]',
        '{"floral": 85.0, "fruity": 72.0, "musky": 65.0, "fresh": 58.0}',
        '{"spring": 75.0, "summer": 60.0, "fall": 45.0, "winter": 30.0}',
        '{"day": 80.0, "night": 50.0}',
        'Female',
        '[{"sizeMl": 50, "price": 255000}, {"sizeMl": 75, "price": 340000}]',
        'https://www.bysuco.com/product/show/creed-aventus-for-her'
    ),
    -- 2. 디올 미스 디올
    (
        'd0000000-0000-0000-0000-000000000002',
        'c0000000-0000-0000-0000-000000000002',
        'Dior',
        'Miss Dior',
        'Eau de Parfum',
        '["Floral", "Rose", "Powdery"]',
        '["Mandarin Orange", "Bergamot"]',
        '["Rose", "Peony", "Lily of the Valley"]',
        '["Musk", "Sandalwood", "Patchouli"]',
        '{"floral": 95.0, "rose": 88.0, "powdery": 70.0, "fresh": 55.0}',
        '{"spring": 35.0, "summer": 10.0, "fall": 40.0, "winter": 15.0}',
        '{"day": 70.0, "night": 30.0}',
        'Female',
        '[{"sizeMl": 30, "price": 120000}, {"sizeMl": 50, "price": 180000}, {"sizeMl": 100, "price": 280000}]',
        'https://www.bysuco.com/product/show/dior-miss-dior'
    ),
    -- 3. 샤넬 코코 마드모아젤
    (
        'd0000000-0000-0000-0000-000000000003',
        'c0000000-0000-0000-0000-000000000003',
        'Chanel',
        'Coco Mademoiselle',
        'Eau de Parfum',
        '["Floral", "Citrus", "Fresh"]',
        '["Orange", "Bergamot", "Grapefruit"]',
        '["Rose", "Jasmine", "Lychee"]',
        '["Vanilla", "Musk", "Vetiver", "Patchouli"]',
        '{"floral": 80.0, "citrus": 75.0, "fresh": 70.0, "sweet": 55.0}',
        '{"spring": 60.0, "summer": 55.0, "fall": 50.0, "winter": 35.0}',
        '{"day": 75.0, "night": 55.0}',
        'Female',
        '[{"sizeMl": 50, "price": 200000}, {"sizeMl": 100, "price": 320000}]',
        'https://www.bysuco.com/product/show/chanel-coco-mademoiselle'
    ),
    -- 4. 겔랑 랭스땅 드 겔랑
    (
        'd0000000-0000-0000-0000-000000000004',
        'c0000000-0000-0000-0000-000000000004',
        'Guerlain',
        'L''Instant de Guerlain',
        'Eau de Parfum',
        '["Floral", "Honey", "Sweet"]',
        '["Orange", "Honey", "Bergamot", "Mandarin Orange"]',
        '["Ylang-Ylang", "Magnolia", "Jasmine"]',
        '["Amber", "Sandalwood"]',
        '{"floral": 90.0, "honey": 82.0, "sweet": 78.0, "woody": 60.0}',
        '{"spring": 24.1, "summer": 3.8, "fall": 95.5, "winter": 14.2}',
        '{"day": 47.1, "night": 25.9}',
        'Female',
        '[{"sizeMl": 75, "price": 195000}]',
        'https://www.bysuco.com/product/show/22689'
    ),
    -- 5. 크리드 어벤투스 (남성)
    (
        'd0000000-0000-0000-0000-000000000005',
        'c0000000-0000-0000-0000-000000000005',
        'Creed',
        'Aventus',
        'Eau de Parfum',
        '["Fruity", "Sweet", "Leather"]',
        '["Bergamot", "Black Currant", "Apple", "Lemon", "Pink Pepper"]',
        '["Pineapple", "Patchouli", "Jasmine"]',
        '["Birch", "Musk", "Oak Moss", "Ambroxan", "Cedar"]',
        '{"fruity": 88.0, "sweet": 75.0, "leather": 68.0, "woody": 72.0}',
        '{"spring": 65.0, "summer": 70.0, "fall": 55.0, "winter": 40.0}',
        '{"day": 85.0, "night": 60.0}',
        'Male',
        '[{"sizeMl": 50, "price": 255000}, {"sizeMl": 100, "price": 399000}]',
        'https://www.bysuco.com/product/show/9370'
    ),
    -- 6. 톰 포드 오드 우드
    (
        'd0000000-0000-0000-0000-000000000006',
        'c0000000-0000-0000-0000-000000000006',
        'Tom Ford',
        'Oud Wood',
        'Eau de Parfum',
        '["Woody", "Oud", "Warm Spicy"]',
        '["Cardamom", "Sichuan Pepper"]',
        '["Oud", "Sandalwood", "Rosewood"]',
        '["Vetiver", "Tonka Bean", "Amber", "Vanilla"]',
        '{"woody": 95.0, "oud": 88.0, "warm_spicy": 72.0, "smoky": 60.0}',
        '{"spring": 20.0, "summer": 10.0, "fall": 75.0, "winter": 90.0}',
        '{"day": 35.0, "night": 90.0}',
        'Male',
        '[{"sizeMl": 30, "price": 179000}, {"sizeMl": 50, "price": 249000}, {"sizeMl": 100, "price": 399000}]',
        'https://www.bysuco.com/product/show/10716'
    ),
    -- 7. 블루 드 샤넬
    (
        'd0000000-0000-0000-0000-000000000007',
        'c0000000-0000-0000-0000-000000000007',
        'Chanel',
        'Bleu de Chanel',
        'Eau de Parfum',
        '["Woody", "Aromatic", "Fresh Spicy"]',
        '["Lemon", "Mint", "Pink Pepper", "Grapefruit"]',
        '["Ginger", "Nutmeg", "Jasmine", "Iso E Super"]',
        '["Sandalwood", "Cedar", "Vetiver", "Incense"]',
        '{"woody": 85.0, "aromatic": 78.0, "fresh_spicy": 72.0, "citrus": 65.0}',
        '{"spring": 55.0, "summer": 50.0, "fall": 60.0, "winter": 55.0}',
        '{"day": 80.0, "night": 65.0}',
        'Male',
        '[{"sizeMl": 50, "price": 145000}, {"sizeMl": 100, "price": 185000}, {"sizeMl": 150, "price": 225000}]',
        'https://www.bysuco.com/product/show/bleu-de-chanel'
    ),
    -- 8. 조말론 라임 바질 앤 만다린 (유니섹스)
    (
        'd0000000-0000-0000-0000-000000000008',
        'c0000000-0000-0000-0000-000000000008',
        'Jo Malone',
        'Lime Basil & Mandarin',
        'Eau de Cologne',
        '["Citrus", "Aromatic", "Green"]',
        '["Lime", "Mandarin Orange", "Bergamot"]',
        '["Basil", "Thyme", "Lilac"]',
        '["Vetiver", "Ambergris"]',
        '{"citrus": 92.0, "aromatic": 80.0, "green": 75.0, "fresh": 88.0}',
        '{"spring": 70.0, "summer": 90.0, "fall": 40.0, "winter": 25.0}',
        '{"day": 95.0, "night": 35.0}',
        'Unisex',
        '[{"sizeMl": 30, "price": 95000}, {"sizeMl": 100, "price": 195000}]',
        'https://www.bysuco.com/product/show/jo-malone-lime-basil'
    ),
    -- 9. 이솝 테싯
    (
        'd0000000-0000-0000-0000-000000000009',
        'c0000000-0000-0000-0000-000000000009',
        'Aesop',
        'Tacit',
        'Eau de Parfum',
        '["Citrus", "Aromatic", "Fresh Spicy"]',
        '["Yuzu", "Citrus"]',
        '["Basil"]',
        '["Vetiver", "Clove"]',
        '{"citrus": 85.0, "aromatic": 78.0, "fresh_spicy": 70.0, "green": 65.0}',
        '{"spring": 60.0, "summer": 75.0, "fall": 50.0, "winter": 35.0}',
        '{"day": 85.0, "night": 40.0}',
        'Unisex',
        '[{"sizeMl": 50, "price": 135000}]',
        'https://www.bysuco.com/product/show/9970'
    ),
    -- 10. 르 라보 상탈 33
    (
        'd0000000-0000-0000-0000-000000000010',
        'c0000000-0000-0000-0000-000000000010',
        'Le Labo',
        'Santal 33',
        'Eau de Parfum',
        '["Woody", "Leather", "Aromatic"]',
        '["Cardamom", "Iris", "Violet"]',
        '["Ambrox", "Iso E Super", "Australian Sandalwood"]',
        '["Cedarwood", "Leather", "Musk"]',
        '{"woody": 98.0, "leather": 85.0, "aromatic": 72.0, "smoky": 68.0}',
        '{"spring": 45.0, "summer": 35.0, "fall": 70.0, "winter": 80.0}',
        '{"day": 60.0, "night": 80.0}',
        'Unisex',
        '[{"sizeMl": 50, "price": 270000}, {"sizeMl": 100, "price": 320000}]',
        'https://www.bysuco.com/product/show/le-labo-santal-33'
    )
ON CONFLICT (id) DO NOTHING;

-- =====================================================
-- 5. 노트 이미지 데이터 - 주요 노트 30개 샘플
-- =====================================================
INSERT INTO p_note_image (id, category, note_name, image_url) VALUES
    -- Citrus
    ('e0000000-0000-0000-0000-000000000001', 'Citrus Smells', 'Bergamot', 'https://fimgs.net/mdimg/sastojci/m.75.jpg'),
    ('e0000000-0000-0000-0000-000000000002', 'Citrus Smells', 'Lemon', 'https://fimgs.net/mdimg/sastojci/m.77.jpg'),
    ('e0000000-0000-0000-0000-000000000003', 'Citrus Smells', 'Lime', 'https://fimgs.net/mdimg/sastojci/m.78.jpg'),
    ('e0000000-0000-0000-0000-000000000004', 'Citrus Smells', 'Orange', 'https://fimgs.net/mdimg/sastojci/m.80.jpg'),
    ('e0000000-0000-0000-0000-000000000005', 'Citrus Smells', 'Mandarin Orange', 'https://fimgs.net/mdimg/sastojci/m.82.jpg'),
    ('e0000000-0000-0000-0000-000000000006', 'Citrus Smells', 'Grapefruit', 'https://fimgs.net/mdimg/sastojci/m.76.jpg'),
    ('e0000000-0000-0000-0000-000000000007', 'Citrus Smells', 'Yuzu', 'https://fimgs.net/mdimg/sastojci/m.83.jpg'),
    ('e0000000-0000-0000-0000-000000000008', 'Citrus Smells', 'Neroli', 'https://fimgs.net/mdimg/sastojci/m.17.jpg'),
    -- Flowers
    ('e0000000-0000-0000-0000-000000000009', 'Flowers', 'Rose', 'https://fimgs.net/mdimg/sastojci/m.4.jpg'),
    ('e0000000-0000-0000-0000-000000000010', 'Flowers', 'Jasmine', 'https://fimgs.net/mdimg/sastojci/m.21.jpg'),
    ('e0000000-0000-0000-0000-000000000011', 'Flowers', 'Peony', 'https://fimgs.net/mdimg/sastojci/m.30.jpg'),
    ('e0000000-0000-0000-0000-000000000012', 'Flowers', 'Ylang-Ylang', 'https://fimgs.net/mdimg/sastojci/m.15.jpg'),
    ('e0000000-0000-0000-0000-000000000013', 'Flowers', 'Lily of the Valley', 'https://fimgs.net/mdimg/sastojci/m.28.jpg'),
    ('e0000000-0000-0000-0000-000000000014', 'Flowers', 'Magnolia', 'https://fimgs.net/mdimg/sastojci/m.35.jpg'),
    ('e0000000-0000-0000-0000-000000000015', 'Flowers', 'Iris', 'https://fimgs.net/mdimg/sastojci/m.25.jpg'),
    ('e0000000-0000-0000-0000-000000000016', 'Flowers', 'Violet', 'https://fimgs.net/mdimg/sastojci/m.36.jpg'),
    -- Woods
    ('e0000000-0000-0000-0000-000000000017', 'Woods', 'Sandalwood', 'https://fimgs.net/mdimg/sastojci/m.249.jpg'),
    ('e0000000-0000-0000-0000-000000000018', 'Woods', 'Cedar', 'https://fimgs.net/mdimg/sastojci/m.250.jpg'),
    ('e0000000-0000-0000-0000-000000000019', 'Woods', 'Vetiver', 'https://fimgs.net/mdimg/sastojci/m.262.jpg'),
    ('e0000000-0000-0000-0000-000000000020', 'Woods', 'Oud', 'https://fimgs.net/mdimg/sastojci/m.269.jpg'),
    ('e0000000-0000-0000-0000-000000000021', 'Woods', 'Patchouli', 'https://fimgs.net/mdimg/sastojci/m.259.jpg'),
    ('e0000000-0000-0000-0000-000000000022', 'Woods', 'Birch', 'https://fimgs.net/mdimg/sastojci/m.251.jpg'),
    -- Spices
    ('e0000000-0000-0000-0000-000000000023', 'Spices', 'Cardamom', 'https://fimgs.net/mdimg/sastojci/m.181.jpg'),
    ('e0000000-0000-0000-0000-000000000024', 'Spices', 'Pink Pepper', 'https://fimgs.net/mdimg/sastojci/m.183.jpg'),
    ('e0000000-0000-0000-0000-000000000025', 'Spices', 'Clove', 'https://fimgs.net/mdimg/sastojci/m.186.jpg'),
    ('e0000000-0000-0000-0000-000000000026', 'Spices', 'Ginger', 'https://fimgs.net/mdimg/sastojci/m.189.jpg'),
    -- Musk & Others
    ('e0000000-0000-0000-0000-000000000027', 'Musks', 'Musk', 'https://fimgs.net/mdimg/sastojci/m.91.jpg'),
    ('e0000000-0000-0000-0000-000000000028', 'Musks', 'Amber', 'https://fimgs.net/mdimg/sastojci/m.294.jpg'),
    ('e0000000-0000-0000-0000-000000000029', 'Sweet', 'Vanilla', 'https://fimgs.net/mdimg/sastojci/m.55.jpg'),
    ('e0000000-0000-0000-0000-000000000030', 'Sweet', 'Tonka Bean', 'https://fimgs.net/mdimg/sastojci/m.194.jpg')
ON CONFLICT (category, note_name) DO NOTHING;

-- 트랜잭션 커밋
COMMIT;

-- =====================================================
-- UUID 참조 가이드 (RAG 벡터DB 연동용)
-- =====================================================
--
-- [카테고리 ID]
-- a0000000-0000-0000-0000-000000000001 : 향수 (루트)
-- a0000000-0000-0000-0000-000000000002 : 여성 향수
-- a0000000-0000-0000-0000-000000000003 : 남성 향수
-- a0000000-0000-0000-0000-000000000004 : 유니섹스 향수
--
-- [스토어 ID]
-- b0000000-0000-0000-0000-000000000001 : C4ANG 향수 전문점
--
-- [제품(Product) ID] - 벡터DB에서 참조할 ID
-- c0000000-0000-0000-0000-000000000001 : 크리드 어벤투스 포 허 (Female)
-- c0000000-0000-0000-0000-000000000002 : 디올 미스 디올 (Female)
-- c0000000-0000-0000-0000-000000000003 : 샤넬 코코 마드모아젤 (Female)
-- c0000000-0000-0000-0000-000000000004 : 겔랑 랭스땅 드 겔랑 (Female)
-- c0000000-0000-0000-0000-000000000005 : 크리드 어벤투스 (Male)
-- c0000000-0000-0000-0000-000000000006 : 톰 포드 오드 우드 (Male)
-- c0000000-0000-0000-0000-000000000007 : 블루 드 샤넬 (Male)
-- c0000000-0000-0000-0000-000000000008 : 조말론 라임 바질 앤 만다린 (Unisex)
-- c0000000-0000-0000-0000-000000000009 : 이솝 테싯 (Unisex)
-- c0000000-0000-0000-0000-000000000010 : 르 라보 상탈 33 (Unisex)
--
-- [향수(Perfume) ID]
-- d0000000-0000-0000-0000-000000000001 ~ d0000000-0000-0000-0000-000000000010
--
-- [노트 이미지 ID]
-- e0000000-0000-0000-0000-000000000001 ~ e0000000-0000-0000-0000-000000000030
-- =====================================================
