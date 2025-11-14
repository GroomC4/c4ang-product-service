-- ========================================
-- 상품 데이터 - 1번째 판매자(테크마켓) 상품 31~40
-- ========================================

-- 상품 31-40 (테크마켓 - 게이밍 및 PC 부품)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 31. 그래픽카드
('a3100003-1111-1111-1111-111111111111'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'NVIDIA GeForce RTX 4070 Ti SUPER 16GB', 'ON_SALE', 1499000, 8,
 'https://images.nvidia.com/aem-dam/Solutions/geforce/ada-lovelace/rtx-4070-ti-super/geforce-rtx-4070-ti-super-product-gallery-full-screen-3840-1.jpg',
 '차세대 Ada Lovelace 아키텍처와 DLSS 3. 16GB GDDR6X 메모리로 4K 게이밍과 AI 작업을 완벽하게 수행합니다.',
 '2024-02-19 10:00:00+09', '2024-02-19 10:00:00+09'),

-- 32. CPU
('a3100003-2222-2222-2222-222222222222'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'AMD Ryzen 9 7950X3D 16코어 프로세서', 'ON_SALE', 899000, 10,
 'https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505460-amd-ryzen-9-7950x3d-pib-1260x709.jpg',
 '3D V-Cache 기술로 게이밍 성능 극대화. 16코어 32스레드와 5.7GHz 부스트로 최고의 성능을 제공합니다.',
 '2024-02-20 10:00:00+09', '2024-02-20 10:00:00+09'),

-- 33. 메모리
('a3100003-3333-3333-3333-333333333333'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Corsair Dominator Platinum RGB DDR5 32GB (2x16GB) 6000MHz', 'ON_SALE', 459000, 25,
 'https://cwsmgmt.corsair.com/pdp/dominator-platinum-rgb-ddr5/images/dominator-platinum-rgb-ddr5-config-hero.png',
 'DDR5-6000 고속 메모리와 12개 RGB LED. Corsair iCUE로 완벽한 RGB 싱크와 최적화된 성능을 경험하세요.',
 '2024-02-21 10:00:00+09', '2024-02-21 10:00:00+09'),

-- 34. 메인보드
('a3100003-4444-4444-4444-444444444444'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'ASUS ROG Maximus Z790 Hero', 'ON_SALE', 899000, 12,
 'https://dlcdnwebimgs.asus.com/gain/1E68DFF8-B6D7-4F0D-8E2C-C91AB7F27168/w717/h525',
 'Intel Z790 칩셋과 DDR5 지원. 20+1 페이즈 전원부와 WiFi 6E로 극한의 오버클럭과 안정성을 제공합니다.',
 '2024-02-22 10:00:00+09', '2024-02-22 10:00:00+09'),

-- 35. 파워서플라이
('a3100003-5555-5555-5555-555555555555'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Seasonic PRIME TX-1600 Titanium ATX 3.0', 'ON_SALE', 699000, 15,
 'https://seasonic.com/pub/media/catalog/product/cache/b6b6b6b6b6b6b6b6b6b6b6b6b6b6b6b6/p/r/prime-tx-1600_1.png',
 '80 PLUS Titanium 인증과 1600W 출력. ATX 3.0과 PCIe 5.0 지원으로 최신 시스템에 최적화된 파워서플라이입니다.',
 '2024-02-23 10:00:00+09', '2024-02-23 10:00:00+09'),

-- 36. 쿨러
('a3100003-6666-6666-6666-666666666666'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'NZXT Kraken Z73 RGB 360mm AIO 수냉쿨러', 'ON_SALE', 449000, 20,
 'https://nzxt.com/assets/cms/34299/1664575916-kraken-z73-rgb-white-hero.png',
 '2.36인치 LCD 디스플레이와 Aer RGB 2 팬. CAM 소프트웨어로 온도, GIF, 시스템 정보를 커스터마이징할 수 있습니다.',
 '2024-02-24 10:00:00+09', '2024-02-24 10:00:00+09'),

-- 37. 케이스
('a3100003-7777-7777-7777-777777777777'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Lian Li O11 Dynamic EVO 강화유리 미들타워 화이트', 'ON_SALE', 249000, 30,
 'https://lian-li.com/wp-content/uploads/2021/10/o11-dynamic-evo-white-1.png',
 '듀얼 챔버 디자인과 리버시블 레이아웃. 최대 10개 팬 장착과 360mm 라디에이터 3개 지원으로 극한의 쿨링 성능을 제공합니다.',
 '2024-02-25 10:00:00+09', '2024-02-25 10:00:00+09'),

-- 38. 게이밍 의자
('a3100003-8888-8888-8888-888888888888'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Herman Miller X Logitech G Embody 게이밍 의자', 'ON_SALE', 2199000, 5,
 'https://store.hermanmiller.com/content/dam/hmicom/page_assets/products/embody_gaming_chair/mh_prd_ovw_embody_gaming_chair.jpg',
 '인체공학 전문가와 프로게이머가 함께 개발. 쿨링 폼과 픽셀 서포트로 장시간 게임에도 완벽한 자세를 유지합니다.',
 '2024-02-26 10:00:00+09', '2024-02-26 10:00:00+09'),

-- 39. 게이밍 헤드셋
('a3100003-9999-9999-9999-999999999999'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-4444-4444-4444-444444444444'::uuid,
 'SteelSeries Arctis Nova Pro Wireless', 'ON_SALE', 549000, 22,
 'https://media.steelseriescdn.com/thumbs/filer_public/66/dd/66dd5e5e-5e5e-5e5e-5e5e-5e5e5e5e5e5e/arctis_nova_pro_wireless_pdp_buybox_01.png__1920x1080_crop-fit_optimize_subsampling-2.png',
 '액티브 노이즈 캔슬링과 듀얼 배터리 시스템. Hi-Fi 드라이버와 360° Spatial Audio로 게임 속 모든 소리를 정확하게 포착합니다.',
 '2024-02-27 10:00:00+09', '2024-02-27 10:00:00+09'),

