-- ========================================
-- 상품 데이터 - 2번째 판매자(패션플러스) 상품 1~10
-- ========================================

-- 상품 1-10 (패션플러스 - 남성의류, 여성의류)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 1. 남성 아우터
('a3200000-1111-1111-1111-111111111111'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-1111-1111-1111-111111111111'::uuid,
 '캐나다구스 맥밀란 파카 블랙 라벨 네이비', 'ON_SALE', 1890000, 8,
 'https://images.canadagoose.com/image/upload/w_480,c_scale,f_auto,q_auto:best/v1694024551/product-image/5080M_61.jpg',
 '극한의 추위를 이겨내는 프리미엄 다운 파카. 625 필파워 화이트 덕 다운과 Arctic Tech 패브릭으로 최고의 보온성을 제공합니다.',
 '2024-03-01 10:00:00+09', '2024-03-01 10:00:00+09'),

-- 2. 남성 니트
('a3200000-2222-2222-2222-222222222222'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-1111-1111-1111-111111111111'::uuid,
 '랄프로렌 케이블 니트 스웨터 네이비', 'ON_SALE', 289000, 25,
 'https://www.ralphlauren.asia/on/demandware.static/-/Sites-rl-catalog/default/dw3e8e8e8e/polo-ralph-lauren/710766772/original/710766772_cf23.jpg',
 '타임리스한 케이블 니트 디자인. 100% 코튼 원단으로 제작되어 부드럽고 따뜻한 착용감을 제공하는 클래식 니트웨어입니다.',
 '2024-03-02 10:00:00+09', '2024-03-02 10:00:00+09'),

-- 3. 남성 셔츠
('a3200000-3333-3333-3333-333333333333'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-1111-1111-1111-111111111111'::uuid,
 '톰브라운 옥스포드 클래식 롱슬리브 셔츠 화이트', 'ON_SALE', 520000, 18,
 'https://thombrowne.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-tb-master-catalog/default/dw3e8e8e8e/images/MWL010A-00139-100/MWL010A-00139-100_2.jpg',
 '시그니처 트리콜로 디테일이 돋보이는 옥스포드 셔츠. 슬림 핏과 프리미엄 코튼으로 세련된 비즈니스룩을 완성합니다.',
 '2024-03-03 10:00:00+09', '2024-03-03 10:00:00+09'),

-- 4. 남성 팬츠
('a3200000-4444-4444-4444-444444444444'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-1111-1111-1111-111111111111'::uuid,
 '구찌 GG 로고 조거 팬츠 블랙', 'ON_SALE', 1490000, 12,
 'https://media.gucci.com/style/DarkGray_Center_0_0_800x800/1672926387/645897_XJDQ0_1000_001_100_0000_Light.jpg',
 'GG 로고 테이프와 웹 스트라이프가 특징인 럭셔리 조거팬츠. 부드러운 코튼 저지 소재로 편안함과 스타일을 동시에 제공합니다.',
 '2024-03-04 10:00:00+09', '2024-03-04 10:00:00+09'),

-- 5. 여성 아우터
('a3200000-5555-5555-5555-555555555555'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-2222-2222-2222-222222222222'::uuid,
 '몽클레어 헤르미퐁 롱 다운 재킷 블랙', 'ON_SALE', 2890000, 6,
 'https://store.moncler.com/on/demandware.static/-/Sites-moncler-master/default/dw8e8e8e8e/images/prd/1B52200C0455/999/1B52200C0455_999_1.jpg',
 '시그니처 퀼팅 디자인의 롱 다운재킷. 90% 화이트 구스 다운으로 극한의 보온성을 제공하며, 슬림한 실루엣으로 우아함을 더했습니다.',
 '2024-03-05 10:00:00+09', '2024-03-05 10:00:00+09'),

-- 6. 여성 원피스
('a3200000-6666-6666-6666-666666666666'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-2222-2222-2222-222222222222'::uuid,
 '버버리 체크 실크 미디 드레스 베이지', 'ON_SALE', 3200000, 10,
 'https://assets.burberry.com/is/image/Burberryltd/3E8E8E8E-8E8E-8E8E-8E8E-8E8E8E8E8E8E?$BBY_V2_SL_1x1$&wid=1278',
 '아이코닉한 빈티지 체크 패턴의 실크 드레스. A라인 실루엣과 벨트 디테일로 우아하고 여성스러운 룩을 완성합니다.',
 '2024-03-06 10:00:00+09', '2024-03-06 10:00:00+09'),

