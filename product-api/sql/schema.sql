-- Marketplace platform DDL generated from database_design_requirent.md requirements
-- PostgreSQL dialect

CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE EXTENSION IF NOT EXISTS "pg_trgm";

-- Product domain
CREATE TABLE IF NOT EXISTS p_product_category (
                                                  id                UUID PRIMARY KEY,
                                                  name              TEXT NOT NULL,
                                                  parent_category_id UUID,
                                                  depth             INTEGER NOT NULL DEFAULT 0,
                                                  created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at        TIMESTAMPTZ,
    UNIQUE (name, parent_category_id),
    CHECK (depth >= 0)
    );

COMMENT ON TABLE p_product_category IS '상품 카테고리의 계층 구조를 관리하는 테이블.';
COMMENT ON COLUMN p_product_category.id IS '카테고리 노드의 UUID 기본 키.';
COMMENT ON COLUMN p_product_category.name IS '카테고리 표시 이름.';
COMMENT ON COLUMN p_product_category.parent_category_id IS '상위 카테고리 ID(NULL이면 루트).';
COMMENT ON COLUMN p_product_category.depth IS '루트 0부터 시작하는 계층 깊이.';
COMMENT ON COLUMN p_product_category.created_at IS '카테고리 생성 시각.';
COMMENT ON COLUMN p_product_category.updated_at IS '카테고리 최종 수정 시각.';
COMMENT ON COLUMN p_product_category.deleted_at IS '소프트 삭제 시각.';

CREATE TABLE IF NOT EXISTS p_product (
                                         id                UUID PRIMARY KEY,
                                         store_id          UUID NOT NULL,
                                         store_name        TEXT NOT NULL,
                                         category_id       UUID NOT NULL,
                                         product_name      TEXT NOT NULL,
                                         status            TEXT NOT NULL DEFAULT 'ON_SALE' CHECK (status IN ('ON_SALE', 'OFF_SHELF')),
    price             NUMERIC(12, 2) NOT NULL,
    stock_quantity    INTEGER NOT NULL DEFAULT 0,
    thumbnail_url     TEXT,
    description       TEXT,
    hidden_at         TIMESTAMPTZ,
    created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at        TIMESTAMPTZ,
    CHECK (price >= 0),
    CHECK (stock_quantity >= 0)
    );

COMMENT ON TABLE p_product IS '판매 가능한 상품 정보를 저장하는 테이블.';
COMMENT ON COLUMN p_product.id IS '상품의 UUID 기본 키.';
COMMENT ON COLUMN p_product.store_id IS '상품을 판매하는 스토어 ID.';
COMMENT ON COLUMN p_product.store_name IS '스토어 이름 (비정규화, 읽기 최적화용).';
COMMENT ON COLUMN p_product.category_id IS '주요 카테고리 식별자.';
COMMENT ON COLUMN p_product.product_name IS '상품명.';
COMMENT ON COLUMN p_product.status IS '판매 상태(ON_SALE/OFF_SHELF).';
COMMENT ON COLUMN p_product.price IS '현재 판매 가격.';
COMMENT ON COLUMN p_product.stock_quantity IS '잔여 재고 수량.';
COMMENT ON COLUMN p_product.thumbnail_url IS '대표 이미지 URL.';
COMMENT ON COLUMN p_product.description IS '상품 상세 설명.';
COMMENT ON COLUMN p_product.hidden_at IS '상품이 숨김 처리된 시각.';
COMMENT ON COLUMN p_product.created_at IS '상품 등록 시각.';
COMMENT ON COLUMN p_product.updated_at IS '상품 정보 최종 수정 시각.';
COMMENT ON COLUMN p_product.deleted_at IS '소프트 삭제 시각.';

CREATE TABLE IF NOT EXISTS p_product_image (
                                               id                UUID PRIMARY KEY,
                                               product_id        UUID NOT NULL,
                                               image_type        TEXT NOT NULL CHECK (image_type IN ('PRIMARY', 'DETAIL')),
    image_url         TEXT NOT NULL,
    display_order     INTEGER NOT NULL DEFAULT 0,
    created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at        TIMESTAMPTZ,
    UNIQUE (product_id, image_type, display_order)
    );

