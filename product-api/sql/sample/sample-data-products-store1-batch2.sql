-- ========================================
-- 상품 데이터 - 1번째 판매자(테크마켓) 상품 11~20
-- ========================================

-- 상품 11-20 (테크마켓 - 전자제품 액세서리)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 11. 키보드
('a3100011-1111-1111-1111-111111111111'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Logitech MX Keys S 무선 키보드 그래파이트', 'ON_SALE', 159000, 40,
 'https://resource.logitechg.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/mx-keys-s/gallery/mx-keys-s-keyboard-top-view-graphite-us.png',
 '완벽한 타이핑 경험을 위한 프리미엄 무선 키보드. 백라이트와 USB-C 충전, Flow 기술로 여러 기기를 원활하게 제어할 수 있습니다.',
 '2024-01-30 10:00:00+09', '2024-01-30 10:00:00+09'),

-- 12. 마우스
('a3100012-2222-2222-2222-222222222222'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Logitech MX Master 3S 무선 마우스 페일 그레이', 'ON_SALE', 149000, 45,
 'https://resource.logitechg.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/mx-master-3s/gallery/mx-master-3s-mouse-top-view-pale-gray.png',
 '8K DPI 센서와 무소음 클릭. MagSpeed 휠과 제스처 버튼으로 생산성을 극대화하는 프리미엄 마우스입니다.',
 '2024-01-31 10:00:00+09', '2024-01-31 10:00:00+09'),

-- 13. 모니터
('a3100013-3333-3333-3333-333333333333'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'LG 울트라파인 32UN880 32인치 4K USB-C 모니터', 'ON_SALE', 899000, 15,
 'https://www.lg.com/content/dam/channel/wcms/kr/images/monitors/32un880-b_akr/gallery/32UN880-B_01.jpg',
 '4K UHD 해상도와 USB-C 연결로 MacBook과 완벽 호환. HDR10과 에르고 스탠드로 편안한 작업 환경을 제공합니다.',
 '2024-02-01 10:00:00+09', '2024-02-01 10:00:00+09'),

-- 14. 웹캠
('a3100014-4444-4444-4444-444444444444'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Logitech Brio 4K Pro 웹캠', 'ON_SALE', 299000, 35,
 'https://resource.logitechg.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/logitech/en/products/webcams/brio-4k-pro-webcam/gallery/brio-4k-pro-webcam-gallery-1.png',
 '4K Ultra HD와 HDR로 선명한 화상 회의. Windows Hello 얼굴 인식과 5배 디지털 줌을 지원하는 프로급 웹캠입니다.',
 '2024-02-02 10:00:00+09', '2024-02-02 10:00:00+09'),

-- 15. 외장 SSD
('a3100015-5555-5555-5555-555555555555'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Samsung T9 포터블 SSD 2TB 블랙', 'ON_SALE', 359000, 60,
 'https://images.samsung.com/is/image/samsung/p6pim/sec/mu-pg2t0b/gallery/sec-portable-ssd-t9-mu-pg2t0b-ww-thumb-539024447',
 '최대 2,000MB/s 전송속도의 초고속 외장 SSD. 견고한 메탈 디자인과 3미터 낙하 테스트 통과로 안전한 데이터 보관이 가능합니다.',
 '2024-02-03 10:00:00+09', '2024-02-03 10:00:00+09'),

-- 16. USB 허브
('a3100016-6666-6666-6666-666666666666'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Belkin 7-in-1 USB-C 멀티포트 허브', 'ON_SALE', 119000, 55,
 'https://www.belkin.com/on/demandware.static/-/Sites-belkin-master-catalog/default/dw4f4b3b8e/images/hi-res/7-in-1-usb-c-hub-multiport-adapter/F4U092btSGY-1.jpg',
 '4K HDMI, USB-A 3.0, SD 카드 리더, 100W PD 충전을 지원하는 올인원 허브. MacBook과 완벽 호환되는 프리미엄 액세서리입니다.',
 '2024-02-04 10:00:00+09', '2024-02-04 10:00:00+09'),

-- 17. 충전기
('a3100017-7777-7777-7777-777777777777'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Anker 737 GaNPrime 120W 충전기', 'ON_SALE', 89000, 70,
 'https://cdn.shopify.com/s/files/1/0493/9834/9974/products/A2148_TD01_ff5bf9e3-3d3f-42d5-823d-d0c7bb3ac69b.jpg',
 'GaN 기술로 작고 강력한 120W 급속 충전. 3포트로 노트북과 스마트폰을 동시 충전할 수 있는 만능 충전기입니다.',
 '2024-02-05 10:00:00+09', '2024-02-05 10:00:00+09'),

