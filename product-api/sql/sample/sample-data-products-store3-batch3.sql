-- ========================================
-- 상품 데이터 - 3번째 판매자(홈리빙365) 상품 21~40
-- ========================================

-- 상품 21-40 (홈리빙365 - 욕실용품 및 기타 생활용품)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 21-30: 욕실용품
('a3300002-1111-1111-1111-111111111111'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-5555-5555-5555-555555555555'::uuid,
 'Aesop 레버랜더 핸드워시 500ml', 'ON_SALE', 62000, 50,
 'https://www.aesop.com/u1nb1km7t5q7/5e5e5e5e5e5e5e5e5e5e5e5e5e5e5e5e/Aesop-Resurrection-Aromatique-Hand-Wash-500mL-large.png',
 '이솝의 시그니처 핸드워시. 레버랜더, 로즈마리, 세이지 향으로 손을 부드럽게 세정하는 프리미엄 핸드 클렌저입니다.',
 '2024-04-30 10:00:00+09', '2024-04-30 10:00:00+09'),

('a3300002-2222-2222-2222-222222222222'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-5555-5555-5555-555555555555'::uuid,
 'Malin+Goetz 페퍼민트 샴푸 473ml', 'ON_SALE', 58000, 40,
 'https://www.malinandgoetz.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-msg-master/default/dw3e3e3e3e/peppermint_shampoo.png',
 '뉴욕 아포테케리 브랜드의 시그니처 샴푸. 페퍼민트와 아미노산으로 두피를 상쾌하게 클렌징하는 데일리 샴푸입니다.',
 '2024-05-01 10:00:00+09', '2024-05-01 10:00:00+09'),

('a3300002-3333-3333-3333-333333333333'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-5555-5555-5555-555555555555'::uuid,
 'Diptyque Do Son 바디 로션 200ml', 'ON_SALE', 89000, 30,
 'https://www.diptyqueparis.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-diptyque-master/default/dw3e3e3e3e/do-son-body-lotion.png',
 '딥티크의 아이코닉한 도손 향. 튜베로즈 플로럴 향과 부드러운 보습으로 피부에 향기와 영양을 더하는 럭셔리 바디 로션입니다.',
 '2024-05-02 10:00:00+09', '2024-05-02 10:00:00+09'),

('a3300002-4444-4444-4444-444444444444'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-5555-5555-5555-555555555555'::uuid,
 'Aquis 마이크로파이버 헤어 터번 화이트', 'ON_SALE', 42000, 45,
 'https://aquis.com/cdn/shop/products/Lisse-Hair-Turban-White.jpg',
 '초흡수 마이크로파이버 헤어 타월. 일반 타월보다 50% 빠른 건조로 머릿결 손상을 최소화하는 혁신적인 제품입니다.',
 '2024-05-03 10:00:00+09', '2024-05-03 10:00:00+09'),

('a3300002-5555-5555-5555-555555555555'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-5555-5555-5555-555555555555'::uuid,
 'Waterworks Studio 목욕 가운 화이트 호텔 컬렉션', 'ON_SALE', 320000, 12,
 'https://www.waterworks.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-waterworks-master/default/dw3e3e3e3e/robe-white.jpg',
 '5성급 호텔 품질의 목욕 가운. 이집트 면 100%로 제작되어 부드럽고 흡수력이 뛰어난 프리미엄 배스로브입니다.',
 '2024-05-04 10:00:00+09', '2024-05-04 10:00:00+09'),

('a3300002-6666-6666-6666-666666666666'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-5555-5555-5555-555555555555'::uuid,
 'Joseph Joseph Slim 비누 디스펜서 그레이', 'ON_SALE', 35000, 55,
 'https://www.josephjoseph.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/s/l/slim_soap_dispenser_grey.jpg',
 '공간 절약형 슬림 디자인 디스펜서. 넓은 개구부와 논슬립 베이스로 사용이 편리한 영국 디자인 욕실 액세서리입니다.',
 '2024-05-05 10:00:00+09', '2024-05-05 10:00:00+09'),

('a3300002-7777-7777-7777-777777777777'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-5555-5555-5555-555555555555'::uuid,
 'Umbra Hub 원형 거울 94cm 블랙', 'ON_SALE', 189000, 18,
 'https://www.umbra.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-umbra-master/default/dw3e3e3e3e/hub-mirror-black.jpg',
 '미니멀 디자인의 대형 원형 거울. 고무 프레임과 벽걸이 스트랩으로 안전하게 설치되는 모던한 인테리어 필수 아이템입니다.',
 '2024-05-06 10:00:00+09', '2024-05-06 10:00:00+09'),

