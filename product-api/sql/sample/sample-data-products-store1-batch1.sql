-- ========================================
-- 상품 데이터 - 1번째 판매자(테크마켓) 상품 1~10
-- ========================================

-- TRUNCATE (최초 1회만 실행)
-- TRUNCATE TABLE p_product_image CASCADE;
-- TRUNCATE TABLE p_product CASCADE;

-- 상품 1-10 (테크마켓 - 전자제품)
INSERT INTO p_product (id, store_id, store_name, category_id, product_name, status, price, stock_quantity, thumbnail_url, description, created_at, updated_at) VALUES
-- 1. 스마트폰
('a3100000-1111-1111-1111-111111111111'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-1111-1111-1111-111111111111'::uuid,
 'iPhone 15 Pro Max 256GB 자연 티타늄', 'ON_SALE', 1790000, 15,
 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch-naturaltitanium?wid=5120',
 '최신 A17 Pro 칩과 티타늄 디자인으로 완성된 프리미엄 스마트폰. ProMotion 디스플레이와 프로급 카메라 시스템을 탑재하여 전문가급 사진과 비디오 촬영이 가능합니다.',
 '2024-01-20 10:00:00+09', '2024-01-20 10:00:00+09'),

-- 2. 스마트폰
('a3100000-2222-2222-2222-222222222222'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-1111-1111-1111-111111111111'::uuid,
 'Samsung Galaxy S24 Ultra 512GB 티타늄 그레이', 'ON_SALE', 1698000, 20,
 'https://images.samsung.com/is/image/samsung/p6pim/ae/2401/gallery/ae-galaxy-s24-s928-sm-s928bzahmea-thumb-539305828',
 'Galaxy AI와 함께하는 새로운 경험. 티타늄 프레임과 코닝 고릴라 아머로 내구성을 강화했으며, 2억화소 카메라와 S펜으로 창의적인 작업이 가능합니다.',
 '2024-01-21 10:00:00+09', '2024-01-21 10:00:00+09'),

-- 3. 노트북
('a3100000-3333-3333-3333-333333333333'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'MacBook Pro 16인치 M3 Max 36GB/1TB 스페이스 블랙', 'ON_SALE', 5249000, 8,
 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/mbp16-spaceblack-select-202310?wid=904',
 'M3 Max 칩으로 구동되는 가장 강력한 MacBook Pro. 최대 22시간의 배터리 사용 시간과 Liquid Retina XDR 디스플레이로 전문가를 위한 완벽한 작업 환경을 제공합니다.',
 '2024-01-22 10:00:00+09', '2024-01-22 10:00:00+09'),

-- 4. 노트북
('a3100000-4444-4444-4444-444444444444'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-2222-2222-2222-222222222222'::uuid,
 'LG 그램 17인치 2024 17Z90S i7/32GB/1TB', 'ON_SALE', 2399000, 12,
 'https://www.lge.co.kr/kr/images/it/md07542077/gallery/D2.jpg',
 '1.35kg의 초경량 17인치 노트북. Intel Core Ultra 7 프로세서와 WQXGA 해상도 디스플레이, 최대 20시간 배터리로 어디서나 강력한 성능을 발휘합니다.',
 '2024-01-23 10:00:00+09', '2024-01-23 10:00:00+09'),

-- 5. 태블릿
('a3100000-5555-5555-5555-555555555555'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-3333-3333-3333-333333333333'::uuid,
 'iPad Pro 12.9인치 M2 WiFi 256GB 스페이스 그레이', 'ON_SALE', 1729000, 18,
 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/ipad-pro-12-select-wifi-spacegray-202210?wid=940',
 'M2 칩과 12.9인치 Liquid Retina XDR 디스플레이를 탑재한 프로급 태블릿. Apple Pencil hover 기능과 ProRes 비디오 촬영으로 창의적인 작업이 가능합니다.',
 '2024-01-24 10:00:00+09', '2024-01-24 10:00:00+09'),

-- 6. 태블릿
('a3100000-6666-6666-6666-666666666666'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-3333-3333-3333-333333333333'::uuid,
 'Galaxy Tab S9 Ultra 5G 512GB 그라파이트', 'ON_SALE', 1599000, 10,
 'https://images.samsung.com/is/image/samsung/p6pim/sec/2307/gallery/sec-galaxy-tab-s9-ultra-5g-x916-sm-x916nzaekoo-thumb-537185135',
 '14.6인치 Dynamic AMOLED 2X 디스플레이와 S펜을 포함한 프리미엄 태블릿. IP68 방수방진과 Snapdragon 8 Gen 2로 어디서나 강력한 성능을 제공합니다.',
 '2024-01-25 10:00:00+09', '2024-01-25 10:00:00+09'),

