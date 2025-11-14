-- ========================================
-- 상품 데이터 - 5번째 판매자(스포츠프로) 상품 21~30
-- ========================================

-- 상품 21-30 (스포츠프로 - 캠핑용품, 운동용품)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 21. 캠핑용품 - 텐트
('a3500002-1111-1111-1111-111111111111'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-5555-5555-5555-555555555555'::uuid,
 'Coleman Sundome 4인용 텐트 그린', 'ON_SALE', 189000, 25,
 'https://www.coleman.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-coleman-master/default/dw3e3e3e3e/images/products/Sundome_4_Person_Tent_Green.jpg',
 '콜맨 선돔 4인용 텐트. 방수 기능과 WeatherTec 시스템으로 비와 바람을 막아주는 믿을 수 있는 패밀리 캠핑 텐트입니다.',
 '2024-06-30 10:00:00+09', '2024-06-30 10:00:00+09'),

-- 22. 캠핑용품 - 침낭
('a3500002-2222-2222-2222-222222222222'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-5555-5555-5555-555555555555'::uuid,
 'The North Face Eco Trail Bed 20 침낭', 'ON_SALE', 159000, 30,
 'https://images.thenorthface.com/is/image/TheNorthFace/NF0A3S8V_JK3_hero?wid=1200&hei=1200',
 '노스페이스 에코 트레일 베드 20. 재활용 소재와 -7°C까지 보온하는 3시즌 침낭으로 편안한 캠핑을 제공합니다.',
 '2024-07-01 10:00:00+09', '2024-07-01 10:00:00+09'),

-- 23. 캠핑용품 - 캠핑 의자
('a3500002-3333-3333-3333-333333333333'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-5555-5555-5555-555555555555'::uuid,
 'Helinox Chair One 초경량 접이식 의자 블랙', 'ON_SALE', 139000, 40,
 'https://www.helinox.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-helinox-master/default/dw3e3e3e3e/images/products/Chair_One_Black.jpg',
 '헬리녹스 체어 원. 960g 초경량과 강력한 내구성으로 백패킹과 페스티벌에 최적화된 프리미엄 캠핑 의자입니다.',
 '2024-07-02 10:00:00+09', '2024-07-02 10:00:00+09'),

-- 24. 캠핑용품 - 쿨러
('a3500002-4444-4444-4444-444444444444'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-5555-5555-5555-555555555555'::uuid,
 'YETI Tundra 45 쿨러 화이트', 'ON_SALE', 589000, 15,
 'https://www.yeti.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-yeti-master/default/dw3e3e3e3e/images/products/Tundra_45_White.jpg',
 '예티 툰드라 45 쿨러. 로토몰딩 구조와 3인치 두께 벽으로 최대 7일간 얼음을 유지하는 프리미엄 하드 쿨러입니다.',
 '2024-07-03 10:00:00+09', '2024-07-03 10:00:00+09'),

-- 25. 캠핑용품 - 랜턴
('a3500002-5555-5555-5555-555555555555'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-5555-5555-5555-555555555555'::uuid,
 'Coleman Quad LED 랜턴 800루멘', 'ON_SALE', 79000, 50,
 'https://www.coleman.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-coleman-master/default/dw3e3e3e3e/images/products/Quad_LED_Lantern_800.jpg',
 '콜맨 쿼드 LED 랜턴. 4개의 분리형 패널로 개별 사용 가능하고 800루멘 밝기로 캠핑장을 환하게 밝혀줍니다.',
 '2024-07-04 10:00:00+09', '2024-07-04 10:00:00+09'),

-- 26. 운동복 - 압축 상의
('a3500002-6666-6666-6666-666666666666'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-1111-1111-1111-111111111111'::uuid,
 'Under Armour HeatGear 압축 롱슬리브 블랙', 'ON_SALE', 65000, 70,
 'https://underarmour.scene7.com/is/image/Underarmour/V5-1257471-001_HF?rp=standard-0pad|gridCellLarge&scl=1&fmt=jpg&qlt=85&resMode=sharp2&cache=on,on&bgc=F0F0F0',
 '언더아머 히트기어 압축 상의. 땀을 빠르게 배출하고 근육을 지지하여 운동 효율을 높이는 컴프레션 웨어입니다.',
 '2024-07-05 10:00:00+09', '2024-07-05 10:00:00+09'),

-- 27. 운동화 - 하이킹 부츠
('a3500002-7777-7777-7777-777777777777'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-2222-2222-2222-222222222222'::uuid,
 'Salomon X Ultra 4 Mid GTX 하이킹 부츠', 'ON_SALE', 249000, 35,
 'https://www.salomon.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-salomon-master/default/dw3e3e3e3e/images/products/X_Ultra_4_Mid_GTX.jpg',
 '살로몬 X 울트라 4 미드 GTX. 고어텍스 방수와 뛰어난 그립력으로 거친 산악 지형을 정복하는 하이킹 부츠입니다.',
 '2024-07-06 10:00:00+09', '2024-07-06 10:00:00+09'),

