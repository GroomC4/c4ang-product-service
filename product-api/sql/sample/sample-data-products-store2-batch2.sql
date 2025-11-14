-- ========================================
-- 상품 데이터 - 2번째 판매자(패션플러스) 상품 11~20
-- ========================================

-- 상품 11-20 (패션플러스 - 신발, 가방)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 11. 남성 스니커즈
('a3200001-1111-1111-1111-111111111111'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-3333-3333-3333-333333333333'::uuid,
 'Nike Air Jordan 1 Retro High OG Chicago Lost & Found', 'ON_SALE', 349000, 30,
 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3f3e7049-5c99-428c-abcf-a38671c851bf/air-jordan-1-retro-high-og-chicago-lost-and-found-dz5485-612.png',
 '아이코닉한 시카고 컬러웨이의 에어조던 1. 빈티지 처리된 디테일과 프리미엄 가죽으로 클래식한 매력을 재현한 스니커즈입니다.',
 '2024-03-11 10:00:00+09', '2024-03-11 10:00:00+09'),

-- 12. 남성 스니커즈
('a3200001-2222-2222-2222-222222222222'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-3333-3333-3333-333333333333'::uuid,
 'Adidas Yeezy Boost 350 V2 Onyx', 'ON_SALE', 489000, 25,
 'https://assets.adidas.com/images/w_600,f_auto,q_auto/3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e_9366/Yeezy_Boost_350_V2_Onyx_Black_HQ4540_01_standard.jpg',
 'Primeknit 어퍼와 Boost 미드솔의 완벽한 조화. 블랙 컬러웨이와 편안한 착용감으로 데일리 스니커즈의 정석입니다.',
 '2024-03-12 10:00:00+09', '2024-03-12 10:00:00+09'),

-- 13. 남성 구두
('a3200001-3333-3333-3333-333333333333'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-3333-3333-3333-333333333333'::uuid,
 'Church''s Consul Oxford 브로그 블랙', 'ON_SALE', 1090000, 15,
 'https://www.church-footwear.com/on/demandware.static/-/Sites-church-master/default/dw3e3e3e3e/images/EEB002/9XV/F0AAB/EEB002_9XV_F0AAB_F_B100.jpg',
 '구두제작의 전설, 처치스의 시그니처 옥스포드. 풀 그레인 가죽과 굿이어웰트 제법으로 제작된 타임리스 드레스 슈즈입니다.',
 '2024-03-13 10:00:00+09', '2024-03-13 10:00:00+09'),

-- 14. 남성 부츠
('a3200001-4444-4444-4444-444444444444'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-3333-3333-3333-333333333333'::uuid,
 'Dr.Martens 1460 8홀 스무스 레더 부츠 블랙', 'ON_SALE', 259000, 40,
 'https://i8.amplience.net/i/jpl/dm_11822006_black_smooth_pd_01?$re6$&w=800&h=800',
 '영국의 아이콘 닥터마틴 1460. 견고한 스무스 레더와 에어쿠션 솔로 어떤 스타일에도 매치 가능한 클래식 부츠입니다.',
 '2024-03-14 10:00:00+09', '2024-03-14 10:00:00+09'),

-- 15. 여성 하이힐
('a3200001-5555-5555-5555-555555555555'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-3333-3333-3333-333333333333'::uuid,
 'Christian Louboutin So Kate 120mm 펌프스 블랙', 'ON_SALE', 1690000, 12,
 'https://us.christianlouboutin.com/on/demandware.static/-/Sites-masterCatalog_CL/default/dw3e3e3e3e/images/3080520/3080520_BK01_1_1200x1200.jpg',
 '레드솔의 아이콘 루부탱 소케이트. 120mm 힐과 포인티드 토로 발목을 길어보이게 하는 시그니처 펌프스입니다.',
 '2024-03-15 10:00:00+09', '2024-03-15 10:00:00+09'),

-- 16. 여성 스니커즈
('a3200001-6666-6666-6666-666666666666'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-3333-3333-3333-333333333333'::uuid,
 'Golden Goose Super-Star 클래식 화이트/실버', 'ON_SALE', 780000, 28,
 'https://www.goldengoose.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-gg-master-catalog/default/dw3e3e3e3e/images/GWF00102F001199_85203_1.jpg',
 '이탈리아 수제 럭셔리 스니커즈. 빈티지 워싱 처리와 스타 로고가 특징인 골든구스의 베스트셀러 모델입니다.',
 '2024-03-16 10:00:00+09', '2024-03-16 10:00:00+09'),

