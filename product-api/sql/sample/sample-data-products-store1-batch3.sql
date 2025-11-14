-- ========================================
-- 상품 데이터 - 1번째 판매자(테크마켓) 상품 21~30
-- ========================================

-- 상품 21-30 (테크마켓 - 스마트홈 및 음향기기)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 21. 스마트홈 기기
('a3100002-1111-1111-1111-111111111111'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Google Nest Hub 2세대 차콜', 'ON_SALE', 129000, 45,
 'https://lh3.googleusercontent.com/lNS99XVCD_W7AZOhFzY4dxmrJYOPCCsV6VQXa5OhxbVqvN5X0RHpzV_WJGVTEWXxD7SLxo-gLGxJSVP5=rw-e365-nu-w1200',
 '7인치 스마트 디스플레이로 집안의 모든 스마트 기기를 제어. 수면 추적과 제스처 인식으로 더욱 편리한 스마트홈 경험을 제공합니다.',
 '2024-02-09 10:00:00+09', '2024-02-09 10:00:00+09'),

-- 22. 스트리밍 기기
('a3100002-2222-2222-2222-222222222222'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Apple TV 4K 128GB WiFi+이더넷 (3세대)', 'ON_SALE', 219000, 35,
 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/apple-tv-4k-hero-select-202210?wid=940',
 'A15 Bionic 칩으로 구동되는 최고의 스트리밍 경험. 4K HDR10+와 Dolby Vision/Atmos로 극장급 엔터테인먼트를 즐기세요.',
 '2024-02-10 10:00:00+09', '2024-02-10 10:00:00+09'),

-- 23. 로봇청소기
('a3100002-3333-3333-3333-333333333333'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'iRobot Roomba j9+ 자동비움 로봇청소기', 'ON_SALE', 1299000, 12,
 'https://media-www.irobot.com/image/upload/f_auto,q_auto/v1693852844/irobot-v2/products/j9/Gallery/j9_Gallery_Overhead_2x.png',
 'PrecisionVision 내비게이션과 자동 먼지통 비움 기능. 3단계 청소 시스템과 앱 연동으로 완벽한 바닥 청소를 실현합니다.',
 '2024-02-11 10:00:00+09', '2024-02-11 10:00:00+09'),

-- 24. 공기청정기
('a3100002-4444-4444-4444-444444444444'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Dyson Pure Cool TP09 공기청정기 화이트/골드', 'ON_SALE', 899000, 18,
 'https://dyson-h.assetsadobe2.com/is/image/content/dam/dyson/products/purifiers/tp09/global/TP09_White_Gold_Primary_800x800.png',
 'HEPA H13 필터와 활성탄소 필터로 0.1마이크론 입자를 99.95% 제거. 에어 멀티플라이어 기술로 공기 정화와 쿨링을 동시에.',
 '2024-02-12 10:00:00+09', '2024-02-12 10:00:00+09'),

-- 25. 드론
('a3100002-5555-5555-5555-555555555555'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'DJI Mini 4 Pro 플라이 모어 콤보', 'ON_SALE', 1499000, 10,
 'https://dji-official-fe.djicdn.com/cms/uploads/78f8c8e8e8e8e8e8e8e8e8e8e8e8e8e8.png',
 '249g 초경량 드론으로 4K 60fps 촬영 가능. 전방위 장애물 감지와 48분 비행시간으로 전문가급 항공촬영을 지원합니다.',
 '2024-02-13 10:00:00+09', '2024-02-13 10:00:00+09'),

-- 26. 스마트폰 액세서리
('a3100002-6666-6666-6666-666666666666'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-1111-1111-1111-111111111111'::uuid,
 'DJI Osmo Mobile 6 스마트폰 짐벌 슬레이트 그레이', 'ON_SALE', 219000, 40,
 'https://dji-official-fe.djicdn.com/cms/uploads/om6_primary_image.png',
 '3축 안정화와 ActiveTrack 5.0으로 부드러운 영상 촬영. 접이식 디자인과 자동 편집 기능으로 누구나 전문가처럼 촬영 가능합니다.',
 '2024-02-14 10:00:00+09', '2024-02-14 10:00:00+09'),

-- 27. 미니 프로젝터
('a3100002-7777-7777-7777-777777777777'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'XGIMI Halo+ 휴대용 스마트 프로젝터', 'ON_SALE', 1099000, 15,
 'https://www.xgimi.com/cdn/shop/products/Halo_Plus_1.png',
 'Full HD 1080p 해상도와 900 ANSI 루멘 밝기. Android TV 내장과 Harman Kardon 스피커로 어디서나 홈시네마 경험을 제공합니다.',
 '2024-02-15 10:00:00+09', '2024-02-15 10:00:00+09'),

