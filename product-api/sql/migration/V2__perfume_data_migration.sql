-- =====================================================
-- Perfume Data Migration Script
-- Source: scentlab (MySQL) -> product-service (PostgreSQL)
-- =====================================================

-- 1. 향수 카테고리 생성 (루트 카테고리)
INSERT INTO p_product_category (id, name, parent_category_id, depth, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111111111', '향수', NULL, 0, now(), now())
ON CONFLICT (name, parent_category_id) DO NOTHING;

-- 2. 향수 전용 스토어 ID (가상 스토어)
-- 실제 환경에서는 스토어 서비스에서 생성된 ID 사용
-- store_id: 22222222-2222-2222-2222-222222222222
-- store_name: '향수 스토어'

-- =====================================================
-- 아래 INSERT 문은 기존 perfumes 데이터를 변환한 예시입니다.
-- 실제 마이그레이션 시에는 Python/Kotlin 스크립트로
-- MySQL에서 데이터를 읽어 PostgreSQL로 삽입합니다.
-- =====================================================

-- 샘플 데이터 (테스트용)
-- p_product 삽입
INSERT INTO p_product (
    id, store_id, store_name, category_id, product_name,
    status, price, stock_quantity, thumbnail_url, description,
    created_at, updated_at
) VALUES (
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    '22222222-2222-2222-2222-222222222222',
    '향수 스토어',
    '11111111-1111-1111-1111-111111111111',
    '겔랑 랭스땅 드 겔랑 오 드 퍼퓸',
    'ON_SALE',
    0,
    999,
    NULL,
    '강렬한 매혹의 향기.',
    now(),
    now()
) ON CONFLICT (id) DO NOTHING;

-- p_perfume 삽입
INSERT INTO p_perfume (
    id, product_id, brand, name, concentration,
    main_accords, top_notes, middle_notes, base_notes,
    notes_score, season_score, day_night_score,
    gender, sizes, detail_url,
    created_at, updated_at
) VALUES (
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    '겔랑',
    '랭스땅 드 겔랑 오 드 퍼퓸',
    '오 드 퍼퓸',
    '["플로랄", "허니", "스위트"]'::jsonb,
    '["오렌지", "허니", "베르가못", "만다린", "오렌지"]'::jsonb,
    '["일랑일랑", "매그놀리아", "자스민"]'::jsonb,
    '["앰버", "샌달우드"]'::jsonb,
    '{"rose": 100.0, "oud": 97.2, "woody": 67.1, "floral": 65.6, "herbal": 59.2, "spicy": 50.7, "leather": 46.5, "green": 46.0}'::jsonb,
    '{"winter": 14.2, "spring": 24.1, "summer": 3.8, "fall": 95.5}'::jsonb,
    '{"day": 47.1, "night": 25.9}'::jsonb,
    'Female',
    '[75]'::jsonb,
    'https://www.bysuco.com/product/show/22689',
    now(),
    now()
) ON CONFLICT (brand, name) DO NOTHING;

-- =====================================================
-- 노트 이미지 샘플 데이터
-- =====================================================
INSERT INTO p_note_image (id, category, note_name, image_url, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'Citrus Smells', 'Bergamot', 'https://fimgs.net/mdimg/sastojci/m.75.jpg', now(), now()),
    (gen_random_uuid(), 'Citrus Smells', 'Lemon', 'https://fimgs.net/mdimg/sastojci/m.77.jpg', now(), now()),
    (gen_random_uuid(), 'Citrus Smells', 'Orange', 'https://fimgs.net/mdimg/sastojci/m.80.jpg', now(), now()),
    (gen_random_uuid(), 'Citrus Smells', 'Neroli', 'https://fimgs.net/mdimg/sastojci/m.17.jpg', now(), now())
ON CONFLICT (category, note_name) DO NOTHING;
