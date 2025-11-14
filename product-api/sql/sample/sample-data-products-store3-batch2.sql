-- ========================================
-- 상품 데이터 - 3번째 판매자(홈리빙365) 상품 11~20
-- ========================================

-- 상품 11-20 (홈리빙365 - 주방용품, 인테리어소품)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 11. 조리도구
('a3300001-1111-1111-1111-111111111111'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-3333-3333-3333-333333333333'::uuid,
 'Le Creuset 시그니처 라운드 더치오븐 24cm 플레임', 'ON_SALE', 489000, 10,
 'https://www.lecreuset.co.kr/media/catalog/product/2/1/21177240602430_1.jpg',
 '프랑스 명품 주물 냄비 르크루제. 뛰어난 열전도와 보온성으로 오랜 시간 요리의 맛과 영양을 지켜주는 평생 쓰는 냄비입니다.',
 '2024-04-20 10:00:00+09', '2024-04-20 10:00:00+09'),

-- 12. 칼세트
('a3300001-2222-2222-2222-222222222222'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-3333-3333-3333-333333333333'::uuid,
 'Zwilling 프로 나이프 블록 세트 7pcs', 'ON_SALE', 890000, 8,
 'https://www.zwilling.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-zwilling-master/default/dw3e3e3e3e/images/large/35665-002-0.jpg',
 '독일 칼 명가 츠빌링의 프로페셔널 나이프 세트. 고탄소 스테인리스와 인체공학적 핸들로 전문가급 조리를 경험하세요.',
 '2024-04-21 10:00:00+09', '2024-04-21 10:00:00+09'),

-- 13. 믹서기
('a3300001-3333-3333-3333-333333333333'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-3333-3333-3333-333333333333'::uuid,
 'Vitamix A3500 고성능 블렌더 스테인리스', 'ON_SALE', 989000, 12,
 'https://www.vitamix.com/media/catalog/product/A/3/A3500_Brushed_Stainless_main-1.jpg',
 '프로페셔널 블렌더의 정점. 2.2HP 모터와 10단계 가변 속도, 5가지 자동 프로그램으로 스무디부터 수프까지 완벽하게 조리합니다.',
 '2024-04-22 10:00:00+09', '2024-04-22 10:00:00+09'),

-- 14. 커피머신
('a3300001-4444-4444-4444-444444444444'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-3333-3333-3333-333333333333'::uuid,
 'Breville Barista Express 에스프레소 머신 스테인리스', 'ON_SALE', 1090000, 6,
 'https://www.breville.com/content/dam/breville/us/assets/products/espresso/bes870xl/bes870xl-image-1.jpg',
 '바리스타 퀄리티 에스프레소 머신. 내장 그라인더와 15바 압력, 스팀 완드로 집에서 카페급 커피를 즐기세요.',
 '2024-04-23 10:00:00+09', '2024-04-23 10:00:00+09'),

-- 15. 식기세트
('a3300001-5555-5555-5555-555555555555'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-3333-3333-3333-333333333333'::uuid,
 'Noritake 본차이나 16피스 디너세트 화이트 골드', 'ON_SALE', 389000, 15,
 'https://www.noritake.co.jp/eng/products/images/4924-32914_main.jpg',
 '일본 명품 본차이나 노리타케. 우아한 골드 테두리와 프리미엄 포슬린으로 특별한 식탁을 연출하는 디너웨어 세트입니다.',
 '2024-04-24 10:00:00+09', '2024-04-24 10:00:00+09'),

-- 16. 수저세트
('a3300001-6666-6666-6666-666666666666'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-3333-3333-3333-333333333333'::uuid,
 'WMF Nuova 커틀러리 30피스 세트 스테인리스', 'ON_SALE', 259000, 20,
 'https://www.wmf.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-WMF-EMEA-Library/default/dw3e3e3e3e/1280H/1280H-0000.jpg',
 '독일 WMF의 타임리스 디자인 커틀러리. 크롬 니켈 스테인리스와 세련된 형태로 일상의 식사를 품격있게 만듭니다.',
 '2024-04-25 10:00:00+09', '2024-04-25 10:00:00+09'),

-- 17. 조명
('a3300001-7777-7777-7777-777777777777'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-4444-4444-4444-444444444444'::uuid,
 'Louis Poulsen PH 5 펜던트 라이트 화이트', 'ON_SALE', 1290000, 5,
 'https://www.louispoulsen.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-louis-poulsen-master/default/dw3e3e3e3e/images/products/PH%205/5741094953_PH5_hero_8.jpg',
 '덴마크 조명 디자인의 명작 PH5. 눈부심 없는 부드러운 빛과 아이코닉한 디자인으로 북유럽 모던 인테리어를 완성합니다.',
 '2024-04-26 10:00:00+09', '2024-04-26 10:00:00+09'),

