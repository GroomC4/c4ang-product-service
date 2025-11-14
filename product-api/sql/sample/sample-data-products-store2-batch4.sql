-- ========================================
-- 상품 데이터 - 2번째 판매자(패션플러스) 상품 31~40
-- ========================================

-- 상품 31-40 (패션플러스 - 기타 패션아이템)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 31. 여성 부츠
('a3200003-1111-1111-1111-111111111111'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-3333-3333-3333-333333333333'::uuid,
 'Stuart Weitzman 5050 오버더니 부츠 블랙', 'ON_SALE', 1290000, 15,
 'https://www.stuartweitzman.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-STW-master-catalog/default/dw3e3e3e3e/images/5050/BLACK/5050_BLACK_MODEL.jpg',
 '아이코닉한 오버더니 부츠. 신축성 있는 나파 가죽과 5050 실루엣으로 다리 라인을 아름답게 연출하는 스튜어트 와이츠먼의 베스트셀러입니다.',
 '2024-03-31 10:00:00+09', '2024-03-31 10:00:00+09'),

-- 32. 남성 코트
('a3200003-2222-2222-2222-222222222222'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-1111-1111-1111-111111111111'::uuid,
 'Ermenegildo Zegna 캐시미어 코트 네이비', 'ON_SALE', 5900000, 6,
 'https://www.zegna.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-zegna-master/default/dw3e3e3e3e/images/8RCT32_7CJB10_N01_A.jpg',
 '이탈리아 장인정신의 정수. 100% 캐시미어 소재와 완벽한 테일러링으로 제작된 프리미엄 코트입니다.',
 '2024-04-01 10:00:00+09', '2024-04-01 10:00:00+09'),

-- 33. 여성 점퍼
('a3200003-3333-3333-3333-333333333333'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-2222-2222-2222-222222222222'::uuid,
 'The North Face Nuptse 700 다운 재킷 블랙', 'ON_SALE', 489000, 35,
 'https://images.thenorthface.com/is/image/TheNorthFace/NF0A3C8D_JK3_hero?wid=1200&hei=1200',
 '90년대 클래식 넙시 재킷의 재해석. 700필 다운과 아이코닉한 박스 퀼팅 디자인으로 스타일과 보온성을 동시에 제공합니다.',
 '2024-04-02 10:00:00+09', '2024-04-02 10:00:00+09'),

-- 34. 남성 데님
('a3200003-4444-4444-4444-444444444444'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-1111-1111-1111-111111111111'::uuid,
 'Levi''s 501 오리지널 진 다크 인디고', 'ON_SALE', 139000, 60,
 'https://lsco.scene7.com/is/image/lsco/005010114-front-pdp?fmt=jpeg&qlt=70&resMode=sharp2&fit=crop,1&op_usm=0.6,0.6,8&wid=2000&hei=1840',
 '진의 원조 리바이스 501. 1873년부터 변함없는 클래식 핏과 버튼 플라이로 진정한 데님 헤리티지를 경험하세요.',
 '2024-04-03 10:00:00+09', '2024-04-03 10:00:00+09'),

-- 35. 여성 청바지
('a3200003-5555-5555-5555-555555555555'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-2222-2222-2222-222222222222'::uuid,
 'Frame Le High Skinny 스키니진 누와 블랙', 'ON_SALE', 329000, 40,
 'https://frame-store.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-frame-master/default/dw3e3e3e3e/LHSKNY218_NWR_FR.jpg',
 'LA 프리미엄 데님 브랜드 프레임. 하이라이즈와 슬림 핏으로 다리 라인을 길어보이게 하는 스키니진입니다.',
 '2024-04-04 10:00:00+09', '2024-04-04 10:00:00+09'),

-- 36. 남성 후드티
('a3200003-6666-6666-6666-666666666666'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-1111-1111-1111-111111111111'::uuid,
 'Supreme Box Logo 후드 그레이', 'ON_SALE', 890000, 10,
 'https://assets.supremenewyork.com/191371/vi/vTyFUx1A8hs.jpg',
 '스트릿 패션의 성배 슈프림 박스로고. 헤비 코튼 소재와 캥거루 포켓, 아이코닉한 박스로고가 특징인 컬렉터 아이템입니다.',
 '2024-04-05 10:00:00+09', '2024-04-05 10:00:00+09'),

-- 37. 여성 가디건
('a3200003-7777-7777-7777-777777777777'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-2222-2222-2222-222222222222'::uuid,
 'Acne Studios 모헤어 카디건 베이비 핑크', 'ON_SALE', 890000, 20,
 'https://www.acnestudios.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-acne-product/default/dw3e3e3e3e/images/A60197/A60197-BGE_A.jpg',
 '스칸디나비안 미니멀리즘의 정수. 모헤어 혼방 소재와 오버사이즈 핏으로 편안하면서도 세련된 룩을 연출합니다.',
 '2024-04-06 10:00:00+09', '2024-04-06 10:00:00+09'),

