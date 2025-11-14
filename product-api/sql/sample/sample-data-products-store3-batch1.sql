-- ========================================
-- 상품 데이터 - 3번째 판매자(홈리빙365) 상품 1~10
-- ========================================

-- 상품 1-10 (홈리빙365 - 가구, 침구류)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 1. 소파
('a3300000-1111-1111-1111-111111111111'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-1111-1111-1111-111111111111'::uuid,
 'IKEA KIVIK 3인용 소파 오란다 다크그레이', 'ON_SALE', 899000, 5,
 'https://www.ikea.com/kr/ko/images/products/kivik-3-seat-sofa-orrsta-dark-grey__0714766_pe730060_s5.jpg',
 '넓고 깊은 좌석과 부드러운 쿠션. 10년 품질보증과 커버 교체 가능한 모듈형 디자인으로 오래도록 사용할 수 있는 소파입니다.',
 '2024-04-10 10:00:00+09', '2024-04-10 10:00:00+09'),

-- 2. 침대
('a3300000-2222-2222-2222-222222222222'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-1111-1111-1111-111111111111'::uuid,
 '시몬스 뷰티레스트 블랙 라벨 매트리스 퀸', 'ON_SALE', 3890000, 3,
 'https://www.simmons.co.kr/upload/product/202304/BLKQ_1_2023041815513059.jpg',
 '포켓코일과 메모리폼의 완벽한 조화. 에어쿨 시스템과 엣지 서포트로 최상의 수면 환경을 제공하는 프리미엄 매트리스입니다.',
 '2024-04-11 10:00:00+09', '2024-04-11 10:00:00+09'),

-- 3. 책상
('a3300000-3333-3333-3333-333333333333'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-1111-1111-1111-111111111111'::uuid,
 'Herman Miller Ratio 전동높이조절책상 화이트 1600mm', 'ON_SALE', 1890000, 8,
 'https://store.hermanmiller.com/content/dam/hmicom/page_assets/products/ratio_desk/product_images/ratio_electric_desk_product_image_1.jpg',
 '전동 높이조절 기능의 프리미엄 스탠딩 데스크. 메모리 프리셋과 조용한 모터로 건강한 업무 환경을 제공합니다.',
 '2024-04-12 10:00:00+09', '2024-04-12 10:00:00+09'),

-- 4. 의자
('a3300000-4444-4444-4444-444444444444'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-1111-1111-1111-111111111111'::uuid,
 'Herman Miller Aeron 리마스터드 체어 사이즈B 그래파이트', 'ON_SALE', 2290000, 6,
 'https://store.hermanmiller.com/content/dam/hmicom/page_assets/products/aeron_chair/product_images/aeron_graphite_34r_bk_prem_base.png',
 '인체공학의 전설 에어론 체어. 8Z 펠리클 서스펜션과 포스처핏 서포트로 장시간 앉아도 편안한 최고의 작업 의자입니다.',
 '2024-04-13 10:00:00+09', '2024-04-13 10:00:00+09'),

-- 5. 수납장
('a3300000-5555-5555-5555-555555555555'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-1111-1111-1111-111111111111'::uuid,
 'MUJI 참나무 서랍장 4단 내추럴', 'ON_SALE', 459000, 12,
 'https://img.muji.net/img/item/4550182562842_1260.jpg',
 '무인양품 시그니처 참나무 가구. 심플한 디자인과 견고한 구조로 오래도록 사용할 수 있는 모던한 수납장입니다.',
 '2024-04-14 10:00:00+09', '2024-04-14 10:00:00+09'),

-- 6. 침구세트
('a3300000-6666-6666-6666-666666666666'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-2222-2222-2222-222222222222'::uuid,
 'FRETTE 호텔 클래식 퍼케일 이불커버 세트 퀸 화이트', 'ON_SALE', 1290000, 10,
 'https://www.frette.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-frette-master/default/dw3e3e3e3e/images/1FR10441BIAQ_01.jpg',
 '이탈리아 명품 침구 브랜드 프레떼. 300수 이집트산 코튼 퍼케일로 최상급 호텔의 침구를 집에서 경험하세요.',
 '2024-04-15 10:00:00+09', '2024-04-15 10:00:00+09'),

-- 7. 베개
('a3300000-7777-7777-7777-777777777777'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-2222-2222-2222-222222222222'::uuid,
 'Tempur 오리지널 베개 미디움', 'ON_SALE', 289000, 25,
 'https://www.tempur.com/dw/image/v2/BGWJ_PRD/on/demandware.static/-/Sites-tempur-master/default/dw3e3e3e3e/images/original-pillow-medium.jpg',
 'NASA 기술로 개발된 템퍼 메모리폼. 목과 어깨를 완벽하게 지지하여 최상의 수면 자세를 유지하는 프리미엄 베개입니다.',
 '2024-04-16 10:00:00+09', '2024-04-16 10:00:00+09'),