-- 28. 헬스/피트니스 - 케틀벨
('a3500002-8888-8888-8888-888888888888'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-3333-3333-3333-333333333333'::uuid,
 'Rogue Fitness 케틀벨 16kg', 'ON_SALE', 89000, 40,
 'https://www.roguefitness.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-rogue-master/default/dw3e3e3e3e/images/products/Kettlebell_16kg.jpg',
 '로그 피트니스 케틀벨 16kg. 주철 일체형 구조와 파우더 코팅으로 내구성이 뛰어나 전신 근력 운동에 최적입니다.',
 '2024-07-07 10:00:00+09', '2024-07-07 10:00:00+09'),

-- 29. 헬스/피트니스 - 스피드 로프
('a3500002-9999-9999-9999-999999999999'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-3333-3333-3333-333333333333'::uuid,
 'RPM Fitness RPM 스피드 로프 3.0', 'ON_SALE', 45000, 60,
 'https://www.rpmtraining.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-rpm-master/default/dw3e3e3e3e/images/products/Speed_Rope_3_0.jpg',
 'RPM 피트니스 스피드 로프 3.0. 볼 베어링과 고속 회전으로 더블언더와 트리플언더를 쉽게 만드는 프리미엄 줄넘기입니다.',
 '2024-07-08 10:00:00+09', '2024-07-08 10:00:00+09'),

-- 30. 운동복 - 바람막이
('a3500003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-1111-1111-1111-111111111111'::uuid,
 'Nike Windrunner 바람막이 자켓 블랙/화이트', 'ON_SALE', 99000, 65,
 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/windrunner-jacket.png',
 '나이키 윈드러너 바람막이. 아이코닉한 V자 디자인과 경량 소재로 바람과 비를 막아주는 클래식 러닝 재킷입니다.',
 '2024-07-09 10:00:00+09', '2024-07-09 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1945002-1111-1111-1111-111111111111'::uuid, 'a3500002-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://www.coleman.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-coleman-master/default/dw3e3e3e3e/images/products/Sundome_4_Person_Tent_Green.jpg', 0, '2024-06-30 10:00:00+09', '2024-06-30 10:00:00+09'),
('a1945002-2222-2222-2222-111111111111'::uuid, 'a3500002-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://images.thenorthface.com/is/image/TheNorthFace/NF0A3S8V_JK3_hero?wid=1200&hei=1200', 0, '2024-07-01 10:00:00+09', '2024-07-01 10:00:00+09'),
('a1945002-3333-3333-3333-111111111111'::uuid, 'a3500002-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://www.helinox.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-helinox-master/default/dw3e3e3e3e/images/products/Chair_One_Black.jpg', 0, '2024-07-02 10:00:00+09', '2024-07-02 10:00:00+09'),
('a1945002-4444-4444-4444-111111111111'::uuid, 'a3500002-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.yeti.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-yeti-master/default/dw3e3e3e3e/images/products/Tundra_45_White.jpg', 0, '2024-07-03 10:00:00+09', '2024-07-03 10:00:00+09'),
('a1945002-5555-5555-5555-111111111111'::uuid, 'a3500002-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://www.coleman.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-coleman-master/default/dw3e3e3e3e/images/products/Quad_LED_Lantern_800.jpg', 0, '2024-07-04 10:00:00+09', '2024-07-04 10:00:00+09'),
('a1945002-6666-6666-6666-111111111111'::uuid, 'a3500002-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://underarmour.scene7.com/is/image/Underarmour/V5-1257471-001_HF?rp=standard-0pad|gridCellLarge&scl=1&fmt=jpg&qlt=85&resMode=sharp2&cache=on,on&bgc=F0F0F0', 0, '2024-07-05 10:00:00+09', '2024-07-05 10:00:00+09'),
('a1945002-7777-7777-7777-111111111111'::uuid, 'a3500002-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.salomon.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-salomon-master/default/dw3e3e3e3e/images/products/X_Ultra_4_Mid_GTX.jpg', 0, '2024-07-06 10:00:00+09', '2024-07-06 10:00:00+09'),
('a1945002-8888-8888-8888-111111111111'::uuid, 'a3500002-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.roguefitness.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-rogue-master/default/dw3e3e3e3e/images/products/Kettlebell_16kg.jpg', 0, '2024-07-07 10:00:00+09', '2024-07-07 10:00:00+09'),
('a1945002-9999-9999-9999-111111111111'::uuid, 'a3500002-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.rpmtraining.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-rpm-master/default/dw3e3e3e3e/images/products/Speed_Rope_3_0.jpg', 0, '2024-07-08 10:00:00+09', '2024-07-08 10:00:00+09'),
('a1945003-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3500003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/windrunner-jacket.png', 0, '2024-07-09 10:00:00+09', '2024-07-09 10:00:00+09');
