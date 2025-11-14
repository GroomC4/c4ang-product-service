-- Cleanup script for Karate Product E2E Test
-- Delete in order to respect foreign key constraints

DELETE FROM p_product_image WHERE product_id IN (
    SELECT id FROM p_product WHERE store_id = 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'
);

DELETE FROM p_product WHERE store_id = 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb';

DELETE FROM p_product_category WHERE id = 'cccccccc-cccc-cccc-cccc-cccccccccccc';

DELETE FROM p_store_rating WHERE store_id = 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb';

DELETE FROM p_store WHERE id = 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb';

DELETE FROM p_user_refresh_token WHERE user_id = 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa';

DELETE FROM p_user_audit WHERE user_id = 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa';

DELETE FROM p_user_profile WHERE user_id = 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa';

DELETE FROM p_user WHERE id = 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa';
