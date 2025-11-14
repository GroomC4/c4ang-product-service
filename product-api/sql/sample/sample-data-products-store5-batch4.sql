-- ========================================
-- 상품 데이터 - 5번째 판매자(스포츠프로) 상품 31~40
-- ========================================

-- 상품 31-40 (스포츠프로 - 운동용품, 캠핑용품, 헬스/피트니스)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 31. 운동화 - 테니스화
('a3500003-1111-1111-1111-111111111111'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-2222-2222-2222-222222222222'::uuid,
 'Nike Court Air Zoom Vapor Pro 테니스화 화이트', 'ON_SALE', 189000, 40,
 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/court-air-zoom-vapor-pro-tennis-shoes.png',
 '나이키 에어 줌 베이퍼 프로. 반응성 좋은 쿠셔닝과 안정적인 지지력으로 코트에서 빠른 움직임을 제공하는 테니스화입니다.',
 '2024-07-10 10:00:00+09', '2024-07-10 10:00:00+09'),

-- 32. 운동복 - 수영복
('a3500003-2222-2222-2222-222222222222'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-1111-1111-1111-111111111111'::uuid,
 'Speedo Fastskin LZR Racer X 경기용 수영복 블랙', 'ON_SALE', 589000, 15,
 'https://www.speedousa.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-speedo-master/default/dw3e3e3e3e/images/products/Fastskin_LZR_Racer_X_Black.jpg',
 '스피도 패스트스킨 LZR 레이서 X. FINA 승인 경기용 수영복으로 수중 저항을 최소화하고 속도를 극대화합니다.',
 '2024-07-11 10:00:00+09', '2024-07-11 10:00:00+09'),

-- 33. 헬스/피트니스 - 플라이오 박스
('a3500003-3333-3333-3333-333333333333'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-3333-3333-3333-333333333333'::uuid,
 'Rogue 플라이오메트릭 박스 20"/24"/30" 3in1', 'ON_SALE', 259000, 25,
 'https://www.roguefitness.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-rogue-master/default/dw3e3e3e3e/images/products/Plyo_Box_3in1.jpg',
 '로그 플라이오 박스 3in1. 20, 24, 30인치 3가지 높이로 사용 가능한 점프 트레이닝 필수 장비입니다.',
 '2024-07-12 10:00:00+09', '2024-07-12 10:00:00+09'),

-- 34. 헬스/피트니스 - 짐링
('a3500003-4444-4444-4444-444444444444'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-3333-3333-3333-333333333333'::uuid,
 'Rogue Wood Gymnastic Rings 우드 짐링', 'ON_SALE', 79000, 35,
 'https://www.roguefitness.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-rogue-master/default/dw3e3e3e3e/images/products/Wood_Gymnastic_Rings.jpg',
 '로그 우드 짐링. 체조 선수들이 사용하는 우드 링으로 상체 근력과 안정성을 극대화하는 캘리스테닉스 장비입니다.',
 '2024-07-13 10:00:00+09', '2024-07-13 10:00:00+09'),

-- 35. 캠핑용품 - 버너
('a3500003-5555-5555-5555-555555555555'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-5555-5555-5555-555555555555'::uuid,
 'Coleman Classic 프로판 스토브 2버너', 'ON_SALE', 149000, 30,
 'https://www.coleman.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-coleman-master/default/dw3e3e3e3e/images/products/Classic_Propane_Stove_2_Burner.jpg',
 '콜맨 클래식 프로판 스토브. 2개의 버너로 20,000 BTU 화력을 제공하여 캠핑 요리를 빠르고 쉽게 만듭니다.',
 '2024-07-14 10:00:00+09', '2024-07-14 10:00:00+09'),

-- 36. 캠핑용품 - 백팩
('a3500003-6666-6666-6666-666666666666'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-5555-5555-5555-555555555555'::uuid,
 'Osprey Atmos AG 65 백패킹 백팩 그레이', 'ON_SALE', 459000, 20,
 'https://www.osprey.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-osprey-master/default/dw3e3e3e3e/images/products/Atmos_AG_65_Grey.jpg',
 '오스프레이 아트모스 AG 65. Anti-Gravity 서스펜션과 65L 용량으로 장거리 트레킹에 최적화된 프리미엄 백팩입니다.',
 '2024-07-15 10:00:00+09', '2024-07-15 10:00:00+09'),

-- 37. 운동복 - 사이클 져지
('a3500003-7777-7777-7777-777777777777'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-1111-1111-1111-111111111111'::uuid,
 'Castelli Aero Race 6.0 사이클 져지 블랙', 'ON_SALE', 189000, 30,
 'https://www.castelli-cycling.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-castelli-master/default/dw3e3e3e3e/images/products/Aero_Race_6_Jersey_Black.jpg',
 '카스텔리 에어로 레이스 6.0. 공기역학적 디자인과 속건성 원단으로 레이싱 퍼포먼스를 극대화하는 프로 사이클 져지입니다.',
 '2024-07-16 10:00:00+09', '2024-07-16 10:00:00+09'),

