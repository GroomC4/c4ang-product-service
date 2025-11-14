-- ========================================
-- 상품 데이터 - 4번째 판매자(뷰티올) 상품 31~40
-- ========================================

-- 상품 31-40 (뷰티올 - 스킨케어, 메이크업, 헤어케어, 바디케어)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 31. 스킨케어 - 미스트
('a3400031-1111-1111-1111-111111111111'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'Caudalie 뷰티 엘릭서 100ml', 'ON_SALE', 28000, 50,
 'https://us.caudalie.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-caudalie-master/default/dw3e3e3e3e/images/large/Beauty_Elixir_100ml.jpg',
 '꼬달리 뷰티 엘릭서. 포도 추출물과 에센셜 오일로 피부에 활력을 주고 메이크업 픽서로도 사용 가능한 멀티 미스트입니다.',
 '2024-05-31 10:00:00+09', '2024-05-31 10:00:00+09'),

-- 32. 스킨케어 - 필링패드
('a3400032-2222-2222-2222-222222222222'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'COSRX 원스텝 모이스처 업 패드 70매', 'ON_SALE', 24000, 70,
 'https://www.cosrx.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-cosrx-master/default/dw3e3e3e3e/images/products/One_Step_Moisture_Up_Pad.jpg',
 '코스알엑스 모이스처 업 패드. 프로폴리스와 히알루론산으로 수분 공급과 각질 정돈을 동시에 해결합니다.',
 '2024-06-01 10:00:00+09', '2024-06-01 10:00:00+09'),

-- 33. 메이크업 - 하이라이터
('a3400033-3333-3333-3333-333333333333'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'Becca 샤이머링 스킨 퍼펙터 프레스드 챔페인 팝', 'ON_SALE', 58000, 40,
 'https://www.beccacosmetics.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-becca-master/default/dw3e3e3e3e/images/products/Shimmering_Skin_Perfector_Pressed_Champagne_Pop.jpg',
 '베카 아이코닉 하이라이터. 섬세한 펄로 자연스러운 윤광을 더하고 입체적인 얼굴형을 연출합니다.',
 '2024-06-02 10:00:00+09', '2024-06-02 10:00:00+09'),

-- 34. 메이크업 - 브로우
('a3400034-4444-4444-4444-444444444444'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'Anastasia Beverly Hills 브로우 위즈 미디엄 브라운', 'ON_SALE', 35000, 55,
 'https://www.anastasiabeverlyhills.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-abh-master/default/dw3e3e3e3e/images/products/Brow_Wiz_Medium_Brown.jpg',
 '아나스타샤 베벌리힐즈 브로우 위즈. 0.08mm 초극세 펜슬로 자연스러운 눈썹을 그리는 아이브로우 펜슬입니다.',
 '2024-06-03 10:00:00+09', '2024-06-03 10:00:00+09'),

-- 35. 메이크업 - 세팅 스프레이
('a3400035-5555-5555-5555-555555555555'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'Urban Decay All Nighter 세팅 스프레이 118ml', 'ON_SALE', 48000, 45,
 'https://www.urbandecay.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-urbandecay-master/default/dw3e3e3e3e/products/complexion/all-nighter-setting-spray/all-nighter-setting-spray-118ml.jpg',
 '어반디케이 올 나이터 세팅 스프레이. 메이크업을 16시간 지속시키고 번짐을 방지하는 롱래스팅 픽서입니다.',
 '2024-06-04 10:00:00+09', '2024-06-04 10:00:00+09'),

-- 36. 헤어케어 - 헤어 스타일러
('a3400036-6666-6666-6666-666666666666'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-4444-4444-4444-444444444444'::uuid,
 'ghd Platinum+ 헤어 스트레이트너', 'ON_SALE', 329000, 15,
 'https://www.ghdhair.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-ghd-master/default/dw3e3e3e3e/images/products/platinum-plus-styler/ghd-platinum-plus-styler-1.jpg',
 'ghd 플래티넘+ 스트레이트너. 예측 기술로 최적의 온도 185도를 유지하여 모발 손상을 최소화하는 프리미엄 고데기입니다.',
 '2024-06-05 10:00:00+09', '2024-06-05 10:00:00+09'),

-- 37. 헤어케어 - 드라이 샴푸
('a3400037-7777-7777-7777-777777777777'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-4444-4444-4444-444444444444'::uuid,
 'Batiste 드라이 샴푸 오리지널 200ml', 'ON_SALE', 12000, 90,
 'https://www.batistehair.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-batiste-master/default/dw3e3e3e3e/images/products/Batiste_Dry_Shampoo_Original_200ml.jpg',
 '바티스트 드라이 샴푸. 물 없이 빠르게 유분을 제거하고 볼륨을 더해 산뜻한 머릿결을 유지합니다.',
 '2024-06-06 10:00:00+09', '2024-06-06 10:00:00+09'),

