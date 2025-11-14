-- ========================================
-- 상품 데이터 - 4번째 판매자(뷰티올) 상품 1~10
-- ========================================

-- 상품 1-10 (뷰티올 - 스킨케어, 메이크업, 향수)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 1. 스킨케어 - 크림
('a3400001-1111-1111-1111-111111111111'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'La Mer 크렘 드 라 메르 모이스처라이징 크림 60ml', 'ON_SALE', 490000, 15,
 'https://www.cremedelamer.com/media/export/cms/products/600x600/lamer_sku_9G4L01_600x600_0.jpg',
 '전설의 럭셔리 크림 라메르. 미라클 브로스™ 성분과 깊은 보습으로 피부 재생을 돕는 프리미엄 안티에이징 크림입니다.',
 '2024-05-01 10:00:00+09', '2024-05-01 10:00:00+09'),

-- 2. 스킨케어 - 에센스
('a3400002-2222-2222-2222-222222222222'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'SK-II 페이셜 트리트먼트 에센스 230ml', 'ON_SALE', 298000, 25,
 'https://www.sk-ii.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-skii-master/default/dw3e3e3e3e/images/large/Facial_Treatment_Essence_230ml.jpg',
 '피테라™ 성분 90% 함유. 맑고 투명한 피부결을 위한 일본 프리미엄 스킨케어의 아이콘입니다.',
 '2024-05-02 10:00:00+09', '2024-05-02 10:00:00+09'),

-- 3. 스킨케어 - 세럼
('a3400003-3333-3333-3333-333333333333'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'Estée Lauder 어드밴스드 나이트 리페어 세럼 50ml', 'ON_SALE', 149000, 40,
 'https://www.esteelauder.com/media/export/cms/products/600x600/el_sku_7R1G01_600x600_0.jpg',
 '밤사이 피부 회복을 돕는 에스티로더 베스트셀러 나이트 세럼. 크로노룩스™ 파워 시그널 기술로 피부 본연의 힘을 깨웁니다.',
 '2024-05-03 10:00:00+09', '2024-05-03 10:00:00+09'),

-- 4. 스킨케어 - 선크림
('a3400004-4444-4444-4444-444444444444'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-1111-1111-1111-111111111111'::uuid,
 'La Roche-Posay 안텔리오스 UVmune 400 하이드레이팅 크림 SPF50+ 50ml', 'ON_SALE', 32000, 80,
 'https://www.laroche-posay.us/dw/image/v2/AANG_PRD/on/demandware.static/-/Sites-acd-laroche-posay-master-catalog/default/dw3e3e3e3e/Images/3337875763295_1.jpg',
 '피부과 전문 브랜드 라로슈포제의 초강력 자외선 차단제. UVA/UVB 광범위 보호와 보습을 동시에 제공합니다.',
 '2024-05-04 10:00:00+09', '2024-05-04 10:00:00+09'),

-- 5. 메이크업 - 파운데이션
('a3400005-5555-5555-5555-555555555555'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'MAC 스튜디오 픽스 플루이드 SPF15 파운데이션 NC25 30ml', 'ON_SALE', 52000, 50,
 'https://www.maccosmetics.com/media/export/cms/products/600x600/mac_sku_M3DN01_600x600_0.jpg',
 'MAC 베스트셀러 리퀴드 파운데이션. 중간~풀 커버리지로 자연스럽고 매트한 피부 표현이 가능합니다.',
 '2024-05-05 10:00:00+09', '2024-05-05 10:00:00+09'),

-- 6. 메이크업 - 립스틱
('a3400006-6666-6666-6666-666666666666'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'MAC 러스터 립스틱 Ruby Woo', 'ON_SALE', 36000, 70,
 'https://www.maccosmetics.com/media/export/cms/products/600x600/mac_sku_SH7F01_600x600_0.jpg',
 '아이코닉한 블루베이스 레드 립스틱. 매트한 텍스처와 선명한 발색으로 클래식한 레드립을 완성합니다.',
 '2024-05-06 10:00:00+09', '2024-05-06 10:00:00+09'),

-- 7. 메이크업 - 아이섀도우 팔레트
('a3400007-7777-7777-7777-777777777777'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-2222-2222-2222-222222222222'::uuid,
 'Urban Decay Naked 3 아이섀도우 팔레트', 'ON_SALE', 72000, 35,
 'https://www.urbandecay.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-urbandecay-master/default/dw3e3e3e3e/products/eyes/naked3/naked3-palette-front.jpg',
 '12가지 로즈 톤 섀도우로 구성된 네이키드 3 팔레트. 부드러운 블렌딩과 우수한 발색력으로 다양한 룩을 연출합니다.',
 '2024-05-07 10:00:00+09', '2024-05-07 10:00:00+09'),

