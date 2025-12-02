-- Contract Test Cleanup
-- 이 파일은 Contract Test 실행 후에 로드됩니다.

DELETE FROM p_product WHERE id = '750e8400-e29b-41d4-a716-446655440201';
DELETE FROM p_category WHERE id IN ('750e8400-e29b-41d4-a716-446655440301', '750e8400-e29b-41d4-a716-446655440302');