('a3300002-8888-8888-8888-888888888888'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-5555-5555-5555-555555555555'::uuid,
 'Kassatex Pergamon 목욕 매트 화이트', 'ON_SALE', 89000, 35,
 'https://www.kassatex.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-kassatex-master/default/dw3e3e3e3e/bath-rug-white.jpg',
 '터키산 코튼 100% 목욕 매트. 두꺼운 파일과 논슬립 백킹으로 안전하고 편안한 욕실 환경을 제공합니다.',
 '2024-05-07 10:00:00+09', '2024-05-07 10:00:00+09'),

('a3300002-9999-9999-9999-999999999999'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-5555-5555-5555-555555555555'::uuid,
 'Brabantia ReNew 휴지통 12L 민트', 'ON_SALE', 79000, 42,
 'https://www.brabantia.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-brabantia-master/default/dw3e3e3e3e/bin-mint.jpg',
 '재활용 소재로 만든 친환경 휴지통. 소프트 클로즈 리드와 플라스틱 이너 버킷으로 위생적이고 조용한 사용이 가능합니다.',
 '2024-05-08 10:00:00+09', '2024-05-08 10:00:00+09'),

('a3300003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-5555-5555-5555-555555555555'::uuid,
 'Simplehuman 센서 비누 디스펜서 스테인리스', 'ON_SALE', 169000, 25,
 'https://www.simplehuman.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-simplehuman-master/default/dw3e3e3e3e/sensor-pump-brushed.jpg',
 '터치프리 센서 디스펜서. 정확한 분사량과 세련된 스테인리스 디자인으로 위생적이고 스타일리시한 욕실을 완성합니다.',
 '2024-05-09 10:00:00+09', '2024-05-09 10:00:00+09'),

-- 31-40: 기타 생활용품
('a3300003-1111-1111-1111-111111111111'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-3333-3333-3333-333333333333'::uuid,
 'OXO Good Grips 음식물 보관 용기 세트 20pcs', 'ON_SALE', 129000, 30,
 'https://www.oxo.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-oxo-master/default/dw3e3e3e3e/food-storage-set.jpg',
 '밀폐력 뛰어난 보관 용기 세트. BPA 프리 플라스틱과 4방향 잠금 뚜껑으로 신선도를 오래 유지합니다.',
 '2024-05-10 10:00:00+09', '2024-05-10 10:00:00+09'),

('a3300003-2222-2222-2222-222222222222'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-3333-3333-3333-333333333333'::uuid,
 'Dyson V15 Detect 무선 청소기', 'ON_SALE', 1149000, 7,
 'https://dyson-h.assetsadobe2.com/is/image/content/dam/dyson/products/vacuums/v15-detect/v15-detect-absolute.png',
 '레이저로 먼지를 감지하는 차세대 무선 청소기. 240AW 흡입력과 60분 사용시간으로 완벽한 청소를 실현합니다.',
 '2024-05-11 10:00:00+09', '2024-05-11 10:00:00+09'),

('a3300003-3333-3333-3333-333333333333'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-4444-4444-4444-444444444444'::uuid,
 'Hay Colour Vase 화병 세트 M 클리어', 'ON_SALE', 89000, 35,
 'https://hay.dk/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-hay-master/default/dw3e3e3e3e/colour-vase-clear.jpg',
 '덴마크 헤이의 컬러 글라스 화병. 핸드메이드 유리 공예로 제작되어 꽃꽂이뿐 아니라 오브제로도 아름다운 제품입니다.',
 '2024-05-12 10:00:00+09', '2024-05-12 10:00:00+09'),

('a3300003-4444-4444-4444-444444444444'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-4444-4444-4444-444444444444'::uuid,
 'Georg Jensen Sky 촛대 스테인리스', 'ON_SALE', 189000, 15,
 'https://www.georgjensen.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-GJ-master/default/dw3e3e3e3e/sky-candleholder.jpg',
 '유려한 곡선의 스칸디나비안 디자인. 거울면 폴리싱 스테인리스로 우아한 식탁이나 거실을 연출하는 촛대입니다.',
 '2024-05-13 10:00:00+09', '2024-05-13 10:00:00+09'),

