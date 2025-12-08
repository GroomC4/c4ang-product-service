-- RAG Internal Controller 통합 테스트 데이터 정리
DELETE FROM p_perfume WHERE id::text LIKE 'aa000000%';
DELETE FROM p_product WHERE id::text LIKE 'aa000000%';
DELETE FROM p_product_category WHERE id::text LIKE 'aa000000%';