-- 8. 이불
('a3300000-8888-8888-8888-888888888888'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-2222-2222-2222-222222222222'::uuid,
 '한샘 프리미엄 거위털 이불 퀸 화이트', 'ON_SALE', 389000, 15,
 'https://image.hanssem.com/hsimg/gds/550/690/550690_A1_550.jpg',
 '95% 화이트 구스다운과 410수 고밀도 면커버. 가볍고 따뜻한 호텔급 거위털 이불로 쾌적한 수면을 제공합니다.',
 '2024-04-17 10:00:00+09', '2024-04-17 10:00:00+09'),

-- 9. 러그
('a3300000-9999-9999-9999-999999999999'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-4444-4444-4444-444444444444'::uuid,
 'Ruggable Washable Rug 8x10 카말라 모로칸 베이지', 'ON_SALE', 890000, 8,
 'https://res.cloudinary.com/ruggable/image/upload/c_pad,dpr_auto,f_auto,q_auto,w_1400/v1/Products/117447/8x10/117447_01.jpg',
 '세탁기로 빨 수 있는 혁신적인 러그. 미끄럼 방지 패드와 2단계 시스템으로 청결하고 안전한 인테리어를 완성합니다.',
 '2024-04-18 10:00:00+09', '2024-04-18 10:00:00+09'),

-- 10. 커튼
('a3300001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-4444-4444-4444-444444444444'::uuid,
 'IKEA MAJGULL 암막커튼 1쌍 다크그레이 145x250cm', 'ON_SALE', 59000, 40,
 'https://www.ikea.com/kr/ko/images/products/majgull-blackout-curtains-1-pair-dark-grey__0735958_pe740245_s5.jpg',
 '99% 빛차단 암막커튼. 두꺼운 원단과 우아한 드레이프로 프라이버시 보호와 숙면을 도와주는 실용적인 커튼입니다.',
 '2024-04-19 10:00:00+09', '2024-04-19 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1943000-1111-1111-1111-111111111111'::uuid, 'a3300000-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://www.ikea.com/kr/ko/images/products/kivik-3-seat-sofa-orrsta-dark-grey__0714766_pe730060_s5.jpg', 0, '2024-04-10 10:00:00+09', '2024-04-10 10:00:00+09'),
('a1943000-2222-2222-2222-111111111111'::uuid, 'a3300000-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.simmons.co.kr/upload/product/202304/BLKQ_1_2023041815513059.jpg', 0, '2024-04-11 10:00:00+09', '2024-04-11 10:00:00+09'),
('a1943000-3333-3333-3333-111111111111'::uuid, 'a3300000-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://store.hermanmiller.com/content/dam/hmicom/page_assets/products/ratio_desk/product_images/ratio_electric_desk_product_image_1.jpg', 0, '2024-04-12 10:00:00+09', '2024-04-12 10:00:00+09'),
('a1943000-4444-4444-4444-111111111111'::uuid, 'a3300000-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://store.hermanmiller.com/content/dam/hmicom/page_assets/products/aeron_chair/product_images/aeron_graphite_34r_bk_prem_base.png', 0, '2024-04-13 10:00:00+09', '2024-04-13 10:00:00+09'),
('a1943000-5555-5555-5555-111111111111'::uuid, 'a3300000-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://img.muji.net/img/item/4550182562842_1260.jpg', 0, '2024-04-14 10:00:00+09', '2024-04-14 10:00:00+09'),
('a1943000-6666-6666-6666-111111111111'::uuid, 'a3300000-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.frette.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-frette-master/default/dw3e3e3e3e/images/1FR10441BIAQ_01.jpg', 0, '2024-04-15 10:00:00+09', '2024-04-15 10:00:00+09'),
('a1943000-7777-7777-7777-111111111111'::uuid, 'a3300000-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.tempur.com/dw/image/v2/BGWJ_PRD/on/demandware.static/-/Sites-tempur-master/default/dw3e3e3e3e/images/original-pillow-medium.jpg', 0, '2024-04-16 10:00:00+09', '2024-04-16 10:00:00+09'),
('a1943000-8888-8888-8888-111111111111'::uuid, 'a3300000-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://image.hanssem.com/hsimg/gds/550/690/550690_A1_550.jpg', 0, '2024-04-17 10:00:00+09', '2024-04-17 10:00:00+09'),
('a1943000-9999-9999-9999-111111111111'::uuid, 'a3300000-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://res.cloudinary.com/ruggable/image/upload/c_pad,dpr_auto,f_auto,q_auto,w_1400/v1/Products/117447/8x10/117447_01.jpg', 0, '2024-04-18 10:00:00+09', '2024-04-18 10:00:00+09'),
('a1943001-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3300001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.ikea.com/kr/ko/images/products/majgull-blackout-curtains-1-pair-dark-grey__0735958_pe740245_s5.jpg', 0, '2024-04-19 10:00:00+09', '2024-04-19 10:00:00+09');