-- 40. 스트림 덱
('a3100004-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'Elgato Stream Deck XL 32키 컨트롤러', 'ON_SALE', 349000, 35,
 'https://www.elgato.com/sites/default/files/2021-03/Stream_Deck_XL_Front.png',
 '32개 커스터마이징 LCD 키와 무제한 폴더. OBS, Twitch, YouTube와 연동으로 프로 스트리머를 위한 완벽한 컨트롤을 제공합니다.',
 '2024-02-28 10:00:00+09', '2024-02-28 10:00:00+09');

-- 상품 이미지 데이터 (상품별 1~2개)
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
-- RTX 4070 Ti SUPER 이미지
('a1941003-1111-1111-1111-111111111111'::uuid, 'a3100003-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://images.nvidia.com/aem-dam/Solutions/geforce/ada-lovelace/rtx-4070-ti-super/geforce-rtx-4070-ti-super-product-gallery-full-screen-3840-1.jpg', 0, '2024-02-19 10:00:00+09', '2024-02-19 10:00:00+09'),

-- AMD Ryzen 9 7950X3D 이미지
('a1941003-2222-2222-2222-111111111111'::uuid, 'a3100003-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505460-amd-ryzen-9-7950x3d-pib-1260x709.jpg', 0, '2024-02-20 10:00:00+09', '2024-02-20 10:00:00+09'),

-- Corsair DDR5 이미지 (2개)
('a1941003-3333-3333-3333-111111111111'::uuid, 'a3100003-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://cwsmgmt.corsair.com/pdp/dominator-platinum-rgb-ddr5/images/dominator-platinum-rgb-ddr5-config-hero.png', 0, '2024-02-21 10:00:00+09', '2024-02-21 10:00:00+09'),
('a1941003-3333-3333-3333-222222222222'::uuid, 'a3100003-3333-3333-3333-333333333333'::uuid, 'DETAIL', 'https://cwsmgmt.corsair.com/pdp/dominator-platinum-rgb-ddr5/images/dominator-platinum-rgb-ddr5-config-gallery-2.png', 1, '2024-02-21 10:00:00+09', '2024-02-21 10:00:00+09'),

-- ASUS ROG 메인보드 이미지
('a1941003-4444-4444-4444-111111111111'::uuid, 'a3100003-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://dlcdnwebimgs.asus.com/gain/1E68DFF8-B6D7-4F0D-8E2C-C91AB7F27168/w717/h525', 0, '2024-02-22 10:00:00+09', '2024-02-22 10:00:00+09'),

-- Seasonic 파워 이미지
('a1941003-5555-5555-5555-111111111111'::uuid, 'a3100003-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://seasonic.com/pub/media/catalog/product/cache/b6b6b6b6b6b6b6b6b6b6b6b6b6b6b6b6/p/r/prime-tx-1600_1.png', 0, '2024-02-23 10:00:00+09', '2024-02-23 10:00:00+09'),

-- NZXT 쿨러 이미지 (2개)
('a1941003-6666-6666-6666-111111111111'::uuid, 'a3100003-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://nzxt.com/assets/cms/34299/1664575916-kraken-z73-rgb-white-hero.png', 0, '2024-02-24 10:00:00+09', '2024-02-24 10:00:00+09'),
('a1941003-6666-6666-6666-222222222222'::uuid, 'a3100003-6666-6666-6666-666666666666'::uuid, 'DETAIL', 'https://nzxt.com/assets/cms/34299/1664575916-kraken-z73-rgb-white-pump.png', 1, '2024-02-24 10:00:00+09', '2024-02-24 10:00:00+09'),

-- Lian Li 케이스 이미지
('a1941003-7777-7777-7777-111111111111'::uuid, 'a3100003-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://lian-li.com/wp-content/uploads/2021/10/o11-dynamic-evo-white-1.png', 0, '2024-02-25 10:00:00+09', '2024-02-25 10:00:00+09'),

-- Herman Miller 의자 이미지
('a1941003-8888-8888-8888-111111111111'::uuid, 'a3100003-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://store.hermanmiller.com/content/dam/hmicom/page_assets/products/embody_gaming_chair/mh_prd_ovw_embody_gaming_chair.jpg', 0, '2024-02-26 10:00:00+09', '2024-02-26 10:00:00+09'),

-- SteelSeries 헤드셋 이미지
('a1941003-9999-9999-9999-111111111111'::uuid, 'a3100003-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://media.steelseriescdn.com/thumbs/filer_public/66/dd/66dd5e5e-5e5e-5e5e-5e5e-5e5e5e5e5e5e/arctis_nova_pro_wireless_pdp_buybox_01.png__1920x1080_crop-fit_optimize_subsampling-2.png', 0, '2024-02-27 10:00:00+09', '2024-02-27 10:00:00+09'),

-- Elgato Stream Deck 이미지 (2개)
('a1941004-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3100004-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://www.elgato.com/sites/default/files/2021-03/Stream_Deck_XL_Front.png', 0, '2024-02-28 10:00:00+09', '2024-02-28 10:00:00+09'),
('a1941004-aaaa-aaaa-aaaa-222222222222'::uuid, 'a3100004-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'DETAIL', 'https://www.elgato.com/sites/default/files/2021-03/Stream_Deck_XL_Angle.png', 1, '2024-02-28 10:00:00+09', '2024-02-28 10:00:00+09');