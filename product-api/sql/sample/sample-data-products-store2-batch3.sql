-- ========================================
-- 상품 데이터 - 2번째 판매자(패션플러스) 상품 21~30
-- ========================================

-- 상품 21-30 (패션플러스 - 액세서리)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 21. 시계
('a3200002-1111-1111-1111-111111111111'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'Rolex Submariner Date 126610LN 블랙', 'ON_SALE', 15900000, 2,
 'https://content.rolex.com/dam/2022/upright-bba-with-shadow/m126610ln-0001.png',
 '다이빙 워치의 전설. 세라크롬 베젤과 자동 무브먼트, 300m 방수 성능으로 완벽한 스포츠 럭셔리 워치입니다.',
 '2024-03-21 10:00:00+09', '2024-03-21 10:00:00+09'),

-- 22. 시계
('a3200002-2222-2222-2222-222222222222'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'Omega Speedmaster Moonwatch 프로페셔널', 'ON_SALE', 8900000, 5,
 'https://www.omegawatches.com/media/catalog/product/cache/image/1280x768/o/m/omega-speedmaster-moonwatch-professional-co-axial-master-chronometer-chronograph-42-mm-31030425001002-1-800x900.png',
 '달에 간 유일한 시계. 수동 와인딩 크로노그래프와 헤사라이트 크리스탈로 아폴로 미션의 전설을 이어갑니다.',
 '2024-03-22 10:00:00+09', '2024-03-22 10:00:00+09'),

-- 23. 선글라스
('a3200002-3333-3333-3333-333333333333'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'Ray-Ban Wayfarer Classic RB2140 블랙', 'ON_SALE', 239000, 60,
 'https://assets.ray-ban.com/is/image/RayBan/8056597832656__STD__shad__qt.png',
 '타임리스 클래식 웨이파러. G-15 렌즈와 아세테이트 프레임으로 60년 넘게 사랑받는 아이코닉 선글라스입니다.',
 '2024-03-23 10:00:00+09', '2024-03-23 10:00:00+09'),

-- 24. 선글라스
('a3200002-4444-4444-4444-444444444444'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'Gentle Monster Lang 01 블랙', 'ON_SALE', 329000, 45,
 'https://www.gentlemonster.com/media/catalog/product/cache/image/700x867/e9c3970ab036de70892d86c6d221abfe/l/a/lang_01_1.jpg',
 '한국 디자이너 브랜드의 시그니처 모델. 과감한 디자인과 미니멀한 실루엣이 조화를 이루는 프리미엄 선글라스입니다.',
 '2024-03-24 10:00:00+09', '2024-03-24 10:00:00+09'),

-- 25. 벨트
('a3200002-5555-5555-5555-555555555555'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'Hermès H 리버서블 레더 벨트 32mm 블랙/브라운', 'ON_SALE', 1290000, 18,
 'https://assets.hermes.com/is/image/hermesproduct/ceinture-h-reversible-32-mm--075177CK89105_worn_1.jpg',
 '에르메스 시그니처 H 버클의 리버서블 벨트. 최고급 송아지 가죽으로 제작되어 양면 사용 가능한 럭셔리 액세서리입니다.',
 '2024-03-25 10:00:00+09', '2024-03-25 10:00:00+09'),

-- 26. 지갑
('a3200002-6666-6666-6666-666666666666'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'Bottega Veneta 인트레치아토 롱 지갑 블랙', 'ON_SALE', 1090000, 25,
 'https://www.bottegaveneta.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-masterCatalog_BottegaVeneta/default/dw3e3e3e3e/images/667582VCPQ18803/F.jpg',
 '시그니처 인트레치아토 위빙 기법의 롱 지갑. 램스킨 소재와 넉넉한 수납공간으로 실용성과 우아함을 겸비했습니다.',
 '2024-03-26 10:00:00+09', '2024-03-26 10:00:00+09'),

-- 27. 지갑
('a3200002-7777-7777-7777-777777777777'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'Montblanc Meisterstück 6cc 반지갑 블랙', 'ON_SALE', 690000, 30,
 'https://www.montblanc.com/variants/images/41091094/B/w600.jpg',
 '장인정신이 담긴 마이스터스튁 컬렉션. 풀 그레인 유럽산 송아지 가죽과 화이트 스타 엠블럼이 돋보이는 클래식 지갑입니다.',
 '2024-03-27 10:00:00+09', '2024-03-27 10:00:00+09'),

-- 28. 모자
('a3200002-8888-8888-8888-888888888888'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'New Era 뉴욕 양키스 59FIFTY 네이비', 'ON_SALE', 59000, 80,
 'https://www.neweracap.eu/dw/image/v2/BGWJ_PRD/on/demandware.static/-/Sites-NEE-master-EU/default/dw3e3e3e3e/original/70361077_3QL_1.jpg',
 'MLB 공식 모자 뉴에라 59FIFTY. 클래식 핏과 자수 로고, 견고한 구조로 완벽한 스타일을 완성하는 정통 야구 모자입니다.',
 '2024-03-28 10:00:00+09', '2024-03-28 10:00:00+09'),