-- 28. VR 헤드셋
('a3100002-8888-8888-8888-888888888888'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Meta Quest 3 512GB VR 헤드셋', 'ON_SALE', 899000, 20,
 'https://scontent-ssn1-1.xx.fbcdn.net/v/t39.8562-6/369858328_1026101298542189_2223321452919928214_n.png',
 '차세대 Snapdragon XR2 Gen 2 칩셋과 4K+ 디스플레이. Mixed Reality와 핸드 트래킹으로 완전히 새로운 가상현실을 경험하세요.',
 '2024-02-16 10:00:00+09', '2024-02-16 10:00:00+09'),

-- 29. 사운드바
('a3100002-9999-9999-9999-999999999999'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-4444-4444-4444-444444444444'::uuid,
 'Sonos Arc 사운드바 블랙', 'ON_SALE', 1249000, 8,
 'https://media.sonos.com/images/znqtjj88/production/01f6a0e8e8e8e8e8e8e8e8e8e8e8e8e8-Arc-Black-Front.png',
 'Dolby Atmos와 11개 고성능 드라이버로 극장급 3D 사운드 구현. Trueplay 튜닝과 음성 제어로 최상의 홈시어터 경험을 제공합니다.',
 '2024-02-17 10:00:00+09', '2024-02-17 10:00:00+09'),

-- 30. 전자책 리더
('a3100003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-3333-3333-3333-333333333333'::uuid,
 'Amazon Kindle Scribe 10.2인치 64GB 프리미엄 펜 포함', 'ON_SALE', 549000, 25,
 'https://m.media-amazon.com/images/I/418RSUoae2L._AC_SY780_.jpg',
 '10.2인치 300ppi 디스플레이와 필기 기능. 수백만 권의 책과 노트 작성을 하나의 기기에서, 눈이 편안한 E-ink 화면으로 즐기세요.',
 '2024-02-18 10:00:00+09', '2024-02-18 10:00:00+09');

-- 상품 이미지 데이터 (상품별 1~3개)
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
-- Google Nest Hub 이미지 (2개)
('a1941002-1111-1111-1111-111111111111'::uuid, 'a3100002-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://lh3.googleusercontent.com/lNS99XVCD_W7AZOhFzY4dxmrJYOPCCsV6VQXa5OhxbVqvN5X0RHpzV_WJGVTEWXxD7SLxo-gLGxJSVP5=rw-e365-nu-w1200', 0, '2024-02-09 10:00:00+09', '2024-02-09 10:00:00+09'),
('a1941002-1111-1111-1111-222222222222'::uuid, 'a3100002-1111-1111-1111-111111111111'::uuid, 'DETAIL', 'https://lh3.googleusercontent.com/Obks5R6BvSJI_D1WBX7BmY47Z5V0lk0J9zlxKfr5JwJTJ1YKBg8vxGqPKYOHfHQ7KRTKDnDjP8HE8H8=rw-e365-nu-w1200', 1, '2024-02-09 10:00:00+09', '2024-02-09 10:00:00+09'),

-- Apple TV 4K 이미지 (1개)
('a1941002-2222-2222-2222-111111111111'::uuid, 'a3100002-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/apple-tv-4k-hero-select-202210?wid=940', 0, '2024-02-10 10:00:00+09', '2024-02-10 10:00:00+09'),

-- iRobot Roomba 이미지 (3개)
('a1941002-3333-3333-3333-111111111111'::uuid, 'a3100002-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://media-www.irobot.com/image/upload/f_auto,q_auto/v1693852844/irobot-v2/products/j9/Gallery/j9_Gallery_Overhead_2x.png', 0, '2024-02-11 10:00:00+09', '2024-02-11 10:00:00+09'),
('a1941002-3333-3333-3333-222222222222'::uuid, 'a3100002-3333-3333-3333-333333333333'::uuid, 'DETAIL', 'https://media-www.irobot.com/image/upload/f_auto,q_auto/v1693852844/irobot-v2/products/j9/Gallery/j9_Gallery_Side_2x.png', 1, '2024-02-11 10:00:00+09', '2024-02-11 10:00:00+09'),
('a1941002-3333-3333-3333-333333333333'::uuid, 'a3100002-3333-3333-3333-333333333333'::uuid, 'DETAIL', 'https://media-www.irobot.com/image/upload/f_auto,q_auto/v1693852844/irobot-v2/products/j9/Gallery/j9_Gallery_Base_2x.png', 2, '2024-02-11 10:00:00+09', '2024-02-11 10:00:00+09'),