-- 17. 남성 백팩
('a3200001-7777-7777-7777-777777777777'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-4444-4444-4444-444444444444'::uuid,
 'The North Face Borealis 백팩 블랙', 'ON_SALE', 149000, 50,
 'https://images.thenorthface.com/is/image/TheNorthFace/NF0A52SE_JK3_hero?wid=1000&hei=1000',
 'FlexVent 시스템과 다중 수납공간. 15인치 노트북 수납 가능한 데일리 백팩으로 편안함과 실용성을 겸비했습니다.',
 '2024-03-17 10:00:00+09', '2024-03-17 10:00:00+09'),

-- 18. 남성 크로스백
('a3200001-8888-8888-8888-888888888888'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-4444-4444-4444-444444444444'::uuid,
 'Louis Vuitton 크리스토퍼 PM 모노그램 이클립스', 'ON_SALE', 3200000, 8,
 'https://us.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-christopher-pm-monogram-eclipse-canvas-bags--M45419_PM2_Front%20view.jpg',
 '모노그램 이클립스 캔버스의 컴팩트 크로스백. 조절 가능한 스트랩과 다중 포켓으로 실용성과 스타일을 모두 갖췄습니다.',
 '2024-03-18 10:00:00+09', '2024-03-18 10:00:00+09'),

-- 19. 여성 토트백
('a3200001-9999-9999-9999-999999999999'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-4444-4444-4444-444444444444'::uuid,
 'Celine 카바 트리옹프 토트백 베이지', 'ON_SALE', 3890000, 10,
 'https://www.celine.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-masterCatalog_CL/default/dw3e3e3e3e/images/large/194373A4T.01BC_1.jpg',
 '셀린느 시그니처 트리옹프 로고의 럭셔리 토트백. 캔버스와 송아지 가죽의 조화로 데일리부터 비즈니스까지 완벽한 백입니다.',
 '2024-03-19 10:00:00+09', '2024-03-19 10:00:00+09'),

-- 20. 여성 숄더백
('a3200002-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1222222-2222-2222-2222-222222222222'::uuid, '패션플러스', 'a5db2222-4444-4444-4444-444444444444'::uuid,
 'Chanel 클래식 플랩백 미디움 블랙 캐비어', 'ON_SALE', 12900000, 3,
 'https://www.chanel.com/images//t_one//w_0.51,h_0.51,c_crop/q_auto:good,f_auto,fl_lossy,dpr_1.1/w_1240/classic-flap-bag-black-lambskin-gold-tone-metal-lambskin-gold-tone-metal-packshot-default-a01112y01864c3906-8819803086878.jpg',
 '샤넬 아이콘 클래식 플랩백. 더블 C 로고와 캐비어 레더, 체인 스트랩이 어우러진 영원한 럭셔리의 상징입니다.',
 '2024-03-20 10:00:00+09', '2024-03-20 10:00:00+09');