-- 7. 여성 블라우스
('a3200000-7777-7777-7777-777777777777'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-2222-2222-2222-222222222222'::uuid,
 '생로랑 라발리에르 보우 실크 블라우스 아이보리', 'ON_SALE', 1890000, 15,
 'https://www.ysl.com/dw/image/v2/BGWJ_PRD/on/demandware.static/-/Sites-masterCatalog_YSL/default/dw8e8e8e8e/assets/Y/6/8/1/Y681677Y011N9601_A.jpg',
 '시그니처 보우 디테일의 럭셔리 실크 블라우스. 100% 실크 소재와 우아한 드레이프로 파리지엥 시크를 완성합니다.',
 '2024-03-07 10:00:00+09', '2024-03-07 10:00:00+09'),

-- 8. 여성 스커트
('a3200000-8888-8888-8888-888888888888'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-2222-2222-2222-222222222222'::uuid,
 'DIOR 플리츠 미디 스커트 네이비', 'ON_SALE', 4200000, 8,
 'https://www.dior.com/couture/ecommerce/media/catalog/product/cache/1/cover_image_mobile_1/715x773/0/0/0000000000000000.jpg',
 '섬세한 플리츠 가공의 미디 스커트. CD 시그니처 디테일과 프리미엄 울 소재로 우아하고 세련된 실루엣을 선사합니다.',
 '2024-03-08 10:00:00+09', '2024-03-08 10:00:00+09'),

-- 9. 여성 니트
('a3200000-9999-9999-9999-999999999999'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-2222-2222-2222-222222222222'::uuid,
 '막스마라 캐시미어 터틀넥 스웨터 카멜', 'ON_SALE', 1290000, 20,
 'https://it.maxmara.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-maxmara-master/default/dw8e8e8e8e/images/1/1364311206000_1_G.jpg',
 '100% 캐시미어의 럭셔리한 터틀넥 니트. 부드러운 촉감과 따뜻함으로 겨울철 필수 아이템으로 시그니처 카멜 컬러가 돋보입니다.',
 '2024-03-09 10:00:00+09', '2024-03-09 10:00:00+09'),

-- 10. 여성 팬츠
('a3200001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-2222-2222-2222-222222222222'::uuid,
 '프라다 와이드 레그 울 팬츠 블랙', 'ON_SALE', 1890000, 14,
 'https://www.prada.com/content/dam/pradanux_products/P/P29/P29E5S/221/1WQ8F0002/P29E5S_221_1WQ8_F0002_S_221_SLF.jpg',
 '모던한 와이드 레그 실루엣의 울 팬츠. 프라다 시그니처 트라이앵글 로고와 테일러드 핏으로 세련된 오피스룩을 완성합니다.',
 '2024-03-10 10:00:00+09', '2024-03-10 10:00:00+09');

-- 상품 이미지 데이터 (상품별 1~3개)
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
-- 캐나다구스 파카 이미지 (2개)
('a1942000-1111-1111-1111-111111111111'::uuid, 'a3200000-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://images.canadagoose.com/image/upload/w_480,c_scale,f_auto,q_auto:best/v1694024551/product-image/5080M_61.jpg', 0, '2024-03-01 10:00:00+09', '2024-03-01 10:00:00+09'),
('a1942000-1111-1111-1111-222222222222'::uuid, 'a3200000-1111-1111-1111-111111111111'::uuid, 'DETAIL', 'https://images.canadagoose.com/image/upload/w_480,c_scale,f_auto,q_auto:best/v1694024551/product-image/5080M_61_b.jpg', 1, '2024-03-01 10:00:00+09', '2024-03-01 10:00:00+09'),

-- 랄프로렌 니트 이미지 (1개)
('a1942000-2222-2222-2222-111111111111'::uuid, 'a3200000-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.ralphlauren.asia/on/demandware.static/-/Sites-rl-catalog/default/dw3e8e8e8e/polo-ralph-lauren/710766772/original/710766772_cf23.jpg', 0, '2024-03-02 10:00:00+09', '2024-03-02 10:00:00+09'),

-- 톰브라운 셔츠 이미지 (3개)
('a1942000-3333-3333-3333-111111111111'::uuid, 'a3200000-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://thombrowne.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-tb-master-catalog/default/dw3e8e8e8e/images/MWL010A-00139-100/MWL010A-00139-100_2.jpg', 0, '2024-03-03 10:00:00+09', '2024-03-03 10:00:00+09'),
('a1942000-3333-3333-3333-222222222222'::uuid, 'a3200000-3333-3333-3333-333333333333'::uuid, 'DETAIL', 'https://thombrowne.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-tb-master-catalog/default/dw3e8e8e8e/images/MWL010A-00139-100/MWL010A-00139-100_3.jpg', 1, '2024-03-03 10:00:00+09', '2024-03-03 10:00:00+09'),
('a1942000-3333-3333-3333-333333333333'::uuid, 'a3200000-3333-3333-3333-333333333333'::uuid, 'DETAIL', 'https://thombrowne.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-tb-master-catalog/default/dw3e8e8e8e/images/MWL010A-00139-100/MWL010A-00139-100_4.jpg', 2, '2024-03-03 10:00:00+09', '2024-03-03 10:00:00+09'),