-- Dyson 공기청정기 이미지 (2개)
('a1941002-4444-4444-4444-111111111111'::uuid, 'a3100002-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://dyson-h.assetsadobe2.com/is/image/content/dam/dyson/products/purifiers/tp09/global/TP09_White_Gold_Primary_800x800.png', 0, '2024-02-12 10:00:00+09', '2024-02-12 10:00:00+09'),
('a1941002-4444-4444-4444-222222222222'::uuid, 'a3100002-4444-4444-4444-444444444444'::uuid, 'DETAIL', 'https://dyson-h.assetsadobe2.com/is/image/content/dam/dyson/products/purifiers/tp09/global/TP09_White_Gold_Secondary_800x800.png', 1, '2024-02-12 10:00:00+09', '2024-02-12 10:00:00+09'),

-- DJI Mini 4 Pro 이미지 (1개)
('a1941002-5555-5555-5555-111111111111'::uuid, 'a3100002-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://dji-official-fe.djicdn.com/cms/uploads/78f8c8e8e8e8e8e8e8e8e8e8e8e8e8e8.png', 0, '2024-02-13 10:00:00+09', '2024-02-13 10:00:00+09'),

-- DJI Osmo Mobile 이미지 (2개)
('a1941002-6666-6666-6666-111111111111'::uuid, 'a3100002-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://dji-official-fe.djicdn.com/cms/uploads/om6_primary_image.png', 0, '2024-02-14 10:00:00+09', '2024-02-14 10:00:00+09'),
('a1941002-6666-6666-6666-222222222222'::uuid, 'a3100002-6666-6666-6666-666666666666'::uuid, 'DETAIL', 'https://dji-official-fe.djicdn.com/cms/uploads/om6_folded_image.png', 1, '2024-02-14 10:00:00+09', '2024-02-14 10:00:00+09'),

-- XGIMI 프로젝터 이미지 (1개)
('a1941002-7777-7777-7777-111111111111'::uuid, 'a3100002-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://www.xgimi.com/cdn/shop/products/Halo_Plus_1.png', 0, '2024-02-15 10:00:00+09', '2024-02-15 10:00:00+09'),

-- Meta Quest 3 이미지 (2개)
('a1941002-8888-8888-8888-111111111111'::uuid, 'a3100002-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://scontent-ssn1-1.xx.fbcdn.net/v/t39.8562-6/369858328_1026101298542189_2223321452919928214_n.png', 0, '2024-02-16 10:00:00+09', '2024-02-16 10:00:00+09'),
('a1941002-8888-8888-8888-222222222222'::uuid, 'a3100002-8888-8888-8888-888888888888'::uuid, 'DETAIL', 'https://scontent-ssn1-1.xx.fbcdn.net/v/t39.8562-6/375661587_984585202615041_4341932771607088349_n.png', 1, '2024-02-16 10:00:00+09', '2024-02-16 10:00:00+09'),

-- Sonos Arc 이미지 (1개)
('a1941002-9999-9999-9999-111111111111'::uuid, 'a3100002-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://media.sonos.com/images/znqtjj88/production/01f6a0e8e8e8e8e8e8e8e8e8e8e8e8e8-Arc-Black-Front.png', 0, '2024-02-17 10:00:00+09', '2024-02-17 10:00:00+09'),

-- Kindle Scribe 이미지 (3개)
('a1941003-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3100003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://m.media-amazon.com/images/I/418RSUoae2L._AC_SY780_.jpg', 0, '2024-02-18 10:00:00+09', '2024-02-18 10:00:00+09'),
('a1941003-aaaa-aaaa-aaaa-222222222222'::uuid, 'a3100003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'DETAIL', 'https://m.media-amazon.com/images/I/61Dv5h5mVQL._AC_SL1500_.jpg', 1, '2024-02-18 10:00:00+09', '2024-02-18 10:00:00+09'),
('a1941003-aaaa-aaaa-aaaa-333333333333'::uuid, 'a3100003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'DETAIL', 'https://m.media-amazon.com/images/I/71MvL2kCFCL._AC_SL1500_.jpg', 2, '2024-02-18 10:00:00+09', '2024-02-18 10:00:00+09');