-- 38. 남성 트레이닝복
('a3200003-8888-8888-8888-888888888888'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-1111-1111-1111-111111111111'::uuid,
 'Nike Tech Fleece 조거팬츠 블랙', 'ON_SALE', 149000, 70,
 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/tech-fleece-joggers-lpx0Hm.png',
 'Tech Fleece 혁신 기술의 조거팬츠. 경량 보온성과 슬림 테이퍼드 핏으로 스타일리시한 애슬레저 룩을 완성합니다.',
 '2024-04-07 10:00:00+09', '2024-04-07 10:00:00+09'),

-- 39. 여성 레깅스
('a3200003-9999-9999-9999-999999999999'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-2222-2222-2222-222222222222'::uuid,
 'Lululemon Align High-Rise 레깅스 25" 블랙', 'ON_SALE', 138000, 55,
 'https://images.lululemon.com/is/image/lululemon/LW5CUXS_0001_1?wid=1080&op_usm=0.5,2,10,0',
 'Nulu 패브릭의 부드러운 착용감. 하이라이즈와 4-way 스트레치로 요가부터 데일리까지 완벽한 레깅스입니다.',
 '2024-04-08 10:00:00+09', '2024-04-08 10:00:00+09'),

-- 40. 남녀공용 캡
('a3200004-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'Carhartt WIP 로고 캡 블랙', 'ON_SALE', 69000, 90,
 'https://www.carhartt-wip.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-carhartt-wip-master/default/dw3e3e3e3e/images/I022782/I022782_8900_XX_1.jpg',
 '워크웨어 헤리티지 브랜드 칼하트 WIP. 견고한 캔버스 소재와 조절 가능한 스트랩으로 스트릿 스타일의 필수 아이템입니다.',
 '2024-04-09 10:00:00+09', '2024-04-09 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1942003-1111-1111-1111-111111111111'::uuid, 'a3200003-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://www.stuartweitzman.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-STW-master-catalog/default/dw3e3e3e3e/images/5050/BLACK/5050_BLACK_MODEL.jpg', 0, '2024-03-31 10:00:00+09', '2024-03-31 10:00:00+09'),
('a1942003-2222-2222-2222-111111111111'::uuid, 'a3200003-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.zegna.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-zegna-master/default/dw3e3e3e3e/images/8RCT32_7CJB10_N01_A.jpg', 0, '2024-04-01 10:00:00+09', '2024-04-01 10:00:00+09'),
('a1942003-3333-3333-3333-111111111111'::uuid, 'a3200003-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://images.thenorthface.com/is/image/TheNorthFace/NF0A3C8D_JK3_hero?wid=1200&hei=1200', 0, '2024-04-02 10:00:00+09', '2024-04-02 10:00:00+09'),
('a1942003-4444-4444-4444-111111111111'::uuid, 'a3200003-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://lsco.scene7.com/is/image/lsco/005010114-front-pdp?fmt=jpeg&qlt=70&resMode=sharp2&fit=crop,1&op_usm=0.6,0.6,8&wid=2000&hei=1840', 0, '2024-04-03 10:00:00+09', '2024-04-03 10:00:00+09'),
('a1942003-5555-5555-5555-111111111111'::uuid, 'a3200003-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://frame-store.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-frame-master/default/dw3e3e3e3e/LHSKNY218_NWR_FR.jpg', 0, '2024-04-04 10:00:00+09', '2024-04-04 10:00:00+09'),
('a1942003-6666-6666-6666-111111111111'::uuid, 'a3200003-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://assets.supremenewyork.com/191371/vi/vTyFUx1A8hs.jpg', 0, '2024-04-05 10:00:00+09', '2024-04-05 10:00:00+09'),
('a1942003-7777-7777-7777-111111111111'::uuid, 'a3200003-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.acnestudios.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-acne-product/default/dw3e3e3e3e/images/A60197/A60197-BGE_A.jpg', 0, '2024-04-06 10:00:00+09', '2024-04-06 10:00:00+09'),
('a1942003-8888-8888-8888-111111111111'::uuid, 'a3200003-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/tech-fleece-joggers-lpx0Hm.png', 0, '2024-04-07 10:00:00+09', '2024-04-07 10:00:00+09'),
('a1942003-9999-9999-9999-111111111111'::uuid, 'a3200003-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://images.lululemon.com/is/image/lululemon/LW5CUXS_0001_1?wid=1080&op_usm=0.5,2,10,0', 0, '2024-04-08 10:00:00+09', '2024-04-08 10:00:00+09'),
('a1942004-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3200004-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.carhartt-wip.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-carhartt-wip-master/default/dw3e3e3e3e/images/I022782/I022782_8900_XX_1.jpg', 0, '2024-04-09 10:00:00+09', '2024-04-09 10:00:00+09');