('a3300003-5555-5555-5555-555555555555'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-4444-4444-4444-444444444444'::uuid,
 'Muuto Dots 월 후크 세트 5pcs 오크', 'ON_SALE', 159000, 25,
 'https://www.muuto.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-muuto-master/default/dw3e3e3e3e/dots-oak.jpg',
 '노르딕 미니멀 디자인의 벽걸이 훅. 원목과 간결한 형태로 실용성과 인테리어를 동시에 완성하는 뮤토의 시그니처 제품입니다.',
 '2024-05-14 10:00:00+09', '2024-05-14 10:00:00+09'),

('a3300003-6666-6666-6666-666666666666'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-3333-3333-3333-333333333333'::uuid,
 'Staub 세라믹 베이킹 디쉬 32cm 화이트', 'ON_SALE', 129000, 20,
 'https://www.zwilling.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-zwilling-master/default/dw3e3e3e3e/staub-ceramic-dish.jpg',
 '프랑스 스토브의 세라믹 베이킹 디쉬. 250도 오븐 사용 가능하고 냉동에서 오븐까지 바로 사용 가능한 다용도 그릇입니다.',
 '2024-05-15 10:00:00+09', '2024-05-15 10:00:00+09'),

('a3300003-7777-7777-7777-777777777777'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-3333-3333-3333-333333333333'::uuid,
 'Tovolo 킹 큐브 아이스 트레이 실리콘', 'ON_SALE', 28000, 60,
 'https://www.tovolo.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-tovolo-master/default/dw3e3e3e3e/king-cube-tray.jpg',
 '5cm 대형 아이스 큐브 트레이. 실리콘 소재로 분리가 쉽고 위스키나 칵테일에 이상적인 프리미엄 얼음을 만듭니다.',
 '2024-05-16 10:00:00+09', '2024-05-16 10:00:00+09'),

('a3300003-8888-8888-8888-888888888888'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-2222-2222-2222-222222222222'::uuid,
 'Brooklinen 럭스케어 타월 세트 화이트', 'ON_SALE', 189000, 28,
 'https://cdn.brooklinen.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-brooklinen-master/default/dw3e3e3e3e/towel-set-white.jpg',
 '호텔급 터키산 타월 세트. 600GSM 밀도와 긴 루프로 부드럽고 흡수력이 뛰어난 프리미엄 욕실 타월입니다.',
 '2024-05-17 10:00:00+09', '2024-05-17 10:00:00+09'),

('a3300003-9999-9999-9999-999999999999'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-1111-1111-1111-111111111111'::uuid,
 'String Pocket 월 쉘프 화이트/오크', 'ON_SALE', 289000, 12,
 'https://string.se/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-string-master/default/dw3e3e3e3e/pocket-white-oak.jpg',
 '스웨덴 스트링의 모듈러 선반 시스템. 미니멀 디자인과 다양한 확장성으로 어디든 설치 가능한 북유럽 명품 가구입니다.',
 '2024-05-18 10:00:00+09', '2024-05-18 10:00:00+09'),

('a3300004-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1333333-3333-3333-3333-333333333333'::uuid, '홈리빙365', 'a5db5555-4444-4444-4444-444444444444'::uuid,
 'Diptyque Baies 캔들 190g', 'ON_SALE', 98000, 40,
 'https://www.diptyqueparis.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-diptyque-master/default/dw3e3e3e3e/baies-candle.jpg',
 '딥티크의 시그니처 베리 향초. 블랙커런트와 장미 향이 어우러진 우아한 향으로 공간을 채우는 럭셔리 캔들입니다.',
 '2024-05-19 10:00:00+09', '2024-05-19 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1943002-1111-1111-1111-111111111111'::uuid, 'a3300002-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://www.aesop.com/u1nb1km7t5q7/5e5e5e5e5e5e5e5e5e5e5e5e5e5e5e5e/Aesop-Resurrection-Aromatique-Hand-Wash-500mL-large.png', 0, '2024-04-30 10:00:00+09', '2024-04-30 10:00:00+09'),