-- 29. 넥타이
('a3200002-9999-9999-9999-999999999999'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'Salvatore Ferragamo 실크 넥타이 네이비', 'ON_SALE', 290000, 40,
 'https://www.ferragamo.com/dw/image/v2/BGWJ_PRD/on/demandware.static/-/Sites-ferragamo-master-catalog/default/dw3e3e3e3e/images/730326/730326_1_BLUE_A.jpg',
 '100% 이탈리아산 실크 소재의 프리미엄 넥타이. 페라가모 시그니처 패턴과 정교한 재봉으로 비즈니스 룩을 완성합니다.',
 '2024-03-29 10:00:00+09', '2024-03-29 10:00:00+09'),

-- 30. 스카프
('a3200003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-5555-5555-5555-555555555555'::uuid,
 'Burberry 캐시미어 체크 스카프 캐멀/베이지', 'ON_SALE', 690000, 35,
 'https://assets.burberry.com/is/image/Burberryltd/3E8E8E8E-8E8E-8E8E-8E8E-8E8E8E8E8E8E?$BBY_V2_SL_1x1$&wid=1278',
 '버버리 아이콘 빈티지 체크 캐시미어 스카프. 100% 스코틀랜드산 캐시미어로 부드럽고 따뜻한 겨울 필수 액세서리입니다.',
 '2024-03-30 10:00:00+09', '2024-03-30 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1942002-1111-1111-1111-111111111111'::uuid, 'a3200002-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://content.rolex.com/dam/2022/upright-bba-with-shadow/m126610ln-0001.png', 0, '2024-03-21 10:00:00+09', '2024-03-21 10:00:00+09'),
('a1942002-2222-2222-2222-111111111111'::uuid, 'a3200002-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.omegawatches.com/media/catalog/product/cache/image/1280x768/o/m/omega-speedmaster-moonwatch-professional-co-axial-master-chronometer-chronograph-42-mm-31030425001002-1-800x900.png', 0, '2024-03-22 10:00:00+09', '2024-03-22 10:00:00+09'),
('a1942002-3333-3333-3333-111111111111'::uuid, 'a3200002-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://assets.ray-ban.com/is/image/RayBan/8056597832656__STD__shad__qt.png', 0, '2024-03-23 10:00:00+09', '2024-03-23 10:00:00+09'),
('a1942002-4444-4444-4444-111111111111'::uuid, 'a3200002-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.gentlemonster.com/media/catalog/product/cache/image/700x867/e9c3970ab036de70892d86c6d221abfe/l/a/lang_01_1.jpg', 0, '2024-03-24 10:00:00+09', '2024-03-24 10:00:00+09'),
('a1942002-5555-5555-5555-111111111111'::uuid, 'a3200002-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://assets.hermes.com/is/image/hermesproduct/ceinture-h-reversible-32-mm--075177CK89105_worn_1.jpg', 0, '2024-03-25 10:00:00+09', '2024-03-25 10:00:00+09'),
('a1942002-6666-6666-6666-111111111111'::uuid, 'a3200002-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.bottegaveneta.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-masterCatalog_BottegaVeneta/default/dw3e3e3e3e/images/667582VCPQ18803/F.jpg', 0, '2024-03-26 10:00:00+09', '2024-03-26 10:00:00+09'),
('a1942002-7777-7777-7777-111111111111'::uuid, 'a3200002-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.montblanc.com/variants/images/41091094/B/w600.jpg', 0, '2024-03-27 10:00:00+09', '2024-03-27 10:00:00+09'),
('a1942002-8888-8888-8888-111111111111'::uuid, 'a3200002-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.neweracap.eu/dw/image/v2/BGWJ_PRD/on/demandware.static/-/Sites-NEE-master-EU/default/dw3e3e3e3e/original/70361077_3QL_1.jpg', 0, '2024-03-28 10:00:00+09', '2024-03-28 10:00:00+09'),
('a1942002-9999-9999-9999-111111111111'::uuid, 'a3200002-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.ferragamo.com/dw/image/v2/BGWJ_PRD/on/demandware.static/-/Sites-ferragamo-master-catalog/default/dw3e3e3e3e/images/730326/730326_1_BLUE_A.jpg', 0, '2024-03-29 10:00:00+09', '2024-03-29 10:00:00+09'),
('a1942003-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3200003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://assets.burberry.com/is/image/Burberryltd/3E8E8E8E-8E8E-8E8E-8E8E-8E8E8E8E8E8E?$BBY_V2_SL_1x1$&wid=1278', 0, '2024-03-30 10:00:00+09', '2024-03-30 10:00:00+09');