-- 7. 이어폰
('a3100000-7777-7777-7777-777777777777'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-4444-4444-4444-444444444444'::uuid,
 'AirPods Pro 2세대 USB-C MagSafe 충전 케이스', 'ON_SALE', 359000, 50,
 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/MTJV3?wid=1144',
 'H2 칩으로 구동되는 능동형 노이즈 캔슬링과 적응형 오디오. 개인 맞춤형 공간 음향과 대화 감지 기능으로 완벽한 청취 경험을 제공합니다.',
 '2024-01-26 10:00:00+09', '2024-01-26 10:00:00+09'),

-- 8. 이어폰
('a3100000-8888-8888-8888-888888888888'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-4444-4444-4444-444444444444'::uuid,
 'Sony WH-1000XM5 무선 헤드폰 블랙', 'ON_SALE', 449000, 35,
 'https://www.sony.co.kr/image/d8f4e7a6cf12f0344b5a277fa7c73d5f?fmt=png-alpha&wid=660',
 '업계 최고 수준의 노이즈 캔슬링과 뛰어난 음질. 30시간 배터리와 멀티포인트 연결로 일상과 여행에서 완벽한 동반자가 됩니다.',
 '2024-01-27 10:00:00+09', '2024-01-27 10:00:00+09'),

-- 9. 스마트워치
('a3100000-9999-9999-9999-999999999999'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-5555-5555-5555-555555555555'::uuid,
 'Apple Watch Ultra 2 49mm 티타늄 트레일 루프 M/L', 'ON_SALE', 1149000, 25,
 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/MU7J3ref_VW_34FR+watch-49-titanium-ultra2_VW_34FR+watch-face-49-trail-ultra2_VW_34FR?wid=750',
 '가장 견고하고 강력한 Apple Watch. 3000니트 밝기 디스플레이와 정밀 듀얼 주파수 GPS, 100m 방수로 극한의 모험을 지원합니다.',
 '2024-01-28 10:00:00+09', '2024-01-28 10:00:00+09'),

-- 10. 스마트워치
('a3100001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, '테크마켓', 'a5db1111-5555-5555-5555-555555555555'::uuid,
 'Galaxy Watch 6 Classic 47mm LTE 블랙', 'ON_SALE', 479000, 30,
 'https://images.samsung.com/is/image/samsung/p6pim/sec/2307/gallery/sec-galaxy-watch6-classic-r955-sm-r955nzkakoo-thumb-537095473',
 '클래식한 회전 베젤과 최신 기술의 조화. 체성분 분석과 수면 코칭, 개인 맞춤형 심박수 구간으로 건강한 라이프스타일을 지원합니다.',
 '2024-01-29 10:00:00+09', '2024-01-29 10:00:00+09');

