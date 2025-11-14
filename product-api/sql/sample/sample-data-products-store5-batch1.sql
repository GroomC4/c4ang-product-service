-- ========================================
-- 상품 데이터 - 5번째 판매자(스포츠프로) 상품 1~10
-- ========================================

-- 상품 1-10 (스포츠프로 - 운동복, 운동화)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 1. 운동복 - 남성 트레이닝 상의
('a3500000-1111-1111-1111-111111111111'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-1111-1111-1111-111111111111'::uuid,
 'Nike Dri-FIT 남성 트레이닝 티셔츠 블랙', 'ON_SALE', 49000, 100,
 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/dri-fit-training-tee-lpx0Hm.png',
 '나이키 드라이핏 트레이닝 티셔츠. 땀을 빠르게 흡수하고 건조시켜 쾌적한 운동 환경을 유지합니다.',
 '2024-06-10 10:00:00+09', '2024-06-10 10:00:00+09'),

-- 2. 운동복 - 여성 레깅스
('a3500000-2222-2222-2222-222222222222'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-1111-1111-1111-111111111111'::uuid,
 'Lululemon Align High-Rise 레깅스 25" 블랙', 'ON_SALE', 138000, 80,
 'https://images.lululemon.com/is/image/lululemon/LW5CUXS_0001_1?wid=1080&op_usm=0.5,2,10,0',
 '룰루레몬 얼라인 레깅스. Nulu 패브릭의 부드러운 착용감과 4-way 스트레치로 요가부터 데일리까지 완벽합니다.',
 '2024-06-11 10:00:00+09', '2024-06-11 10:00:00+09'),

-- 3. 운동복 - 남성 쇼츠
('a3500000-3333-3333-3333-333333333333'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-1111-1111-1111-111111111111'::uuid,
 'Under Armour 남성 스포츠스타일 쇼츠 블랙', 'ON_SALE', 55000, 90,
 'https://underarmour.scene7.com/is/image/Underarmour/V5-1379780-001_HF?rp=standard-0pad|gridCellLarge&scl=1&fmt=jpg&qlt=85&resMode=sharp2&cache=on,on&bgc=F0F0F0',
 '언더아머 스포츠스타일 쇼츠. 가볍고 통기성 좋은 원단으로 편안한 착용감과 자유로운 움직임을 제공합니다.',
 '2024-06-12 10:00:00+09', '2024-06-12 10:00:00+09'),

-- 4. 운동복 - 여성 스포츠 브라
('a3500000-4444-4444-4444-444444444444'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-1111-1111-1111-111111111111'::uuid,
 'Nike Swoosh 미디엄 서포트 스포츠 브라 블랙', 'ON_SALE', 39000, 70,
 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/swoosh-medium-support-sports-bra-lpx0Hm.png',
 '나이키 스우시 스포츠 브라. 미디엄 서포트와 드라이핏 기술로 중간 강도 운동에 최적화되어 있습니다.',
 '2024-06-13 10:00:00+09', '2024-06-13 10:00:00+09'),

-- 5. 운동복 - 트레이닝 자켓
('a3500000-5555-5555-5555-555555555555'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-1111-1111-1111-111111111111'::uuid,
 'Adidas Tiro 23 트레이닝 자켓 블랙', 'ON_SALE', 79000, 60,
 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e/tiro-23-training-jacket.jpg',
 '아디다스 티로 23 트레이닝 자켓. AEROREADY 기술로 땀을 관리하고 슬림핏 디자인으로 세련된 스타일을 연출합니다.',
 '2024-06-14 10:00:00+09', '2024-06-14 10:00:00+09'),

-- 6. 운동화 - 러닝화
('a3500000-6666-6666-6666-666666666666'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-2222-2222-2222-222222222222'::uuid,
 'Nike Air Zoom Pegasus 40 남성 러닝화', 'ON_SALE', 159000, 50,
 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/air-zoom-pegasus-40-running-shoes.png',
 '나이키 에어 줌 페가수스 40. 반응성 좋은 쿠셔닝과 가벼운 무게로 장거리 러닝에 최적화된 러닝화입니다.',
 '2024-06-15 10:00:00+09', '2024-06-15 10:00:00+09'),

-- 7. 운동화 - 트레이닝화
('a3500000-7777-7777-7777-777777777777'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-2222-2222-2222-222222222222'::uuid,
 'Reebok Nano X3 크로스핏 트레이닝화', 'ON_SALE', 179000, 45,
 'https://assets.reebok.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e/nano-x3-training-shoes.jpg',
 '리복 나노 X3. 크로스핏과 HIIT 운동에 최적화된 안정성과 민첩성을 제공하는 프리미엄 트레이닝화입니다.',
 '2024-06-16 10:00:00+09', '2024-06-16 10:00:00+09'),