-- 상품 이미지 데이터 (상품별 1~2개)
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
-- 에어조던 이미지 (2개)
('a1942001-1111-1111-1111-111111111111'::uuid, 'a3200001-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/3f3e7049-5c99-428c-abcf-a38671c851bf/air-jordan-1-retro-high-og-chicago-lost-and-found-dz5485-612.png', 0, '2024-03-11 10:00:00+09', '2024-03-11 10:00:00+09'),
('a1942001-1111-1111-1111-222222222222'::uuid, 'a3200001-1111-1111-1111-111111111111'::uuid, 'DETAIL', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/8e3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e/air-jordan-1-retro-high-og-chicago-lost-and-found-dz5485-612.png', 1, '2024-03-11 10:00:00+09', '2024-03-11 10:00:00+09'),

-- 이지 부스트 이미지
('a1942001-2222-2222-2222-111111111111'::uuid, 'a3200001-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://assets.adidas.com/images/w_600,f_auto,q_auto/3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e3e_9366/Yeezy_Boost_350_V2_Onyx_Black_HQ4540_01_standard.jpg', 0, '2024-03-12 10:00:00+09', '2024-03-12 10:00:00+09'),

-- 처치스 구두 이미지
('a1942001-3333-3333-3333-111111111111'::uuid, 'a3200001-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://www.church-footwear.com/on/demandware.static/-/Sites-church-master/default/dw3e3e3e3e/images/EEB002/9XV/F0AAB/EEB002_9XV_F0AAB_F_B100.jpg', 0, '2024-03-13 10:00:00+09', '2024-03-13 10:00:00+09'),

-- 닥터마틴 부츠 이미지 (2개)
('a1942001-4444-4444-4444-111111111111'::uuid, 'a3200001-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://i8.amplience.net/i/jpl/dm_11822006_black_smooth_pd_01?$re6$&w=800&h=800', 0, '2024-03-14 10:00:00+09', '2024-03-14 10:00:00+09'),
('a1942001-4444-4444-4444-222222222222'::uuid, 'a3200001-4444-4444-4444-444444444444'::uuid, 'DETAIL', 'https://i8.amplience.net/i/jpl/dm_11822006_black_smooth_pd_02?$re6$&w=800&h=800', 1, '2024-03-14 10:00:00+09', '2024-03-14 10:00:00+09'),

-- 루부탱 펌프스 이미지
('a1942001-5555-5555-5555-111111111111'::uuid, 'a3200001-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://us.christianlouboutin.com/on/demandware.static/-/Sites-masterCatalog_CL/default/dw3e3e3e3e/images/3080520/3080520_BK01_1_1200x1200.jpg', 0, '2024-03-15 10:00:00+09', '2024-03-15 10:00:00+09'),

-- 골든구스 스니커즈 이미지
('a1942001-6666-6666-6666-111111111111'::uuid, 'a3200001-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.goldengoose.com/dw/image/v2/BFSF_PRD/on/demandware.static/-/Sites-gg-master-catalog/default/dw3e3e3e3e/images/GWF00102F001199_85203_1.jpg', 0, '2024-03-16 10:00:00+09', '2024-03-16 10:00:00+09'),

-- 노스페이스 백팩 이미지
('a1942001-7777-7777-7777-111111111111'::uuid, 'a3200001-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://images.thenorthface.com/is/image/TheNorthFace/NF0A52SE_JK3_hero?wid=1000&hei=1000', 0, '2024-03-17 10:00:00+09', '2024-03-17 10:00:00+09'),

-- LV 크로스백 이미지 (2개)
('a1942001-8888-8888-8888-111111111111'::uuid, 'a3200001-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://us.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-christopher-pm-monogram-eclipse-canvas-bags--M45419_PM2_Front%20view.jpg', 0, '2024-03-18 10:00:00+09', '2024-03-18 10:00:00+09'),
('a1942001-8888-8888-8888-222222222222'::uuid, 'a3200001-8888-8888-8888-888888888888'::uuid, 'DETAIL', 'https://us.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-christopher-pm-monogram-eclipse-canvas-bags--M45419_PM2_Side%20view.jpg', 1, '2024-03-18 10:00:00+09', '2024-03-18 10:00:00+09'),

-- 셀린느 토트백 이미지
('a1942001-9999-9999-9999-111111111111'::uuid, 'a3200001-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://www.celine.com/dw/image/v2/BBSF_PRD/on/demandware.static/-/Sites-masterCatalog_CL/default/dw3e3e3e3e/images/large/194373A4T.01BC_1.jpg', 0, '2024-03-19 10:00:00+09', '2024-03-19 10:00:00+09'),

-- 샤넬 플랩백 이미지 (2개)
('a1942002-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3200002-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.chanel.com/images//t_one//w_0.51,h_0.51,c_crop/q_auto:good,f_auto,fl_lossy,dpr_1.1/w_1240/classic-flap-bag-black-lambskin-gold-tone-metal-lambskin-gold-tone-metal-packshot-default-a01112y01864c3906-8819803086878.jpg', 0, '2024-03-20 10:00:00+09', '2024-03-20 10:00:00+09'),
('a1942002-aaaa-aaaa-aaaa-222222222222'::uuid, 'a3200002-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'DETAIL', 'https://www.chanel.com/images//t_one//w_0.51,h_0.51,c_crop/q_auto:good,f_auto,fl_lossy,dpr_1.1/w_1240/classic-flap-bag-black-lambskin-gold-tone-metal-lambskin-gold-tone-metal-packshot-back-a01112y01864c3906-8819803152414.jpg', 1, '2024-03-20 10:00:00+09', '2024-03-20 10:00:00+09');