-- 18. 스피커
('a3100018-8888-8888-8888-888888888888'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-4444-4444-4444-444444444444'::uuid,
 'Marshall Stanmore III 블루투스 스피커 블랙', 'ON_SALE', 549000, 25,
 'https://www.marshallheadphones.com/on/demandware.static/-/Sites-zs-master-catalog/default/dw4e1b3e3a/images/marshall/speakers/stanmore-iii/black/high-res/Stanmore-III-Black-01.png',
 '아이코닉한 마샬 사운드를 담은 홈 스피커. 다이나믹 라우드니스와 공간 음향으로 라이브 공연장의 감동을 재현합니다.',
 '2024-02-06 10:00:00+09', '2024-02-06 10:00:00+09'),

-- 19. 게이밍 기어
('a3100019-9999-9999-9999-999999999999'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Razer DeathAdder V3 Pro 무선 게이밍 마우스 화이트', 'ON_SALE', 219000, 30,
 'https://assets2.razerzone.com/images/pnx.assets/b59e1c9bc07a1e4e5a3c8c0c0c0c0c0c/razer-deathadder-v3-pro-white-gallery-01.jpg',
 '59g 초경량 디자인과 30K DPI 센서. 90시간 배터리와 8K Hz 폴링으로 프로 게이머를 위한 최고의 성능을 제공합니다.',
 '2024-02-07 10:00:00+09', '2024-02-07 10:00:00+09'),

-- 20. 카메라
('a3100020-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'GoPro HERO12 Black 액션캠', 'ON_SALE', 649000, 20,
 'https://gopro.com/content/dam/gopro/camera-pdp/hero12black/01-Hero12-Black-PDP-Hero-D.png',
 '5.3K60 비디오와 27MP 사진. HyperSmooth 6.0 손떨림 보정과 방수 기능으로 모든 모험을 생생하게 기록합니다.',
 '2024-02-08 10:00:00+09', '2024-02-08 10:00:00+09');

-- 상품 이미지 데이터 (상품별 1~3개)
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
-- Logitech MX Keys S 이미지 (2개)
('a1941001-1111-1111-1111-111111111111'::uuid, 'a3100011-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://resource.logitechg.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/mx-keys-s/gallery/mx-keys-s-keyboard-top-view-graphite-us.png', 0, '2024-01-30 10:00:00+09', '2024-01-30 10:00:00+09'),
('a1941001-1111-1111-1111-222222222222'::uuid, 'a3100011-1111-1111-1111-111111111111'::uuid, 'DETAIL', 'https://resource.logitechg.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/mx-keys-s/gallery/mx-keys-s-keyboard-front-view-graphite-us.png', 1, '2024-01-30 10:00:00+09', '2024-01-30 10:00:00+09'),

-- Logitech MX Master 3S 이미지 (3개)
('a1941001-2222-2222-2222-111111111111'::uuid, 'a3100012-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://resource.logitechg.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/mx-master-3s/gallery/mx-master-3s-mouse-top-view-pale-gray.png', 0, '2024-01-31 10:00:00+09', '2024-01-31 10:00:00+09'),
('a1941001-2222-2222-2222-222222222222'::uuid, 'a3100012-2222-2222-2222-222222222222'::uuid, 'DETAIL', 'https://resource.logitechg.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/mx-master-3s/gallery/mx-master-3s-mouse-front-view-pale-gray.png', 1, '2024-01-31 10:00:00+09', '2024-01-31 10:00:00+09'),
('a1941001-2222-2222-2222-333333333333'::uuid, 'a3100012-2222-2222-2222-222222222222'::uuid, 'DETAIL', 'https://resource.logitechg.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/mx-master-3s/gallery/mx-master-3s-mouse-side-view-pale-gray.png', 2, '2024-01-31 10:00:00+09', '2024-01-31 10:00:00+09'),

-- LG 울트라파인 모니터 이미지 (1개)
('a1941001-3333-3333-3333-111111111111'::uuid, 'a3100013-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://www.lg.com/content/dam/channel/wcms/kr/images/monitors/32un880-b_akr/gallery/32UN880-B_01.jpg', 0, '2024-02-01 10:00:00+09', '2024-02-01 10:00:00+09'),