('a1943002-2222-2222-2222-111111111111'::uuid, 'a3300002-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.malinandgoetz.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-msg-master/default/dw3e3e3e3e/peppermint_shampoo.png', 0, '2024-05-01 10:00:00+09', '2024-05-01 10:00:00+09'),
('a1943002-3333-3333-3333-111111111111'::uuid, 'a3300002-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://www.diptyqueparis.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-diptyque-master/default/dw3e3e3e3e/do-son-body-lotion.png', 0, '2024-05-02 10:00:00+09', '2024-05-02 10:00:00+09'),
('a1943002-4444-4444-4444-111111111111'::uuid, 'a3300002-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://aquis.com/cdn/shop/products/Lisse-Hair-Turban-White.jpg', 0, '2024-05-03 10:00:00+09', '2024-05-03 10:00:00+09'),
('a1943002-5555-5555-5555-111111111111'::uuid, 'a3300002-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://www.waterworks.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-waterworks-master/default/dw3e3e3e3e/robe-white.jpg', 0, '2024-05-04 10:00:00+09', '2024-05-04 10:00:00+09'),
('a1943002-6666-6666-6666-111111111111'::uuid, 'a3300002-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.josephjoseph.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/s/l/slim_soap_dispenser_grey.jpg', 0, '2024-05-05 10:00:00+09', '2024-05-05 10:00:00+09'),
('a1943002-7777-7777-7777-111111111111'::uuid, 'a3300002-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.umbra.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-umbra-master/default/dw3e3e3e3e/hub-mirror-black.jpg', 0, '2024-05-06 10:00:00+09', '2024-05-06 10:00:00+09'),
('a1943002-8888-8888-8888-111111111111'::uuid, 'a3300002-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.kassatex.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-kassatex-master/default/dw3e3e3e3e/bath-rug-white.jpg', 0, '2024-05-07 10:00:00+09', '2024-05-07 10:00:00+09'),
('a1943002-9999-9999-9999-111111111111'::uuid, 'a3300002-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.brabantia.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-brabantia-master/default/dw3e3e3e3e/bin-mint.jpg', 0, '2024-05-08 10:00:00+09', '2024-05-08 10:00:00+09'),
('a1943003-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3300003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.simplehuman.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-simplehuman-master/default/dw3e3e3e3e/sensor-pump-brushed.jpg', 0, '2024-05-09 10:00:00+09', '2024-05-09 10:00:00+09'),
('a1943003-1111-1111-1111-111111111111'::uuid, 'a3300003-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://www.oxo.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-oxo-master/default/dw3e3e3e3e/food-storage-set.jpg', 0, '2024-05-10 10:00:00+09', '2024-05-10 10:00:00+09'),
('a1943003-2222-2222-2222-111111111111'::uuid, 'a3300003-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://dyson-h.assetsadobe2.com/is/image/content/dam/dyson/products/vacuums/v15-detect/v15-detect-absolute.png', 0, '2024-05-11 10:00:00+09', '2024-05-11 10:00:00+09'),
('a1943003-3333-3333-3333-111111111111'::uuid, 'a3300003-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://hay.dk/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-hay-master/default/dw3e3e3e3e/colour-vase-clear.jpg', 0, '2024-05-12 10:00:00+09', '2024-05-12 10:00:00+09'),
('a1943003-4444-4444-4444-111111111111'::uuid, 'a3300003-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.georgjensen.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-GJ-master/default/dw3e3e3e3e/sky-candleholder.jpg', 0, '2024-05-13 10:00:00+09', '2024-05-13 10:00:00+09'),
('a1943003-5555-5555-5555-111111111111'::uuid, 'a3300003-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://www.muuto.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-muuto-master/default/dw3e3e3e3e/dots-oak.jpg', 0, '2024-05-14 10:00:00+09', '2024-05-14 10:00:00+09'),
('a1943003-6666-6666-6666-111111111111'::uuid, 'a3300003-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.zwilling.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-zwilling-master/default/dw3e3e3e3e/staub-ceramic-dish.jpg', 0, '2024-05-15 10:00:00+09', '2024-05-15 10:00:00+09'),
('a1943003-7777-7777-7777-111111111111'::uuid, 'a3300003-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.tovolo.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-tovolo-master/default/dw3e3e3e3e/king-cube-tray.jpg', 0, '2024-05-16 10:00:00+09', '2024-05-16 10:00:00+09'),
('a1943003-8888-8888-8888-111111111111'::uuid, 'a3300003-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://cdn.brooklinen.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-brooklinen-master/default/dw3e3e3e3e/towel-set-white.jpg', 0, '2024-05-17 10:00:00+09', '2024-05-17 10:00:00+09'),
('a1943003-9999-9999-9999-111111111111'::uuid, 'a3300003-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://string.se/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-string-master/default/dw3e3e3e3e/pocket-white-oak.jpg', 0, '2024-05-18 10:00:00+09', '2024-05-18 10:00:00+09'),
('a1943004-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3300004-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.diptyqueparis.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-diptyque-master/default/dw3e3e3e3e/baies-candle.jpg', 0, '2024-05-19 10:00:00+09', '2024-05-19 10:00:00+09');