-- 38. 헬스/피트니스 - 요가 휠
('a3500003-8888-8888-8888-888888888888'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-4444-4444-4444-444444444444'::uuid,
 'UpCircleSeven 요가 휠 프리미엄 12" 블랙', 'ON_SALE', 69000, 45,
 'https://www.upcircleseven.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-upc7-master/default/dw3e3e3e3e/images/products/Yoga_Wheel_Premium_12_Black.jpg',
 'UpCircleSeven 요가 휠. 척추 스트레칭과 백벤드 연습에 완벽한 12인치 요가 휠로 유연성과 균형감을 향상시킵니다.',
 '2024-07-17 10:00:00+09', '2024-07-17 10:00:00+09'),

-- 39. 헬스/피트니스 - 워터보틀
('a3500003-9999-9999-9999-999999999999'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-3333-3333-3333-333333333333'::uuid,
 'Hydro Flask 스탠다드 마우스 보틀 946ml 블랙', 'ON_SALE', 59000, 80,
 'https://www.hydroflask.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-hydroflask-master/default/dw3e3e3e3e/images/products/Standard_Mouth_Bottle_946ml_Black.jpg',
 '하이드로플라스크 스탠다드 마우스. 이중벽 진공 단열로 24시간 차갑게, 12시간 뜨겁게 유지하는 프리미엄 보온병입니다.',
 '2024-07-18 10:00:00+09', '2024-07-18 10:00:00+09'),

-- 40. 캠핑용품 - 해먹
('a3500004-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-5555-5555-5555-555555555555'::uuid,
 'ENO DoubleNest 해먹 올리브/오렌지', 'ON_SALE', 89000, 50,
 'https://www.eaglesnestoutfittersinc.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-eno-master/default/dw3e3e3e3e/images/products/DoubleNest_Hammock_Olive_Orange.jpg',
 'ENO 더블네스트 해먹. 2인용 캠핑 해먹으로 가볍고 튼튼한 나일론 소재와 간편한 설치로 자연 속 휴식을 완벽하게 즐깁니다.',
 '2024-07-19 10:00:00+09', '2024-07-19 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1945003-1111-1111-1111-111111111111'::uuid, 'a3500003-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/court-air-zoom-vapor-pro-tennis-shoes.png', 0, '2024-07-10 10:00:00+09', '2024-07-10 10:00:00+09'),
('a1945003-2222-2222-2222-111111111111'::uuid, 'a3500003-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.speedousa.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-speedo-master/default/dw3e3e3e3e/images/products/Fastskin_LZR_Racer_X_Black.jpg', 0, '2024-07-11 10:00:00+09', '2024-07-11 10:00:00+09'),
('a1945003-3333-3333-3333-111111111111'::uuid, 'a3500003-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://www.roguefitness.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-rogue-master/default/dw3e3e3e3e/images/products/Plyo_Box_3in1.jpg', 0, '2024-07-12 10:00:00+09', '2024-07-12 10:00:00+09'),
('a1945003-4444-4444-4444-111111111111'::uuid, 'a3500003-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.roguefitness.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-rogue-master/default/dw3e3e3e3e/images/products/Wood_Gymnastic_Rings.jpg', 0, '2024-07-13 10:00:00+09', '2024-07-13 10:00:00+09'),
('a1945003-5555-5555-5555-111111111111'::uuid, 'a3500003-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://www.coleman.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-coleman-master/default/dw3e3e3e3e/images/products/Classic_Propane_Stove_2_Burner.jpg', 0, '2024-07-14 10:00:00+09', '2024-07-14 10:00:00+09'),
('a1945003-6666-6666-6666-111111111111'::uuid, 'a3500003-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.osprey.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-osprey-master/default/dw3e3e3e3e/images/products/Atmos_AG_65_Grey.jpg', 0, '2024-07-15 10:00:00+09', '2024-07-15 10:00:00+09'),
('a1945003-7777-7777-7777-111111111111'::uuid, 'a3500003-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.castelli-cycling.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-castelli-master/default/dw3e3e3e3e/images/products/Aero_Race_6_Jersey_Black.jpg', 0, '2024-07-16 10:00:00+09', '2024-07-16 10:00:00+09'),
('a1945003-8888-8888-8888-111111111111'::uuid, 'a3500003-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.upcircleseven.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-upc7-master/default/dw3e3e3e3e/images/products/Yoga_Wheel_Premium_12_Black.jpg', 0, '2024-07-17 10:00:00+09', '2024-07-17 10:00:00+09'),
('a1945003-9999-9999-9999-111111111111'::uuid, 'a3500003-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.hydroflask.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-hydroflask-master/default/dw3e3e3e3e/images/products/Standard_Mouth_Bottle_946ml_Black.jpg', 0, '2024-07-18 10:00:00+09', '2024-07-18 10:00:00+09'),
('a1945004-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3500004-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.eaglesnestoutfittersinc.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-eno-master/default/dw3e3e3e3e/images/products/DoubleNest_Hammock_Olive_Orange.jpg', 0, '2024-07-19 10:00:00+09', '2024-07-19 10:00:00+09');