-- 8. 향수 - 여성용
('a3400008-8888-8888-8888-888888888888'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-3333-3333-3333-333333333333'::uuid,
 'Chanel No.5 오 드 퍼퓸 50ml', 'ON_SALE', 189000, 20,
 'https://www.chanel.com/images//t_one//w_0.51,h_0.51,c_crop/q_auto:good,f_auto,fl_lossy,dpr_1.2/w_620/chanel-n-5-eau-de-parfum-spray-50ml-3145891355000.jpg',
 '전설적인 샤넬의 시그니처 향수. 1921년부터 이어져온 플로럴 알데히드 향으로 여성성의 정수를 담았습니다.',
 '2024-05-08 10:00:00+09', '2024-05-08 10:00:00+09'),

-- 9. 향수 - 남성용
('a3400009-9999-9999-9999-999999999999'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-3333-3333-3333-333333333333'::uuid,
 'Dior Sauvage 오 드 투왈렛 100ml', 'ON_SALE', 168000, 30,
 'https://www.dior.com/dw/image/v2/BGXS_PRD/on/demandware.static/-/Sites-master_dior/default/dw3e3e3e3e/Y0998302/Y0998302_C099600849_E01_GHC.jpg',
 '디올 사바지 오 드 투왈렛. 프레시한 베르가못과 파퓰러 톤의 강렬한 조화로 야생의 자유로움을 표현합니다.',
 '2024-05-09 10:00:00+09', '2024-05-09 10:00:00+09'),

-- 10. 향수 - 유니섹스
('a3400010-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1444444-4444-4444-4444-444444444444'::uuid, '뷰티올', 'a5db3333-3333-3333-3333-333333333333'::uuid,
 'Jo Malone London 잉글리쉬 페어 앤 프리지아 코롱 100ml', 'ON_SALE', 220000, 25,
 'https://www.jomalone.com/media/export/cms/products/600x600/jo_sku_L2P601_600x600_0.jpg',
 '조말론 런던 베스트셀러 향수. 달콤한 배와 프리지아의 조화로 우아하고 신선한 향기를 선사합니다.',
 '2024-05-10 10:00:00+09', '2024-05-10 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1944000-1111-1111-1111-111111111111'::uuid, 'a3400001-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://www.cremedelamer.com/media/export/cms/products/600x600/lamer_sku_9G4L01_600x600_0.jpg', 0, '2024-05-01 10:00:00+09', '2024-05-01 10:00:00+09'),
('a1944000-2222-2222-2222-111111111111'::uuid, 'a3400002-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.sk-ii.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-skii-master/default/dw3e3e3e3e/images/large/Facial_Treatment_Essence_230ml.jpg', 0, '2024-05-02 10:00:00+09', '2024-05-02 10:00:00+09'),
('a1944000-3333-3333-3333-111111111111'::uuid, 'a3400003-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://www.esteelauder.com/media/export/cms/products/600x600/el_sku_7R1G01_600x600_0.jpg', 0, '2024-05-03 10:00:00+09', '2024-05-03 10:00:00+09'),
('a1944000-4444-4444-4444-111111111111'::uuid, 'a3400004-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.laroche-posay.us/dw/image/v2/AANG_PRD/on/demandware.static/-/Sites-acd-laroche-posay-master-catalog/default/dw3e3e3e3e/Images/3337875763295_1.jpg', 0, '2024-05-04 10:00:00+09', '2024-05-04 10:00:00+09'),
('a1944000-5555-5555-5555-111111111111'::uuid, 'a3400005-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://www.maccosmetics.com/media/export/cms/products/600x600/mac_sku_M3DN01_600x600_0.jpg', 0, '2024-05-05 10:00:00+09', '2024-05-05 10:00:00+09'),
('a1944000-6666-6666-6666-111111111111'::uuid, 'a3400006-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.maccosmetics.com/media/export/cms/products/600x600/mac_sku_SH7F01_600x600_0.jpg', 0, '2024-05-06 10:00:00+09', '2024-05-06 10:00:00+09'),
('a1944000-7777-7777-7777-111111111111'::uuid, 'a3400007-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.urbandecay.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-urbandecay-master/default/dw3e3e3e3e/products/eyes/naked3/naked3-palette-front.jpg', 0, '2024-05-07 10:00:00+09', '2024-05-07 10:00:00+09'),
('a1944000-8888-8888-8888-111111111111'::uuid, 'a3400008-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.chanel.com/images//t_one//w_0.51,h_0.51,c_crop/q_auto:good,f_auto,fl_lossy,dpr_1.2/w_620/chanel-n-5-eau-de-parfum-spray-50ml-3145891355000.jpg', 0, '2024-05-08 10:00:00+09', '2024-05-08 10:00:00+09'),
('a1944000-9999-9999-9999-111111111111'::uuid, 'a3400009-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.dior.com/dw/image/v2/BGXS_PRD/on/demandware.static/-/Sites-master_dior/default/dw3e3e3e3e/Y0998302/Y0998302_C099600849_E01_GHC.jpg', 0, '2024-05-09 10:00:00+09', '2024-05-09 10:00:00+09'),
('a1944001-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3400010-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.jomalone.com/media/export/cms/products/600x600/jo_sku_L2P601_600x600_0.jpg', 0, '2024-05-10 10:00:00+09', '2024-05-10 10:00:00+09');
