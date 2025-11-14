-- ========================================
-- 상품 데이터 - 4번째 판매자(뷰티올) 상품 21~30
-- ========================================

-- 상품 21-30 (뷰티올 - 스킨케어, 메이크업, 향수, 헤어케어)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 21. 스킨케어 - 클렌저
('a3400021-1111-1111-1111-111111111111'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'Sulwhasoo 순행 클렌징 폼 200ml', 'ON_SALE', 45000, 50,
 'https://www.sulwhasoo.com/kr/ko/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-sulwhasoo-master/default/dw3e3e3e3e/images/large/Gentle_Cleansing_Foam_200ml.jpg',
 '설화수 순행 클렌징 폼. 한방 성분으로 피부를 부드럽게 클렌징하고 피부 본연의 균형을 유지합니다.',
 '2024-05-21 10:00:00+09', '2024-05-21 10:00:00+09'),

-- 22. 스킨케어 - 토너
('a3400022-2222-2222-2222-222222222222'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'Huxley 토너 익스트랙트잇 120ml', 'ON_SALE', 36000, 55,
 'https://www.huxley.com/web/product/big/202103/c8e8e8e8e8e8e8e8e8e8e8e8e8e8e8e8.jpg',
 '사하라 선인장씨 오일 함유 허슬리 토너. 수분 공급과 피부결 정돈을 동시에 해결하는 멀티 토너입니다.',
 '2024-05-22 10:00:00+09', '2024-05-22 10:00:00+09'),

-- 23. 스킨케어 - 앰플
('a3400023-3333-3333-3333-333333333333'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'The Ordinary 니아신아마이드 10% + 징크 1% 세럼 30ml', 'ON_SALE', 8900, 100,
 'https://theordinary.com/dw/image/v2/BFKJ_PRD/on/demandware.static/-/Sites-deciem-master/default/dw3e3e3e3e/Images/products/The%20Ordinary/rdn-niacinamide-10pct-zinc-1pct-30ml.png',
 '더 오디너리 베스트셀러 니아신아마이드 세럼. 모공 케어와 피부 톤 개선에 효과적인 고농도 앰플입니다.',
 '2024-05-23 10:00:00+09', '2024-05-23 10:00:00+09'),

-- 24. 스킨케어 - 마스크팩
('a3400024-4444-4444-4444-444444444444'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'Mediheal N.M.F 아쿠아링 앰플 마스크 EX 10매', 'ON_SALE', 15000, 80,
 'https://www.mediheal.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-mediheal-master/default/dw3e3e3e3e/images/large/NMF_Aquaring_Ampoule_Mask_EX.jpg',
 '메디힐 N.M.F 아쿠아링 마스크. 히알루론산과 세라마이드로 즉각적인 수분 충전과 피부 진정 효과를 제공합니다.',
 '2024-05-24 10:00:00+09', '2024-05-24 10:00:00+09'),

-- 25. 메이크업 - 아이라이너
('a3400025-5555-5555-5555-555555555555'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'Stila Stay All Day 워터프루프 리퀴드 아이라이너 블랙', 'ON_SALE', 32000, 60,
 'https://www.stilacosmetics.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-stila-master/default/dw3e3e3e3e/images/products/eyes/Stay_All_Day_Waterproof_Liquid_Eye_Liner_Intense_Black.jpg',
 '스틸라 베스트셀러 워터프루프 아이라이너. 선명한 발색과 정교한 라인으로 하루 종일 번지지 않습니다.',
 '2024-05-25 10:00:00+09', '2024-05-25 10:00:00+09'),

-- 26. 메이크업 - 컨실러
('a3400026-6666-6666-6666-666666666666'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'NARS 래디언트 크리미 컨실러 1.4 바닐라', 'ON_SALE', 42000, 45,
 'https://www.narscosmetics.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-nars-master/default/dw3e3e3e3e/images/large/0607845012146.jpg',
 '나스 베스트셀러 컨실러. 크리미한 텍스처로 완벽한 커버리지와 자연스러운 피부 표현을 동시에 제공합니다.',
 '2024-05-26 10:00:00+09', '2024-05-26 10:00:00+09'),

-- 27. 메이크업 - 블러셔
('a3400027-7777-7777-7777-777777777777'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'Benefit 코랄리스타 파우더 블러셔', 'ON_SALE', 48000, 40,
 'https://www.benefitcosmetics.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-benefit-master/default/dw3e3e3e3e/images/large/Coralista_Blush.jpg',
 '베네핏 코랄리스타 블러셔. 골든 코랄 컬러로 생기 있고 건강한 혈색을 표현하는 파우더 블러셔입니다.',
 '2024-05-27 10:00:00+09', '2024-05-27 10:00:00+09'),

