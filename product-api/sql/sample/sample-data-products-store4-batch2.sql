-- ========================================
-- 상품 데이터 - 4번째 판매자(뷰티올) 상품 11~20
-- ========================================

-- 상품 11-20 (뷰티올 - 헤어케어, 바디케어, 메이크업)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 11. 헤어케어 - 헤어드라이어
('a3400011-1111-1111-1111-111111111111'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-4444-4444-4444-444444444444'::uuid,
 'Dyson Supersonic 헤어드라이어 HD08 니켈/코퍼', 'ON_SALE', 550000, 12,
 'https://dyson-h.assetsadobe2.com/is/image/content/dam/dyson/products/hair-care/447/supersonic/overview/dyson-supersonic-nickel-copper-overview-1.jpg',
 '다이슨 디지털 모터 V9로 빠르고 정밀한 드라이. 지능형 온도 조절로 모발 손상을 방지하는 프리미엄 헤어드라이어입니다.',
 '2024-05-11 10:00:00+09', '2024-05-11 10:00:00+09'),

-- 12. 헤어케어 - 헤어 트리트먼트
('a3400012-2222-2222-2222-222222222222'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-4444-4444-4444-444444444444'::uuid,
 'Olaplex No.3 헤어 퍼펙터 100ml', 'ON_SALE', 38000, 60,
 'https://olaplex.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-olaplex-master/default/dw3e3e3e3e/images/products/no3/no3-100ml-front.jpg',
 '손상된 모발을 집에서 케어하는 올라플렉스 No.3. 주 1~2회 사용으로 결합이 끊어진 모발을 복구하고 윤기를 더합니다.',
 '2024-05-12 10:00:00+09', '2024-05-12 10:00:00+09'),

-- 13. 헤어케어 - 샴푸
('a3400013-3333-3333-3333-333333333333'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-4444-4444-4444-444444444444'::uuid,
 'Kérastase 레지스탕스 방 엑스텐셔니스트 샴푸 250ml', 'ON_SALE', 52000, 45,
 'https://www.kerastase.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-kerastase-master/default/dw3e3e3e3e/images/large/Resistance_Bain_Extentioniste_250ml.jpg',
 '프랑스 프로페셔널 헤어케어 브랜드 케라스타즈. 크레아틴과 세라마이드로 모발을 강화하고 길고 건강한 머릿결로 가꿔줍니다.',
 '2024-05-13 10:00:00+09', '2024-05-13 10:00:00+09'),

-- 14. 헤어케어 - 컨디셔너
('a3400014-4444-4444-4444-444444444444'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-4444-4444-4444-444444444444'::uuid,
 'Aveda 데미지 레미디 인텐시브 리스트럭쳐링 트리트먼트 200ml', 'ON_SALE', 58000, 35,
 'https://www.aveda.com/media/export/cms/products/600x600/aveda_prod_SHDRT_600x600_0.jpg',
 '아베다 베스트셀러 인텐시브 트리트먼트. 퀴노아 단백질로 손상 모발을 집중 케어하고 즉각적인 복구 효과를 제공합니다.',
 '2024-05-14 10:00:00+09', '2024-05-14 10:00:00+09'),

-- 15. 바디케어 - 바디 로션
('a3400015-5555-5555-5555-555555555555'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-5555-5555-5555-555555555555'::uuid,
 'Jo Malone London 잉글리쉬 페어 앤 프리지아 바디 크렘 175ml', 'ON_SALE', 85000, 40,
 'https://www.jomalone.com/media/export/cms/products/600x600/jo_sku_LABP01_600x600_0.jpg',
 '조말론 시그니처 향이 담긴 바디 크렘. 풍부한 보습과 함께 달콤한 배와 프리지아 향이 오래 지속됩니다.',
 '2024-05-15 10:00:00+09', '2024-05-15 10:00:00+09'),

-- 16. 바디케어 - 핸드크림
('a3400016-6666-6666-6666-666666666666'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-5555-5555-5555-555555555555'::uuid,
 'L''Occitane 시어 핸드크림 150ml', 'ON_SALE', 39000, 80,
 'https://www.loccitane.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-loccitane-master/default/dw3e3e3e3e/images/large/01HC150K19_1.jpg',
 '록시땅 베스트셀러 시어버터 핸드크림. 25% 시어버터 함유로 건조한 손을 깊이 보습하고 부드럽게 가꿔줍니다.',
 '2024-05-16 10:00:00+09', '2024-05-16 10:00:00+09'),

-- 17. 바디케어 - 샤워젤
('a3400017-7777-7777-7777-777777777777'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-5555-5555-5555-555555555555'::uuid,
 'Aesop 제라늄 리프 바디 클렌저 500ml', 'ON_SALE', 62000, 30,
 'https://www.aesop.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-aesop-master/default/dw3e3e3e3e/images/large/Aesop-Body-Geranium-Leaf-Body-Cleanser-500mL-large.png',
 '이솝의 베스트셀러 바디 클렌저. 제라늄과 감귤 향이 상쾌하고 피부에 자극 없이 깨끗하게 세정합니다.',
 '2024-05-17 10:00:00+09', '2024-05-17 10:00:00+09'),