COMMENT ON TABLE p_product_image IS '상품의 상세/대표 이미지를 관리하는 테이블.';
COMMENT ON COLUMN p_product_image.id IS '상품 이미지의 UUID 기본 키.';
COMMENT ON COLUMN p_product_image.product_id IS '이미지가 연결된 상품 ID.';
COMMENT ON COLUMN p_product_image.image_type IS 'PRIMARY/DETAIL 등 이미지 용도.';
COMMENT ON COLUMN p_product_image.image_url IS '이미지 파일 URL.';
COMMENT ON COLUMN p_product_image.display_order IS '0부터 시작하는 노출 순서.';
COMMENT ON COLUMN p_product_image.created_at IS '이미지 레코드 생성 시각.';
COMMENT ON COLUMN p_product_image.updated_at IS '이미지 레코드 최종 수정 시각.';
COMMENT ON COLUMN p_product_image.deleted_at IS '소프트 삭제 시각.';

CREATE TABLE IF NOT EXISTS p_product_audit (
                                               id                UUID PRIMARY KEY,
                                               product_id        UUID NOT NULL,
                                               actor_user_id     UUID,
                                               event_type        TEXT NOT NULL CHECK (event_type IN ('PRODUCT_REGISTERED', 'PRODUCT_HIDDEN', 'PRODUCT_DELETED', 'PRODUCT_UPDATED')),
    status_snapshot   TEXT CHECK (status_snapshot IN ('ON_SALE', 'OFF_SHELF')),
    hidden_at_snapshot TIMESTAMPTZ,
    change_summary    TEXT,
    recorded_at       TIMESTAMPTZ NOT NULL DEFAULT now(),
    metadata          JSONB
    );

-- Processed Events (이벤트 멱등성 보장)
CREATE TABLE IF NOT EXISTS p_processed_event (
    event_id          TEXT PRIMARY KEY,
    event_type        TEXT NOT NULL,
    processed_at      TIMESTAMPTZ NOT NULL DEFAULT now()
);

COMMENT ON TABLE p_processed_event IS 'Kafka 이벤트 중복 처리 방지를 위한 테이블';
COMMENT ON COLUMN p_processed_event.event_id IS '처리된 이벤트의 고유 ID';
COMMENT ON COLUMN p_processed_event.event_type IS '이벤트 타입 (예: order.created, store.info.updated)';
COMMENT ON COLUMN p_processed_event.processed_at IS '이벤트 처리 시각';

CREATE INDEX IF NOT EXISTS idx_processed_event_type ON p_processed_event(event_type);
CREATE INDEX IF NOT EXISTS idx_processed_event_processed_at ON p_processed_event(processed_at);

-- Stock Reservation Log (Redis 재고 예약 백업)
CREATE TABLE IF NOT EXISTS p_stock_reservation_log (
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id    TEXT NOT NULL UNIQUE,
    order_id          UUID NOT NULL,
    store_id          UUID NOT NULL,
    products          JSONB NOT NULL,
    status            TEXT NOT NULL CHECK (status IN ('RESERVED', 'CONFIRMED', 'RELEASED', 'EXPIRED')),
    reserved_at       TIMESTAMPTZ NOT NULL DEFAULT now(),
    expires_at        TIMESTAMPTZ NOT NULL,
    confirmed_at      TIMESTAMPTZ,
    released_at       TIMESTAMPTZ,
    created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at        TIMESTAMPTZ NOT NULL DEFAULT now()
    );

CREATE INDEX IF NOT EXISTS idx_stock_reservation_order ON p_stock_reservation_log(order_id);
CREATE INDEX IF NOT EXISTS idx_stock_reservation_expires ON p_stock_reservation_log(expires_at) WHERE status = 'RESERVED';
CREATE INDEX IF NOT EXISTS idx_stock_reservation_status ON p_stock_reservation_log(status);

COMMENT ON TABLE p_stock_reservation_log IS 'Redis 재고 예약 내역을 DB에 백업 (모니터링 및 복구용)';
COMMENT ON COLUMN p_stock_reservation_log.reservation_id IS 'Redis에서 사용하는 예약 ID';
COMMENT ON COLUMN p_stock_reservation_log.order_id IS '연결된 주문 ID';
COMMENT ON COLUMN p_stock_reservation_log.store_id IS '스토어 ID';
COMMENT ON COLUMN p_stock_reservation_log.products IS '예약된 상품 목록 [{productId, quantity}]';
COMMENT ON COLUMN p_stock_reservation_log.status IS 'RESERVED(예약), CONFIRMED(확정), RELEASED(해제), EXPIRED(만료)';
COMMENT ON COLUMN p_stock_reservation_log.reserved_at IS '예약 시각';
COMMENT ON COLUMN p_stock_reservation_log.expires_at IS '예약 만료 시각';
COMMENT ON COLUMN p_stock_reservation_log.confirmed_at IS '예약 확정 시각 (결제 완료)';
COMMENT ON COLUMN p_stock_reservation_log.released_at IS '예약 해제 시각 (취소 또는 만료)';