-- 상품 이미지 데이터 (상품별 1~5개)
INSERT INTO p_product_image (id, product_id, image_type, image_url, display_order, created_at, updated_at) VALUES
-- iPhone 15 Pro Max 이미지 (3개)
('a1941000-1111-1111-1111-111111111111'::uuid, 'a3100000-1111-1111-1111-111111111111'::uuid, 'PRIMARY', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch-naturaltitanium?wid=5120', 0, '2024-01-20 10:00:00+09', '2024-01-20 10:00:00+09'),
('a1941000-1111-1111-1111-222222222222'::uuid, 'a3100000-1111-1111-1111-111111111111'::uuid, 'DETAIL', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-15-pro-model-unselect-gallery-2-202309?wid=5120', 1, '2024-01-20 10:00:00+09', '2024-01-20 10:00:00+09'),
('a1941000-1111-1111-1111-333333333333'::uuid, 'a3100000-1111-1111-1111-111111111111'::uuid, 'DETAIL', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-15-pro-model-unselect-gallery-3-202309?wid=5120', 2, '2024-01-20 10:00:00+09', '2024-01-20 10:00:00+09'),

-- Galaxy S24 Ultra 이미지 (4개)
('a1941000-2222-2222-2222-111111111111'::uuid, 'a3100000-2222-2222-2222-222222222222'::uuid, 'PRIMARY', 'https://images.samsung.com/is/image/samsung/p6pim/ae/2401/gallery/ae-galaxy-s24-s928-sm-s928bzahmea-thumb-539305828', 0, '2024-01-21 10:00:00+09', '2024-01-21 10:00:00+09'),
('a1941000-2222-2222-2222-222222222222'::uuid, 'a3100000-2222-2222-2222-222222222222'::uuid, 'DETAIL', 'https://images.samsung.com/is/image/samsung/p6pim/ae/2401/gallery/ae-galaxy-s24-s928-sm-s928bzahmea-539305829', 1, '2024-01-21 10:00:00+09', '2024-01-21 10:00:00+09'),
('a1941000-2222-2222-2222-333333333333'::uuid, 'a3100000-2222-2222-2222-222222222222'::uuid, 'DETAIL', 'https://images.samsung.com/is/image/samsung/p6pim/ae/2401/gallery/ae-galaxy-s24-s928-sm-s928bzahmea-539305830', 2, '2024-01-21 10:00:00+09', '2024-01-21 10:00:00+09'),
('a1941000-2222-2222-2222-444444444444'::uuid, 'a3100000-2222-2222-2222-222222222222'::uuid, 'DETAIL', 'https://images.samsung.com/is/image/samsung/p6pim/ae/2401/gallery/ae-galaxy-s24-s928-sm-s928bzahmea-539305831', 3, '2024-01-21 10:00:00+09', '2024-01-21 10:00:00+09'),

-- MacBook Pro 이미지 (2개)
('a1941000-3333-3333-3333-111111111111'::uuid, 'a3100000-3333-3333-3333-333333333333'::uuid, 'PRIMARY', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/mbp16-spaceblack-select-202310?wid=904', 0, '2024-01-22 10:00:00+09', '2024-01-22 10:00:00+09'),
('a1941000-3333-3333-3333-222222222222'::uuid, 'a3100000-3333-3333-3333-333333333333'::uuid, 'DETAIL', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/mbp16-gallery2-202310?wid=2000', 1, '2024-01-22 10:00:00+09', '2024-01-22 10:00:00+09'),

-- LG 그램 이미지 (5개)
('a1941000-4444-4444-4444-111111111111'::uuid, 'a3100000-4444-4444-4444-444444444444'::uuid, 'PRIMARY', 'https://www.lge.co.kr/kr/images/it/md07542077/gallery/D2.jpg', 0, '2024-01-23 10:00:00+09', '2024-01-23 10:00:00+09'),
('a1941000-4444-4444-4444-222222222222'::uuid, 'a3100000-4444-4444-4444-444444444444'::uuid, 'DETAIL', 'https://www.lge.co.kr/kr/images/it/md07542077/gallery/D3.jpg', 1, '2024-01-23 10:00:00+09', '2024-01-23 10:00:00+09'),
('a1941000-4444-4444-4444-333333333333'::uuid, 'a3100000-4444-4444-4444-444444444444'::uuid, 'DETAIL', 'https://www.lge.co.kr/kr/images/it/md07542077/gallery/D4.jpg', 2, '2024-01-23 10:00:00+09', '2024-01-23 10:00:00+09'),
('a1941000-4444-4444-4444-444444444444'::uuid, 'a3100000-4444-4444-4444-444444444444'::uuid, 'DETAIL', 'https://www.lge.co.kr/kr/images/it/md07542077/gallery/D5.jpg', 3, '2024-01-23 10:00:00+09', '2024-01-23 10:00:00+09'),
('a1941000-4444-4444-4444-555555555555'::uuid, 'a3100000-4444-4444-4444-444444444444'::uuid, 'DETAIL', 'https://www.lge.co.kr/kr/images/it/md07542077/gallery/D6.jpg', 4, '2024-01-23 10:00:00+09', '2024-01-23 10:00:00+09'),

-- iPad Pro 이미지 (1개)
('a1941000-5555-5555-5555-111111111111'::uuid, 'a3100000-5555-5555-5555-555555555555'::uuid, 'PRIMARY', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/ipad-pro-12-select-wifi-spacegray-202210?wid=940', 0, '2024-01-24 10:00:00+09', '2024-01-24 10:00:00+09'),

-- Galaxy Tab S9 Ultra 이미지 (3개)
('a1941000-6666-6666-6666-111111111111'::uuid, 'a3100000-6666-6666-6666-666666666666'::uuid, 'PRIMARY', 'https://images.samsung.com/is/image/samsung/p6pim/sec/2307/gallery/sec-galaxy-tab-s9-ultra-5g-x916-sm-x916nzaekoo-thumb-537185135', 0, '2024-01-25 10:00:00+09', '2024-01-25 10:00:00+09'),
('a1941000-6666-6666-6666-222222222222'::uuid, 'a3100000-6666-6666-6666-666666666666'::uuid, 'DETAIL', 'https://images.samsung.com/is/image/samsung/p6pim/sec/2307/gallery/sec-galaxy-tab-s9-ultra-5g-x916-sm-x916nzaekoo-537185136', 1, '2024-01-25 10:00:00+09', '2024-01-25 10:00:00+09'),
('a1941000-6666-6666-6666-333333333333'::uuid, 'a3100000-6666-6666-6666-666666666666'::uuid, 'DETAIL', 'https://images.samsung.com/is/image/samsung/p6pim/sec/2307/gallery/sec-galaxy-tab-s9-ultra-5g-x916-sm-x916nzaekoo-537185137', 2, '2024-01-25 10:00:00+09', '2024-01-25 10:00:00+09'),

-- AirPods Pro 이미지 (2개)
('a1941000-7777-7777-7777-111111111111'::uuid, 'a3100000-7777-7777-7777-777777777777'::uuid, 'PRIMARY', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/MTJV3?wid=1144', 0, '2024-01-26 10:00:00+09', '2024-01-26 10:00:00+09'),
('a1941000-7777-7777-7777-222222222222'::uuid, 'a3100000-7777-7777-7777-777777777777'::uuid, 'DETAIL', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/MTJV3_AV1?wid=1144', 1, '2024-01-26 10:00:00+09', '2024-01-26 10:00:00+09'),

-- Sony WH-1000XM5 이미지 (4개)
('a1941000-8888-8888-8888-111111111111'::uuid, 'a3100000-8888-8888-8888-888888888888'::uuid, 'PRIMARY', 'https://www.sony.co.kr/image/d8f4e7a6cf12f0344b5a277fa7c73d5f?fmt=png-alpha&wid=660', 0, '2024-01-27 10:00:00+09', '2024-01-27 10:00:00+09'),
('a1941000-8888-8888-8888-222222222222'::uuid, 'a3100000-8888-8888-8888-888888888888'::uuid, 'DETAIL', 'https://www.sony.co.kr/image/0d1cf25de377cec6dc36bc0cd14a4f33?fmt=png-alpha&wid=660', 1, '2024-01-27 10:00:00+09', '2024-01-27 10:00:00+09'),
('a1941000-8888-8888-8888-333333333333'::uuid, 'a3100000-8888-8888-8888-888888888888'::uuid, 'DETAIL', 'https://www.sony.co.kr/image/89f3e03e6f59ab07b079e5eb59e30fb3?fmt=png-alpha&wid=660', 2, '2024-01-27 10:00:00+09', '2024-01-27 10:00:00+09'),
('a1941000-8888-8888-8888-444444444444'::uuid, 'a3100000-8888-8888-8888-888888888888'::uuid, 'DETAIL', 'https://www.sony.co.kr/image/1f8e8f087a0e87e3b00e6ba1e2f965c8?fmt=png-alpha&wid=660', 3, '2024-01-27 10:00:00+09', '2024-01-27 10:00:00+09'),

-- Apple Watch Ultra 2 이미지 (1개)
('a1941000-9999-9999-9999-111111111111'::uuid, 'a3100000-9999-9999-9999-999999999999'::uuid, 'PRIMARY', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/MU7J3ref_VW_34FR+watch-49-titanium-ultra2_VW_34FR+watch-face-49-trail-ultra2_VW_34FR?wid=750', 0, '2024-01-28 10:00:00+09', '2024-01-28 10:00:00+09'),

-- Galaxy Watch 6 Classic 이미지 (2개)
('a1941001-aaaa-aaaa-aaaa-111111111111'::uuid, 'a3100001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'PRIMARY', 'https://images.samsung.com/is/image/samsung/p6pim/sec/2307/gallery/sec-galaxy-watch6-classic-r955-sm-r955nzkakoo-thumb-537095473', 0, '2024-01-29 10:00:00+09', '2024-01-29 10:00:00+09'),
('a1941001-aaaa-aaaa-aaaa-222222222222'::uuid, 'a3100001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'::uuid, 'DETAIL', 'https://images.samsung.com/is/image/samsung/p6pim/sec/2307/gallery/sec-galaxy-watch6-classic-r955-sm-r955nzkakoo-537095474', 1, '2024-01-29 10:00:00+09', '2024-01-29 10:00:00+09');