-- 18. 화분
('a3300001-8888-8888-8888-888888888888'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-4444-4444-4444-444444444444'::uuid,
 'Lechuza Classico 자동급수 화분 35cm 화이트', 'ON_SALE', 189000, 25,
 'https://www.lechuza.com/out/pictures/master/product/1/13300_classico-ls-35_white-high-gloss.jpg',
 '독일 레추자의 자동급수 화분. 4주간 물 공급 시스템과 UV 안정화 플라스틱으로 식물 관리가 쉬운 프리미엄 화분입니다.',
 '2024-04-27 10:00:00+09', '2024-04-27 10:00:00+09'),

-- 19. 시계
('a3300001-9999-9999-9999-999999999999'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-4444-4444-4444-444444444444'::uuid,
 'Vitra Ball Clock 월 클락 멀티컬러', 'ON_SALE', 489000, 8,
 'https://www.vitra.com/en-ch/product/ball-clock?image=20125701',
 '조지 넬슨의 미드센추리 모던 디자인. 컬러풀한 우드 볼과 유려한 선이 만드는 예술적인 벽시계로 공간에 활력을 더합니다.',
 '2024-04-28 10:00:00+09', '2024-04-28 10:00:00+09'),

-- 20. 액자
('a3300002-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-4444-4444-4444-444444444444'::uuid,
 'IKEA RIBBA 액자 50x70cm 화이트', 'ON_SALE', 29000, 60,
 'https://www.ikea.com/kr/ko/images/products/ribba-frame-white__0638327_pe698857_s5.jpg',
 '심플하고 실용적인 액자. 앞면 보호 플라스틱과 쉬운 교체 시스템으로 사진이나 포스터를 멋지게 전시할 수 있습니다.',
 '2024-04-29 10:00:00+09', '2024-04-29 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1943001-1111-1111-1111-111111111111'::uuid, 'a3300001-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://www.lecreuset.co.kr/media/catalog/product/2/1/21177240602430_1.jpg', 0, '2024-04-20 10:00:00+09', '2024-04-20 10:00:00+09'),
('a1943001-2222-2222-2222-111111111111'::uuid, 'a3300001-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.zwilling.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-zwilling-master/default/dw3e3e3e3e/images/large/35665-002-0.jpg', 0, '2024-04-21 10:00:00+09', '2024-04-21 10:00:00+09'),
('a1943001-3333-3333-3333-111111111111'::uuid, 'a3300001-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://www.vitamix.com/media/catalog/product/A/3/A3500_Brushed_Stainless_main-1.jpg', 0, '2024-04-22 10:00:00+09', '2024-04-22 10:00:00+09'),
('a1943001-4444-4444-4444-111111111111'::uuid, 'a3300001-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.breville.com/content/dam/breville/us/assets/products/espresso/bes870xl/bes870xl-image-1.jpg', 0, '2024-04-23 10:00:00+09', '2024-04-23 10:00:00+09'),
('a1943001-5555-5555-5555-111111111111'::uuid, 'a3300001-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://www.noritake.co.jp/eng/products/images/4924-32914_main.jpg', 0, '2024-04-24 10:00:00+09', '2024-04-24 10:00:00+09'),
('a1943001-6666-6666-6666-111111111111'::uuid, 'a3300001-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.wmf.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-WMF-EMEA-Library/default/dw3e3e3e3e/1280H/1280H-0000.jpg', 0, '2024-04-25 10:00:00+09', '2024-04-25 10:00:00+09'),
('a1943001-7777-7777-7777-111111111111'::uuid, 'a3300001-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.louispoulsen.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-louis-poulsen-master/default/dw3e3e3e3e/images/products/PH%205/5741094953_PH5_hero_8.jpg', 0, '2024-04-26 10:00:00+09', '2024-04-26 10:00:00+09'),
('a1943001-8888-8888-8888-111111111111'::uuid, 'a3300001-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.lechuza.com/out/pictures/master/product/1/13300_classico-ls-35_white-high-gloss.jpg', 0, '2024-04-27 10:00:00+09', '2024-04-27 10:00:00+09'),
('a1943001-9999-9999-9999-111111111111'::uuid, 'a3300001-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.vitra.com/en-ch/product/ball-clock?image=20125701', 0, '2024-04-28 10:00:00+09', '2024-04-28 10:00:00+09'),
('a1943002-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3300002-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.ikea.com/kr/ko/images/products/ribba-frame-white__0638327_pe698857_s5.jpg', 0, '2024-04-29 10:00:00+09', '2024-04-29 10:00:00+09');