COMMENT ON TABLE p_product_audit IS '상품 등록/숨김/삭제 등 변동 내역을 기록하는 감사 로그.';
COMMENT ON COLUMN p_product_audit.id IS '상품 감사 레코드의 UUID 기본 키.';
COMMENT ON COLUMN p_product_audit.product_id IS '이벤트 대상 상품 ID.';
COMMENT ON COLUMN p_product_audit.actor_user_id IS '변경 수행자 사용자 UUID.';
COMMENT ON COLUMN p_product_audit.event_type IS 'PRODUCT_REGISTERED 등 이벤트 유형.';
COMMENT ON COLUMN p_product_audit.status_snapshot IS '이벤트 시점의 상품 상태 스냅샷.';
COMMENT ON COLUMN p_product_audit.hidden_at_snapshot IS '이벤트 기록 당시 hidden_at 값.';
COMMENT ON COLUMN p_product_audit.change_summary IS '변경 사항 요약 설명.';
COMMENT ON COLUMN p_product_audit.recorded_at IS '감사 이벤트 기록 시각.';
COMMENT ON COLUMN p_product_audit.metadata IS '추가 정보를 담은 JSON 메타데이터.';

CREATE TABLE IF NOT EXISTS p_ai_prompt_audit (
    id                UUID PRIMARY KEY,
    user_id           UUID,
    prompt            TEXT NOT NULL,
    response          TEXT,
    model             TEXT,
    success           BOOLEAN NOT NULL DEFAULT false,
    error_message     TEXT,
    created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    metadata          JSONB
);

COMMENT ON TABLE p_ai_prompt_audit IS 'AI API 요청 질문과 응답을 모두 저장하는 감사 로그.';
COMMENT ON COLUMN p_ai_prompt_audit.id IS 'AI 프롬프트 감사 레코드의 UUID 기본 키.';
COMMENT ON COLUMN p_ai_prompt_audit.user_id IS '요청한 사용자 UUID (nullable).';
COMMENT ON COLUMN p_ai_prompt_audit.prompt IS 'AI에게 전송한 프롬프트 내용.';
COMMENT ON COLUMN p_ai_prompt_audit.response IS 'AI로부터 받은 응답 내용.';
COMMENT ON COLUMN p_ai_prompt_audit.model IS '사용된 AI 모델명 (예: gemini-pro).';
COMMENT ON COLUMN p_ai_prompt_audit.success IS 'API 호출 성공 여부.';
COMMENT ON COLUMN p_ai_prompt_audit.error_message IS '실패 시 에러 메시지.';
COMMENT ON COLUMN p_ai_prompt_audit.created_at IS '프롬프트 요청 시각.';
COMMENT ON COLUMN p_ai_prompt_audit.metadata IS '추가 정보를 담은 JSON 메타데이터.';

CREATE INDEX IF NOT EXISTS idx_p_product_category_parent ON p_product_category (parent_category_id);
CREATE INDEX IF NOT EXISTS idx_p_product_store ON p_product (store_id);
CREATE INDEX IF NOT EXISTS idx_p_product_category ON p_product (category_id);
CREATE INDEX IF NOT EXISTS idx_p_product_status ON p_product (status);
CREATE INDEX IF NOT EXISTS idx_p_product_hidden ON p_product (hidden_at);
CREATE INDEX IF NOT EXISTS idx_p_product_name_trgm ON p_product USING GIN (product_name gin_trgm_ops);
CREATE INDEX IF NOT EXISTS idx_p_product_store_name_trgm ON p_product USING GIN (store_name gin_trgm_ops);

-- =====================================================
-- Perfume Domain (향수 특화 정보)
-- =====================================================

-- 향수 특화 정보 테이블
-- p_product와 1:1 관계 (논리적 FK, 물리적 제약 없음)
CREATE TABLE IF NOT EXISTS p_perfume (
    id                UUID PRIMARY KEY,
    product_id        UUID NOT NULL,
    brand             TEXT NOT NULL,
    name              TEXT NOT NULL,
    concentration     TEXT,
    main_accords      JSONB,
    top_notes         JSONB,
    middle_notes      JSONB,
    base_notes        JSONB,
    notes_score       JSONB,
    season_score      JSONB,
    day_night_score   JSONB,
    gender            TEXT CHECK (gender IN ('Male', 'Female', 'Unisex')),
    sizes             JSONB,
    detail_url        TEXT,
    created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at        TIMESTAMPTZ,
    UNIQUE (brand, name)
);