-- 38. 바디케어 - 바디 워시
('a3400038-8888-8888-8888-888888888888'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-5555-5555-5555-555555555555'::uuid,
 'Dove 딥 모이스처 바디 워시 500ml', 'ON_SALE', 9800, 100,
 'https://www.dove.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-dove-master/default/dw3e3e3e3e/images/products/Deep_Moisture_Body_Wash_500ml.jpg',
 '다브 딥 모이스처 바디 워시. 너트리엄 모이스처 기술로 피부 장벽을 보호하고 깊은 보습을 제공합니다.',
 '2024-06-07 10:00:00+09', '2024-06-07 10:00:00+09'),

-- 39. 바디케어 - 데오드란트
('a3400039-9999-9999-9999-999999999999'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-5555-5555-5555-555555555555'::uuid,
 'Secret 클리니컬 스트렝스 데오드란트 무향 45g', 'ON_SALE', 18000, 60,
 'https://www.secret.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-secret-master/default/dw3e3e3e3e/images/products/Clinical_Strength_Unscented_45g.jpg',
 '시크릿 클리니컬 스트렝스. 처방급 강력한 땀 억제 효과로 48시간 지속되는 데오드란트입니다.',
 '2024-06-08 10:00:00+09', '2024-06-08 10:00:00+09'),

-- 40. 향수 - 바디 미스트
('a3400040-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-3333-3333-3333-333333333333'::uuid,
 'Bath & Body Works A Thousand Wishes 파인 프래그런스 미스트 236ml', 'ON_SALE', 22000, 75,
 'https://www.bathandbodyworks.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-BathAndBodyWorks-master/default/dw3e3e3e3e/images/products/A_Thousand_Wishes_Fragrance_Mist_236ml.jpg',
 '배스앤바디웍스 어 싸우전드 위시스. 샴페인과 복숭아의 달콤하고 화려한 향이 가볍게 지속되는 바디 미스트입니다.',
 '2024-06-09 10:00:00+09', '2024-06-09 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1944003-1111-1111-1111-111111111111'::uuid, 'a3400031-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://us.caudalie.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-caudalie-master/default/dw3e3e3e3e/images/large/Beauty_Elixir_100ml.jpg', 0, '2024-05-31 10:00:00+09', '2024-05-31 10:00:00+09'),
('a1944003-2222-2222-2222-111111111111'::uuid, 'a3400032-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.cosrx.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-cosrx-master/default/dw3e3e3e3e/images/products/One_Step_Moisture_Up_Pad.jpg', 0, '2024-06-01 10:00:00+09', '2024-06-01 10:00:00+09'),
('a1944003-3333-3333-3333-111111111111'::uuid, 'a3400033-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://www.beccacosmetics.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-becca-master/default/dw3e3e3e3e/images/products/Shimmering_Skin_Perfector_Pressed_Champagne_Pop.jpg', 0, '2024-06-02 10:00:00+09', '2024-06-02 10:00:00+09'),
('a1944003-4444-4444-4444-111111111111'::uuid, 'a3400034-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.anastasiabeverlyhills.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-abh-master/default/dw3e3e3e3e/images/products/Brow_Wiz_Medium_Brown.jpg', 0, '2024-06-03 10:00:00+09', '2024-06-03 10:00:00+09'),
('a1944003-5555-5555-5555-111111111111'::uuid, 'a3400035-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://www.urbandecay.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-urbandecay-master/default/dw3e3e3e3e/products/complexion/all-nighter-setting-spray/all-nighter-setting-spray-118ml.jpg', 0, '2024-06-04 10:00:00+09', '2024-06-04 10:00:00+09'),
('a1944003-6666-6666-6666-111111111111'::uuid, 'a3400036-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.ghdhair.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-ghd-master/default/dw3e3e3e3e/images/products/platinum-plus-styler/ghd-platinum-plus-styler-1.jpg', 0, '2024-06-05 10:00:00+09', '2024-06-05 10:00:00+09'),
('a1944003-7777-7777-7777-111111111111'::uuid, 'a3400037-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.batistehair.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-batiste-master/default/dw3e3e3e3e/images/products/Batiste_Dry_Shampoo_Original_200ml.jpg', 0, '2024-06-06 10:00:00+09', '2024-06-06 10:00:00+09'),
('a1944003-8888-8888-8888-111111111111'::uuid, 'a3400038-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.dove.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-dove-master/default/dw3e3e3e3e/images/products/Deep_Moisture_Body_Wash_500ml.jpg', 0, '2024-06-07 10:00:00+09', '2024-06-07 10:00:00+09'),
('a1944003-9999-9999-9999-111111111111'::uuid, 'a3400039-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.secret.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-secret-master/default/dw3e3e3e3e/images/products/Clinical_Strength_Unscented_45g.jpg', 0, '2024-06-08 10:00:00+09', '2024-06-08 10:00:00+09'),
('a1944004-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3400040-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.bathandbodyworks.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-BathAndBodyWorks-master/default/dw3e3e3e3e/images/products/A_Thousand_Wishes_Fragrance_Mist_236ml.jpg', 0, '2024-06-09 10:00:00+09', '2024-06-09 10:00:00+09');