-- 28. 향수 - 여성용
('a3400028-8888-8888-8888-888888888888'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-3333-3333-3333-333333333333'::uuid,
 'Yves Saint Laurent Mon Paris 오 드 퍼퓸 90ml', 'ON_SALE', 198000, 25,
 'https://www.yslbeautyus.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-ysl-master-catalog/default/dw3e3e3e3e/3614272049949_mon_paris_edp_90ml_1.jpg',
 '입생로랑 몽 파리. 베리와 피오니의 달콤하고 관능적인 플로럴 향수로 로맨틱한 매력을 발산합니다.',
 '2024-05-28 10:00:00+09', '2024-05-28 10:00:00+09'),

-- 29. 헤어케어 - 헤어 오일
('a3400029-9999-9999-9999-999999999999'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-4444-4444-4444-444444444444'::uuid,
 'Moroccanoil 트리트먼트 오리지널 100ml', 'ON_SALE', 49000, 50,
 'https://www.moroccanoil.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-moroccanoil-master/default/dw3e3e3e3e/images/products/Moroccanoil_Treatment_100ml.jpg',
 '모로칸오일 베스트셀러 헤어 트리트먼트. 아르간 오일로 모발에 윤기와 영양을 더하고 정전기를 방지합니다.',
 '2024-05-29 10:00:00+09', '2024-05-29 10:00:00+09'),

-- 30. 바디케어 - 스크럽
('a3400030-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-5555-5555-5555-555555555555'::uuid,
 'Fresh 슈가 레몬 바디 폴리쉬 200g', 'ON_SALE', 59000, 35,
 'https://www.fresh.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-fresh-master/default/dw3e3e3e3e/images/large/Sugar_Lemon_Body_Polish_200g.jpg',
 '프레시 슈가 바디 스크럽. 천연 설탕 결정과 레몬 오일로 각질을 부드럽게 제거하고 상쾌한 향을 남깁니다.',
 '2024-05-30 10:00:00+09', '2024-05-30 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1944002-1111-1111-1111-111111111111'::uuid, 'a3400021-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://www.sulwhasoo.com/kr/ko/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-sulwhasoo-master/default/dw3e3e3e3e/images/large/Gentle_Cleansing_Foam_200ml.jpg', 0, '2024-05-21 10:00:00+09', '2024-05-21 10:00:00+09'),
('a1944002-2222-2222-2222-111111111111'::uuid, 'a3400022-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.huxley.com/web/product/big/202103/c8e8e8e8e8e8e8e8e8e8e8e8e8e8e8e8.jpg', 0, '2024-05-22 10:00:00+09', '2024-05-22 10:00:00+09'),
('a1944002-3333-3333-3333-111111111111'::uuid, 'a3400023-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://theordinary.com/dw/image/v2/BFKJ_PRD/on/demandware.static/-/Sites-deciem-master/default/dw3e3e3e3e/Images/products/The%20Ordinary/rdn-niacinamide-10pct-zinc-1pct-30ml.png', 0, '2024-05-23 10:00:00+09', '2024-05-23 10:00:00+09'),
('a1944002-4444-4444-4444-111111111111'::uuid, 'a3400024-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.mediheal.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-mediheal-master/default/dw3e3e3e3e/images/large/NMF_Aquaring_Ampoule_Mask_EX.jpg', 0, '2024-05-24 10:00:00+09', '2024-05-24 10:00:00+09'),
('a1944002-5555-5555-5555-111111111111'::uuid, 'a3400025-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://www.stilacosmetics.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-stila-master/default/dw3e3e3e3e/images/products/eyes/Stay_All_Day_Waterproof_Liquid_Eye_Liner_Intense_Black.jpg', 0, '2024-05-25 10:00:00+09', '2024-05-25 10:00:00+09'),
('a1944002-6666-6666-6666-111111111111'::uuid, 'a3400026-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.narscosmetics.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-nars-master/default/dw3e3e3e3e/images/large/0607845012146.jpg', 0, '2024-05-26 10:00:00+09', '2024-05-26 10:00:00+09'),
('a1944002-7777-7777-7777-111111111111'::uuid, 'a3400027-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.benefitcosmetics.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-benefit-master/default/dw3e3e3e3e/images/large/Coralista_Blush.jpg', 0, '2024-05-27 10:00:00+09', '2024-05-27 10:00:00+09'),
('a1944002-8888-8888-8888-111111111111'::uuid, 'a3400028-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.yslbeautyus.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-ysl-master-catalog/default/dw3e3e3e3e/3614272049949_mon_paris_edp_90ml_1.jpg', 0, '2024-05-28 10:00:00+09', '2024-05-28 10:00:00+09'),
('a1944002-9999-9999-9999-111111111111'::uuid, 'a3400029-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.moroccanoil.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-moroccanoil-master/default/dw3e3e3e3e/images/products/Moroccanoil_Treatment_100ml.jpg', 0, '2024-05-29 10:00:00+09', '2024-05-29 10:00:00+09'),
('a1944003-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3400030-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.fresh.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-fresh-master/default/dw3e3e3e3e/images/large/Sugar_Lemon_Body_Polish_200g.jpg', 0, '2024-05-30 10:00:00+09', '2024-05-30 10:00:00+09');
