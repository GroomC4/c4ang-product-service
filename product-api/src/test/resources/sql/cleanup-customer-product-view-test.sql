-- ============================================================
-- Cleanup script for Customer Product View E2E Test
-- 테스트 데이터를 역순으로 정리 (외래키 제약조건 고려)
-- ============================================================

-- Product images (자식 테이블)
DELETE FROM p_product_image WHERE product_id IN (
    SELECT id FROM p_product WHERE id::text LIKE '55555555-5555-5555-5555-5555555555%'
);

-- Products (부모 테이블)
DELETE FROM p_product WHERE id::text LIKE '55555555-5555-5555-5555-5555555555%';

-- Product categories
DELETE FROM p_product_category WHERE id = '44444444-4444-4444-4444-44444444441b';
DELETE FROM p_product_category WHERE id = '44444444-4444-4444-4444-444444444441';

-- Store ratings (자식 테이블)
DELETE FROM p_store_rating WHERE store_id = '33333333-3333-3333-3333-333333333331';

-- Stores (부모 테이블)
DELETE FROM p_store WHERE id = '33333333-3333-3333-3333-333333333331';

-- User profiles (자식 테이블)
DELETE FROM p_user_profile WHERE user_id = '22222222-2222-2222-2222-222222222221';

-- Users (부모 테이블 - 판매자)
DELETE FROM p_user WHERE id = '22222222-2222-2222-2222-222222222221';