-- 구찌 팬츠 이미지 (2개)
('a1942000-4444-4444-4444-111111111111'::uuid, 'a3200000-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://media.gucci.com/style/DarkGray_Center_0_0_800x800/1672926387/645897_XJDQ0_1000_001_100_0000_Light.jpg', 0, '2024-03-04 10:00:00+09', '2024-03-04 10:00:00+09'),
('a1942000-4444-4444-4444-222222222222'::uuid, 'a3200000-4444-4444-4444-444444444444'::uuid, 'DETAIL', 'https://media.gucci.com/style/DarkGray_Center_0_0_800x800/1672926387/645897_XJDQ0_1000_002_100_0000_Light.jpg', 1, '2024-03-04 10:00:00+09', '2024-03-04 10:00:00+09'),

-- 몽클레어 다운 이미지 (1개)
('a1942000-5555-5555-5555-111111111111'::uuid, 'a3200000-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://store.moncler.com/on/demandware.static/-/Sites-moncler-master/default/dw8e8e8e8e/images/prd/1B52200C0455/999/1B52200C0455_999_1.jpg', 0, '2024-03-05 10:00:00+09', '2024-03-05 10:00:00+09'),

-- 버버리 드레스 이미지 (2개)
('a1942000-6666-6666-6666-111111111111'::uuid, 'a3200000-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://assets.burberry.com/is/image/Burberryltd/3E8E8E8E-8E8E-8E8E-8E8E-8E8E8E8E8E8E?$BBY_V2_SL_1x1$&wid=1278', 0, '2024-03-06 10:00:00+09', '2024-03-06 10:00:00+09'),
('a1942000-6666-6666-6666-222222222222'::uuid, 'a3200000-6666-6666-6666-666666666666'::uuid, 'DETAIL', 'https://assets.burberry.com/is/image/Burberryltd/3E8E8E8E-8E8E-8E8E-8E8E-8E8E8E8E8E8E?$BBY_V2_SL_1x1$&wid=1278', 1, '2024-03-06 10:00:00+09', '2024-03-06 10:00:00+09'),

-- 생로랑 블라우스 이미지 (1개)
('a1942000-7777-7777-7777-111111111111'::uuid, 'a3200000-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.ysl.com/dw/image/v2/BGWJ_PRD/on/demandware.static/-/Sites-masterCatalog_YSL/default/dw8e8e8e8e/assets/Y/6/8/1/Y681677Y011N9601_A.jpg', 0, '2024-03-07 10:00:00+09', '2024-03-07 10:00:00+09'),

-- DIOR 스커트 이미지 (2개)
('a1942000-8888-8888-8888-111111111111'::uuid, 'a3200000-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.dior.com/couture/ecommerce/media/catalog/product/cache/1/cover_image_mobile_1/715x773/0/0/0000000000000000.jpg', 0, '2024-03-08 10:00:00+09', '2024-03-08 10:00:00+09'),
('a1942000-8888-8888-8888-222222222222'::uuid, 'a3200000-8888-8888-8888-888888888888'::uuid, 'DETAIL', 'https://www.dior.com/couture/ecommerce/media/catalog/product/cache/1/cover_image_mobile_1/715x773/0/0/0000000000000001.jpg', 1, '2024-03-08 10:00:00+09', '2024-03-08 10:00:00+09'),

-- 막스마라 니트 이미지 (1개)
('a1942000-9999-9999-9999-111111111111'::uuid, 'a3200000-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://it.maxmara.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-maxmara-master/default/dw8e8e8e8e/images/1/1364311206000_1_G.jpg', 0, '2024-03-09 10:00:00+09', '2024-03-09 10:00:00+09'),

-- 프라다 팬츠 이미지 (3개)
('a1942001-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3200001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.prada.com/content/dam/pradanux_products/P/P29/P29E5S/221/1WQ8F0002/P29E5S_221_1WQ8_F0002_S_221_SLF.jpg', 0, '2024-03-10 10:00:00+09', '2024-03-10 10:00:00+09'),
('a1942001-aaaa-aaaa-aaaa-222222222222'::uuid, 'a3200001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'DETAIL', 'https://www.prada.com/content/dam/pradanux_products/P/P29/P29E5S/221/1WQ8F0002/P29E5S_221_1WQ8_F0002_S_221_SLD.jpg', 1, '2024-03-10 10:00:00+09', '2024-03-10 10:00:00+09'),
('a1942001-aaaa-aaaa-aaaa-333333333333'::uuid, 'a3200001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'DETAIL', 'https://www.prada.com/content/dam/pradanux_products/P/P29/P29E5S/221/1WQ8F0002/P29E5S_221_1WQ8_F0002_S_221_SLB.jpg', 2, '2024-03-10 10:00:00+09', '2024-03-10 10:00:00+09');