-- 8. 운동화 - 농구화
('a3500000-8888-8888-8888-888888888888'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-2222-2222-2222-222222222222'::uuid,
 'Nike LeBron 21 농구화 블랙/화이트', 'ON_SALE', 239000, 35,
 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/lebron-21-basketball-shoes.png',
 '나이키 르브론 21. 에어 줌 유닛과 뛰어난 지지력으로 코트에서 폭발적인 퍼포먼스를 발휘하는 시그니처 농구화입니다.',
 '2024-06-17 10:00:00+09', '2024-06-17 10:00:00+09'),

-- 9. 운동화 - 축구화
('a3500000-9999-9999-9999-999999999999'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-2222-2222-2222-222222222222'::uuid,
 'Adidas Predator Accuracy.1 FG 축구화', 'ON_SALE', 299000, 30,
 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e/predator-accuracy-1-fg-football-boots.jpg',
 '아디다스 프레데터 정확성.1 FG. 하이브리드 터치 어퍼와 파워페이싯으로 정확하고 강력한 슈팅을 구현하는 프리미엄 축구화입니다.',
 '2024-06-18 10:00:00+09', '2024-06-18 10:00:00+09'),

-- 10. 운동화 - 여성 러닝화
('a3500001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-2222-2222-2222-222222222222'::uuid,
 'Brooks Ghost 15 여성 러닝화 핑크/블랙', 'ON_SALE', 169000, 55,
 'https://www.brooksrunning.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-brooks-master/default/dw3e3e3e3e/images/Brooks_Ghost_15_Womens.jpg',
 '브룩스 고스트 15. 부드러운 쿠셔닝과 스무스한 라이드감으로 편안한 중장거리 러닝을 제공하는 여성 러닝화입니다.',
 '2024-06-19 10:00:00+09', '2024-06-19 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1945000-1111-1111-1111-111111111111'::uuid, 'a3500000-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/dri-fit-training-tee-lpx0Hm.png', 0, '2024-06-10 10:00:00+09', '2024-06-10 10:00:00+09'),
('a1945000-2222-2222-2222-111111111111'::uuid, 'a3500000-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://images.lululemon.com/is/image/lululemon/LW5CUXS_0001_1?wid=1080&op_usm=0.5,2,10,0', 0, '2024-06-11 10:00:00+09', '2024-06-11 10:00:00+09'),
('a1945000-3333-3333-3333-111111111111'::uuid, 'a3500000-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://underarmour.scene7.com/is/image/Underarmour/V5-1379780-001_HF?rp=standard-0pad|gridCellLarge&scl=1&fmt=jpg&qlt=85&resMode=sharp2&cache=on,on&bgc=F0F0F0', 0, '2024-06-12 10:00:00+09', '2024-06-12 10:00:00+09'),
('a1945000-4444-4444-4444-111111111111'::uuid, 'a3500000-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/swoosh-medium-support-sports-bra-lpx0Hm.png', 0, '2024-06-13 10:00:00+09', '2024-06-13 10:00:00+09'),
('a1945000-5555-5555-5555-111111111111'::uuid, 'a3500000-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e/tiro-23-training-jacket.jpg', 0, '2024-06-14 10:00:00+09', '2024-06-14 10:00:00+09'),
('a1945000-6666-6666-6666-111111111111'::uuid, 'a3500000-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/air-zoom-pegasus-40-running-shoes.png', 0, '2024-06-15 10:00:00+09', '2024-06-15 10:00:00+09'),
('a1945000-7777-7777-7777-111111111111'::uuid, 'a3500000-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://assets.reebok.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e/nano-x3-training-shoes.jpg', 0, '2024-06-16 10:00:00+09', '2024-06-16 10:00:00+09'),
('a1945000-8888-8888-8888-111111111111'::uuid, 'a3500000-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/lebron-21-basketball-shoes.png', 0, '2024-06-17 10:00:00+09', '2024-06-17 10:00:00+09'),
('a1945000-9999-9999-9999-111111111111'::uuid, 'a3500000-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e/predator-accuracy-1-fg-football-boots.jpg', 0, '2024-06-18 10:00:00+09', '2024-06-18 10:00:00+09'),
('a1945001-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3500001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.brooksrunning.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-brooks-master/default/dw3e3e3e3e/images/Brooks_Ghost_15_Womens.jpg', 0, '2024-06-19 10:00:00+09', '2024-06-19 10:00:00+09');
