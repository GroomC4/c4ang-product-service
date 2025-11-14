-- ========================================
-- 상품 데이터 - 5번째 판매자(스포츠프로) 상품 11~20
-- ========================================

-- 상품 11-20 (스포츠프로 - 헬스/피트니스, 요가/필라테스)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 11. 헬스/피트니스 - 덤벨 세트
('a3500001-1111-1111-1111-111111111111'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-3333-3333-3333-333333333333'::uuid,
 'Bowflex SelectTech 552 조절식 덤벨 세트', 'ON_SALE', 589000, 20,
 'https://www.bowflex.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-nautilus-master/default/dw3e3e3e3e/images/products/SelectTech_552_Dumbbells.jpg',
 '보우플렉스 셀렉텍 552 덤벨. 2.5kg~24kg까지 15단계 조절 가능한 공간 절약형 홈 트레이닝 필수 아이템입니다.',
 '2024-06-20 10:00:00+09', '2024-06-20 10:00:00+09'),

-- 12. 헬스/피트니스 - 프로틴 파우더
('a3500001-2222-2222-2222-222222222222'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-3333-3333-3333-333333333333'::uuid,
 'Optimum Nutrition Gold Standard 100% 웨이 더블 리치 초콜릿 2.27kg', 'ON_SALE', 89000, 60,
 'https://www.optimumnutrition.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-on-master/default/dw3e3e3e3e/images/products/Gold_Standard_Whey_Double_Rich_Chocolate_2270g.jpg',
 '옵티멈 뉴트리션 골드 스탠다드. 24g 단백질 함유로 근육 회복과 성장을 돕는 세계 1위 웨이 프로틴입니다.',
 '2024-06-21 10:00:00+09', '2024-06-21 10:00:00+09'),

-- 13. 헬스/피트니스 - 저항 밴드
('a3500001-3333-3333-3333-333333333333'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-3333-3333-3333-333333333333'::uuid,
 'TheraBand 저항 밴드 세트 5개입', 'ON_SALE', 35000, 80,
 'https://www.theraband.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-theraband-master/default/dw3e3e3e3e/images/products/Resistance_Band_Set_5.jpg',
 '세라밴드 저항 밴드 세트. 5가지 강도로 구성되어 재활부터 근력 강화까지 다양한 운동에 활용 가능합니다.',
 '2024-06-22 10:00:00+09', '2024-06-22 10:00:00+09'),

-- 14. 헬스/피트니스 - 폼롤러
('a3500001-4444-4444-4444-444444444444'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-3333-3333-3333-333333333333'::uuid,
 'TriggerPoint GRID 폼롤러 블랙', 'ON_SALE', 49000, 70,
 'https://www.tptherapy.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-trigger-point-master/default/dw3e3e3e3e/images/products/GRID_Foam_Roller_Black.jpg',
 '트리거포인트 그리드 폼롤러. 3차원 표면 디자인으로 근막 이완과 근육 회복을 효과적으로 돕는 셀프 마사지 도구입니다.',
 '2024-06-23 10:00:00+09', '2024-06-23 10:00:00+09'),

-- 15. 헬스/피트니스 - 짐백
('a3500001-5555-5555-5555-555555555555'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-3333-3333-3333-333333333333'::uuid,
 'Nike Brasilia 트레이닝 더플백 미디엄 블랙', 'ON_SALE', 45000, 90,
 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/brasilia-training-duffel-bag.png',
 '나이키 브라질리아 더플백. 넉넉한 수납 공간과 분리된 신발 수납 공간으로 헬스장 필수 가방입니다.',
 '2024-06-24 10:00:00+09', '2024-06-24 10:00:00+09'),

-- 16. 요가/필라테스 - 요가 매트
('a3500001-6666-6666-6666-666666666666'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-4444-4444-4444-444444444444'::uuid,
 'Manduka PRO 요가 매트 블랙 6mm', 'ON_SALE', 169000, 40,
 'https://www.manduka.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-manduka-master/default/dw3e3e3e3e/images/products/PRO_Yoga_Mat_Black_6mm.jpg',
 '만두카 PRO 요가 매트. 평생 보증과 뛰어난 쿠셔닝, 완벽한 그립력으로 모든 요가 수련에 최적화된 프리미엄 매트입니다.',
 '2024-06-25 10:00:00+09', '2024-06-25 10:00:00+09'),

-- 17. 요가/필라테스 - 요가 블록
('a3500001-7777-7777-7777-777777777777'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-4444-4444-4444-444444444444'::uuid,
 'Gaiam 코르크 요가 블록 2개 세트', 'ON_SALE', 39000, 60,
 'https://www.gaiam.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-gaiam-master/default/dw3e3e3e3e/images/products/Cork_Yoga_Block_2_Pack.jpg',
 '가이암 코르크 요가 블록. 천연 코르크 소재로 안정적인 지지력과 친환경성을 갖춘 요가 보조 도구입니다.',
 '2024-06-26 10:00:00+09', '2024-06-26 10:00:00+09'),