COMMENT ON TABLE p_perfume IS '향수 특화 정보를 저장하는 테이블. p_product와 1:1 관계.';
COMMENT ON COLUMN p_perfume.id IS '향수 정보의 UUID 기본 키.';
COMMENT ON COLUMN p_perfume.product_id IS '연결된 p_product ID (논리적 FK).';
COMMENT ON COLUMN p_perfume.brand IS '브랜드명.';
COMMENT ON COLUMN p_perfume.name IS '향수명.';
COMMENT ON COLUMN p_perfume.concentration IS '농도 (오 드 퍼퓸, 오 드 뚜왈렛 등).';
COMMENT ON COLUMN p_perfume.main_accords IS '주요 향조 배열 (JSONB).';
COMMENT ON COLUMN p_perfume.top_notes IS '탑노트 배열 (JSONB).';
COMMENT ON COLUMN p_perfume.middle_notes IS '미들노트 배열 (JSONB).';
COMMENT ON COLUMN p_perfume.base_notes IS '베이스노트 배열 (JSONB).';
COMMENT ON COLUMN p_perfume.notes_score IS '노트별 점수 (JSONB).';
COMMENT ON COLUMN p_perfume.season_score IS '계절 점수 (JSONB). 예: {"spring": 24.1, "summer": 3.8, "fall": 95.5, "winter": 14.2}';
COMMENT ON COLUMN p_perfume.day_night_score IS '주야간 점수 (JSONB). 예: {"day": 47.1, "night": 25.9}';
COMMENT ON COLUMN p_perfume.gender IS '성별 타겟 (Male/Female/Unisex).';
COMMENT ON COLUMN p_perfume.sizes IS '용량별 가격 정보 (JSONB). 예: [{"size_ml": 30, "price": 120000}, {"size_ml": 50, "price": 180000}]';
COMMENT ON COLUMN p_perfume.detail_url IS '원본 상세 페이지 URL.';
COMMENT ON COLUMN p_perfume.created_at IS '레코드 생성 시각.';
COMMENT ON COLUMN p_perfume.updated_at IS '레코드 최종 수정 시각.';
COMMENT ON COLUMN p_perfume.deleted_at IS '소프트 삭제 시각.';

CREATE INDEX IF NOT EXISTS idx_p_perfume_product ON p_perfume (product_id);
CREATE INDEX IF NOT EXISTS idx_p_perfume_brand ON p_perfume (brand);
CREATE INDEX IF NOT EXISTS idx_p_perfume_gender ON p_perfume (gender);
CREATE INDEX IF NOT EXISTS idx_p_perfume_brand_trgm ON p_perfume USING GIN (brand gin_trgm_ops);
CREATE INDEX IF NOT EXISTS idx_p_perfume_name_trgm ON p_perfume USING GIN (name gin_trgm_ops);

-- 노트 이미지 참조 테이블
-- 향수 노트(재료)의 이미지 URL을 관리하는 마스터 데이터
CREATE TABLE IF NOT EXISTS p_note_image (
    id                UUID PRIMARY KEY,
    category          TEXT NOT NULL,
    note_name         TEXT NOT NULL,
    image_url         TEXT NOT NULL,
    created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    UNIQUE (category, note_name)
);

COMMENT ON TABLE p_note_image IS '향수 노트(재료) 이미지를 관리하는 참조 테이블.';
COMMENT ON COLUMN p_note_image.id IS '노트 이미지의 UUID 기본 키.';
COMMENT ON COLUMN p_note_image.category IS '노트 카테고리 (예: Citrus Smells, Floral).';
COMMENT ON COLUMN p_note_image.note_name IS '노트 이름 (예: Bergamot, Rose).';
COMMENT ON COLUMN p_note_image.image_url IS '노트 이미지 URL.';
COMMENT ON COLUMN p_note_image.created_at IS '레코드 생성 시각.';
COMMENT ON COLUMN p_note_image.updated_at IS '레코드 최종 수정 시각.';

CREATE INDEX IF NOT EXISTS idx_p_note_image_category ON p_note_image (category);
CREATE INDEX IF NOT EXISTS idx_p_note_image_note_name ON p_note_image (note_name);
CREATE INDEX IF NOT EXISTS idx_p_note_image_note_name_trgm ON p_note_image USING GIN (note_name gin_trgm_ops);