-- 18. 메이크업 - 마스카라
('a3400018-8888-8888-8888-888888888888'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'Lancôme 입솔뤼 마스카라 블랙', 'ON_SALE', 48000, 55,
 'https://www.lancome.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-lancome-master/default/dw3e3e3e3e/images/large/Hypnose_Mascara_01_Black.jpg',
 '랑콤 아이코닉 마스카라. 풍성한 볼륨과 선명한 컬링 효과로 눈매를 극대화합니다.',
 '2024-05-18 10:00:00+09', '2024-05-18 10:00:00+09'),

-- 19. 메이크업 - 쿠션 파운데이션
('a3400019-9999-9999-9999-999999999999'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'Sulwhasoo 퍼펙팅 쿠션 엑스 SPF50+ PA+++ 23호', 'ON_SALE', 68000, 50,
 'https://www.sulwhasoo.com/kr/ko/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-sulwhasoo-master/default/dw3e3e3e3e/images/large/Perfecting_Cushion_EX_23.jpg',
 '설화수 퍼펙팅 쿠션. 한방 성분과 높은 자외선 차단 지수로 피부를 보호하며 자연스러운 윤광 피부를 연출합니다.',
 '2024-05-19 10:00:00+09', '2024-05-19 10:00:00+09'),

-- 20. 스킨케어 - 아이크림
('a3400020-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'La Mer 디 아이 컨센트레이트 15ml', 'ON_SALE', 290000, 20,
 'https://www.cremedelamer.com/media/export/cms/products/600x600/lamer_sku_7T7601_600x600_0.jpg',
 '라메르 프리미엄 아이 케어. 미라클 브로스™와 라임 티 추출물로 눈가의 주름과 다크서클을 집중 케어합니다.',
 '2024-05-20 10:00:00+09', '2024-05-20 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1944001-1111-1111-1111-111111111111'::uuid, 'a3400011-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://dyson-h.assetsadobe2.com/is/image/content/dam/dyson/products/hair-care/447/supersonic/overview/dyson-supersonic-nickel-copper-overview-1.jpg', 0, '2024-05-11 10:00:00+09', '2024-05-11 10:00:00+09'),
('a1944001-2222-2222-2222-111111111111'::uuid, 'a3400012-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://olaplex.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-olaplex-master/default/dw3e3e3e3e/images/products/no3/no3-100ml-front.jpg', 0, '2024-05-12 10:00:00+09', '2024-05-12 10:00:00+09'),
('a1944001-3333-3333-3333-111111111111'::uuid, 'a3400013-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://www.kerastase.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-kerastase-master/default/dw3e3e3e3e/images/large/Resistance_Bain_Extentioniste_250ml.jpg', 0, '2024-05-13 10:00:00+09', '2024-05-13 10:00:00+09'),
('a1944001-4444-4444-4444-111111111111'::uuid, 'a3400014-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.aveda.com/media/export/cms/products/600x600/aveda_prod_SHDRT_600x600_0.jpg', 0, '2024-05-14 10:00:00+09', '2024-05-14 10:00:00+09'),
('a1944001-5555-5555-5555-111111111111'::uuid, 'a3400015-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://www.jomalone.com/media/export/cms/products/600x600/jo_sku_LABP01_600x600_0.jpg', 0, '2024-05-15 10:00:00+09', '2024-05-15 10:00:00+09'),
('a1944001-6666-6666-6666-111111111111'::uuid, 'a3400016-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.loccitane.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-loccitane-master/default/dw3e3e3e3e/images/large/01HC150K19_1.jpg', 0, '2024-05-16 10:00:00+09', '2024-05-16 10:00:00+09'),
('a1944001-7777-7777-7777-111111111111'::uuid, 'a3400017-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.aesop.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-aesop-master/default/dw3e3e3e3e/images/large/Aesop-Body-Geranium-Leaf-Body-Cleanser-500mL-large.png', 0, '2024-05-17 10:00:00+09', '2024-05-17 10:00:00+09'),
('a1944001-8888-8888-8888-111111111111'::uuid, 'a3400018-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.lancome.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-lancome-master/default/dw3e3e3e3e/images/large/Hypnose_Mascara_01_Black.jpg', 0, '2024-05-18 10:00:00+09', '2024-05-18 10:00:00+09'),
('a1944001-9999-9999-9999-111111111111'::uuid, 'a3400019-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.sulwhasoo.com/kr/ko/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-sulwhasoo-master/default/dw3e3e3e3e/images/large/Perfecting_Cushion_EX_23.jpg', 0, '2024-05-19 10:00:00+09', '2024-05-19 10:00:00+09'),
('a1944002-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3400020-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.cremedelamer.com/media/export/cms/products/600x600/lamer_sku_7T7601_600x600_0.jpg', 0, '2024-05-20 10:00:00+09', '2024-05-20 10:00:00+09');