-- 18. 요가/필라테스 - 요가 스트랩
('a3500001-8888-8888-8888-888888888888'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-4444-4444-4444-444444444444'::uuid,
 'Lululemon Align Yoga Strap 2.4m 퍼플', 'ON_SALE', 28000, 70,
 'https://images.lululemon.com/is/image/lululemon/LU9BCZS_045654_1?wid=1080&op_usm=0.5,2,10,0',
 '룰루레몬 얼라인 요가 스트랩. 부드러운 코튼 소재와 안전한 버클로 스트레칭 범위를 확장하고 자세를 교정합니다.',
 '2024-06-27 10:00:00+09', '2024-06-27 10:00:00+09'),

-- 19. 요가/필라테스 - 필라테스 링
('a3500001-9999-9999-9999-999999999999'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-4444-4444-4444-444444444444'::uuid,
 'STOTT PILATES 필라테스 링 14인치', 'ON_SALE', 45000, 50,
 'https://www.stottpilates.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-stott-master/default/dw3e3e3e3e/images/products/Pilates_Ring_14.jpg',
 'STOTT 필라테스 링. 저항 운동으로 코어 강화와 근력 향상에 효과적인 필라테스 필수 도구입니다.',
 '2024-06-28 10:00:00+09', '2024-06-28 10:00:00+09'),

-- 20. 요가/필라테스 - 볼스터
('a3500002-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1555555-5555-5555-5555-555555555555'::uuid, '스포츠프로', 'a5db4444-4444-4444-4444-444444444444'::uuid,
 'Hugger Mugger 요가 볼스터 표준 퍼플', 'ON_SALE', 89000, 35,
 'https://www.huggermugger.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-hugger-master/default/dw3e3e3e3e/images/products/Standard_Yoga_Bolster_Purple.jpg',
 '허거 머거 요가 볼스터. 리스토러티브 요가와 명상에 최적화된 든든한 지지력과 편안함을 제공하는 쿠션입니다.',
 '2024-06-29 10:00:00+09', '2024-06-29 10:00:00+09');

-- 상품 이미지 데이터
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
('a1945001-1111-1111-1111-111111111111'::uuid, 'a3500001-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://www.bowflex.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-nautilus-master/default/dw3e3e3e3e/images/products/SelectTech_552_Dumbbells.jpg', 0, '2024-06-20 10:00:00+09', '2024-06-20 10:00:00+09'),
('a1945001-2222-2222-2222-111111111111'::uuid, 'a3500001-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.optimumnutrition.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-on-master/default/dw3e3e3e3e/images/products/Gold_Standard_Whey_Double_Rich_Chocolate_2270g.jpg', 0, '2024-06-21 10:00:00+09', '2024-06-21 10:00:00+09'),
('a1945001-3333-3333-3333-111111111111'::uuid, 'a3500001-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://www.theraband.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-theraband-master/default/dw3e3e3e3e/images/products/Resistance_Band_Set_5.jpg', 0, '2024-06-22 10:00:00+09', '2024-06-22 10:00:00+09'),
('a1945001-4444-4444-4444-111111111111'::uuid, 'a3500001-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.tptherapy.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-trigger-point-master/default/dw3e3e3e3e/images/products/GRID_Foam_Roller_Black.jpg', 0, '2024-06-23 10:00:00+09', '2024-06-23 10:00:00+09'),
('a1945001-5555-5555-5555-111111111111'::uuid, 'a3500001-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/brasilia-training-duffel-bag.png', 0, '2024-06-24 10:00:00+09', '2024-06-24 10:00:00+09'),
('a1945001-6666-6666-6666-111111111111'::uuid, 'a3500001-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.manduka.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-manduka-master/default/dw3e3e3e3e/images/products/PRO_Yoga_Mat_Black_6mm.jpg', 0, '2024-06-25 10:00:00+09', '2024-06-25 10:00:00+09'),
('a1945001-7777-7777-7777-111111111111'::uuid, 'a3500001-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.gaiam.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-gaiam-master/default/dw3e3e3e3e/images/products/Cork_Yoga_Block_2_Pack.jpg', 0, '2024-06-26 10:00:00+09', '2024-06-26 10:00:00+09'),
('a1945001-8888-8888-8888-111111111111'::uuid, 'a3500001-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://images.lululemon.com/is/image/lululemon/LU9BCZS_045654_1?wid=1080&op_usm=0.5,2,10,0', 0, '2024-06-27 10:00:00+09', '2024-06-27 10:00:00+09'),
('a1945001-9999-9999-9999-111111111111'::uuid, 'a3500001-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.stottpilates.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-stott-master/default/dw3e3e3e3e/images/products/Pilates_Ring_14.jpg', 0, '2024-06-28 10:00:00+09', '2024-06-28 10:00:00+09'),
('a1945002-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3500002-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.huggermugger.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-hugger-master/default/dw3e3e3e3e/images/products/Standard_Yoga_Bolster_Purple.jpg', 0, '2024-06-29 10:00:00+09', '2024-06-29 10:00:00+09');