-- Logitech Brio 웹캠 이미지 (2개)
('a1941001-4444-4444-4444-111111111111'::uuid, 'a3100014-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://resource.logitechg.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/logitech/en/products/webcams/brio-4k-pro-webcam/gallery/brio-4k-pro-webcam-gallery-1.png', 0, '2024-02-02 10:00:00+09', '2024-02-02 10:00:00+09'),
('a1941001-4444-4444-4444-222222222222'::uuid, 'a3100014-4444-4444-4444-444444444444'::uuid, 'DETAIL', 'https://resource.logitechg.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/logitech/en/products/webcams/brio-4k-pro-webcam/gallery/brio-4k-pro-webcam-gallery-2.png', 1, '2024-02-02 10:00:00+09', '2024-02-02 10:00:00+09'),

-- Samsung T9 SSD 이미지 (1개)
('a1941001-5555-5555-5555-111111111111'::uuid, 'a3100015-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://images.samsung.com/is/image/samsung/p6pim/sec/mu-pg2t0b/gallery/sec-portable-ssd-t9-mu-pg2t0b-ww-thumb-539024447', 0, '2024-02-03 10:00:00+09', '2024-02-03 10:00:00+09'),

-- Belkin USB 허브 이미지 (1개)
('a1941001-6666-6666-6666-111111111111'::uuid, 'a3100016-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://www.belkin.com/on/demandware.static/-/Sites-belkin-master-catalog/default/dw4f4b3b8e/images/hi-res/7-in-1-usb-c-hub-multiport-adapter/F4U092btSGY-1.jpg', 0, '2024-02-04 10:00:00+09', '2024-02-04 10:00:00+09'),

-- Anker 충전기 이미지 (2개)
('a1941001-7777-7777-7777-111111111111'::uuid, 'a3100017-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://cdn.shopify.com/s/files/1/0493/9834/9974/products/A2148_TD01_ff5bf9e3-3d3f-42d5-823d-d0c7bb3ac69b.jpg', 0, '2024-02-05 10:00:00+09', '2024-02-05 10:00:00+09'),
('a1941001-7777-7777-7777-222222222222'::uuid, 'a3100017-7777-7777-7777-777777777777'::uuid, 'DETAIL', 'https://cdn.shopify.com/s/files/1/0493/9834/9974/products/A2148_TD02.jpg', 1, '2024-02-05 10:00:00+09', '2024-02-05 10:00:00+09'),

-- Marshall 스피커 이미지 (3개)
('a1941001-8888-8888-8888-111111111111'::uuid, 'a3100018-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.marshallheadphones.com/on/demandware.static/-/Sites-zs-master-catalog/default/dw4e1b3e3a/images/marshall/speakers/stanmore-iii/black/high-res/Stanmore-III-Black-01.png', 0, '2024-02-06 10:00:00+09', '2024-02-06 10:00:00+09'),
('a1941001-8888-8888-8888-222222222222'::uuid, 'a3100018-8888-8888-8888-888888888888'::uuid, 'DETAIL', 'https://www.marshallheadphones.com/on/demandware.static/-/Sites-zs-master-catalog/default/dw8b3d8f9a/images/marshall/speakers/stanmore-iii/black/high-res/Stanmore-III-Black-02.png', 1, '2024-02-06 10:00:00+09', '2024-02-06 10:00:00+09'),
('a1941001-8888-8888-8888-333333333333'::uuid, 'a3100018-8888-8888-8888-888888888888'::uuid, 'DETAIL', 'https://www.marshallheadphones.com/on/demandware.static/-/Sites-zs-master-catalog/default/dw5e5e5e5e/images/marshall/speakers/stanmore-iii/black/high-res/Stanmore-III-Black-03.png', 2, '2024-02-06 10:00:00+09', '2024-02-06 10:00:00+09'),

-- Razer 마우스 이미지 (1개)
('a1941001-9999-9999-9999-111111111111'::uuid, 'a3100019-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://assets2.razerzone.com/images/pnx.assets/b59e1c9bc07a1e4e5a3c8c0c0c0c0c0c/razer-deathadder-v3-pro-white-gallery-01.jpg', 0, '2024-02-07 10:00:00+09', '2024-02-07 10:00:00+09'),

-- GoPro 이미지 (2개)
('a1941002-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3100020-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://gopro.com/content/dam/gopro/camera-pdp/hero12black/01-Hero12-Black-PDP-Hero-D.png', 0, '2024-02-08 10:00:00+09', '2024-02-08 10:00:00+09'),
('a1941002-aaaa-aaaa-aaaa-222222222222'::uuid, 'a3100020-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'DETAIL', 'https://gopro.com/content/dam/gopro/camera-pdp/hero12black/02-Hero12-Black-PDP-Gallery-Front.png', 1, '2024-02-08 10:00:00+09', '2024-02-08 10:00:00+09');