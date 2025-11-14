#!/bin/bash

# 샘플 데이터 Insert 스크립트
# PostgreSQL docker container에 샘플 데이터를 insert합니다.

set -e  # 에러 발생 시 스크립트 중단

# 색상 정의
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# 환경변수 설정
CONTAINER_NAME="ecommerce_postgres_primary"
DB_USER="application"
DB_PASSWORD="application"
DB_NAME="groom"
SQL_DIR="sql/sample/"

echo -e "${YELLOW}========================================${NC}"
echo -e "${YELLOW}샘플 데이터 Insert 시작${NC}"
echo -e "${YELLOW}========================================${NC}"

# Docker container 실행 여부 확인
if ! docker ps | grep -q ${CONTAINER_NAME}; then
    echo -e "${RED}Error: ${CONTAINER_NAME} container가 실행 중이지 않습니다.${NC}"
    echo -e "${YELLOW}먼저 'docker-compose up -d'를 실행하세요.${NC}"
    exit 1
fi

# psql 함수 정의
execute_sql() {
    local file=$1
    local filename=$(basename "$file")

    echo -e "${GREEN}▶ Executing: ${filename}${NC}"

    if docker exec -i ${CONTAINER_NAME} bash -c "PGPASSWORD=${DB_PASSWORD} psql -U ${DB_USER} -d ${DB_NAME}" < "$file"; then
        echo -e "${GREEN}✓ ${filename} 완료${NC}"
    else
        echo -e "${RED}✗ ${filename} 실패${NC}"
        exit 1
    fi
}

# 1. 기본 데이터 먼저 insert
echo -e "\n${YELLOW}[1/6] 기본 데이터 (사용자, 스토어, 카테고리) insert${NC}"
execute_sql "${SQL_DIR}/sample-data-base.sql"

# 2. Store 1 (테크마켓) 상품 데이터
echo -e "\n${YELLOW}[2/6] Store 1 (테크마켓) 상품 데이터 insert${NC}"
execute_sql "${SQL_DIR}/sample-data-products-store1-batch1.sql"
execute_sql "${SQL_DIR}/sample-data-products-store1-batch2.sql"
execute_sql "${SQL_DIR}/sample-data-products-store1-batch3.sql"
execute_sql "${SQL_DIR}/sample-data-products-store1-batch4.sql"

# 3. Store 2 (패션플러스) 상품 데이터
echo -e "\n${YELLOW}[3/6] Store 2 (패션플러스) 상품 데이터 insert${NC}"
execute_sql "${SQL_DIR}/sample-data-products-store2-batch1.sql"
execute_sql "${SQL_DIR}/sample-data-products-store2-batch2.sql"
execute_sql "${SQL_DIR}/sample-data-products-store2-batch3.sql"
execute_sql "${SQL_DIR}/sample-data-products-store2-batch4.sql"

# 4. Store 3 (홈리빙365) 상품 데이터
echo -e "\n${YELLOW}[4/6] Store 3 (홈리빙365) 상품 데이터 insert${NC}"
execute_sql "${SQL_DIR}/sample-data-products-store3-batch1.sql"
execute_sql "${SQL_DIR}/sample-data-products-store3-batch2.sql"
execute_sql "${SQL_DIR}/sample-data-products-store3-batch3.sql"

# 5. Store 4 (뷰티올) 상품 데이터
echo -e "\n${YELLOW}[5/6] Store 4 (뷰티올) 상품 데이터 insert${NC}"
execute_sql "${SQL_DIR}/sample-data-products-store4-batch1.sql"
execute_sql "${SQL_DIR}/sample-data-products-store4-batch2.sql"
execute_sql "${SQL_DIR}/sample-data-products-store4-batch3.sql"
execute_sql "${SQL_DIR}/sample-data-products-store4-batch4.sql"

# 6. Store 5 (스포츠프로) 상품 데이터
echo -e "\n${YELLOW}[6/6] Store 5 (스포츠프로) 상품 데이터 insert${NC}"
execute_sql "${SQL_DIR}/sample-data-products-store5-batch1.sql"
execute_sql "${SQL_DIR}/sample-data-products-store5-batch2.sql"
execute_sql "${SQL_DIR}/sample-data-products-store5-batch3.sql"
execute_sql "${SQL_DIR}/sample-data-products-store5-batch4.sql"

# 데이터 확인
echo -e "\n${YELLOW}========================================${NC}"
echo -e "${YELLOW}데이터 Insert 완료! 결과 확인 중...${NC}"
echo -e "${YELLOW}========================================${NC}"

echo -e "\n${GREEN}▶ 사용자 수:${NC}"
docker exec -i ${CONTAINER_NAME} bash -c "PGPASSWORD=${DB_PASSWORD} psql -U ${DB_USER} -d ${DB_NAME} -t -c 'SELECT COUNT(*) FROM p_user;'"

echo -e "${GREEN}▶ 스토어 수:${NC}"
docker exec -i ${CONTAINER_NAME} bash -c "PGPASSWORD=${DB_PASSWORD} psql -U ${DB_USER} -d ${DB_NAME} -t -c 'SELECT COUNT(*) FROM p_store;'"

echo -e "${GREEN}▶ 상품 수 (총 200개여야 함):${NC}"
docker exec -i ${CONTAINER_NAME} bash -c "PGPASSWORD=${DB_PASSWORD} psql -U ${DB_USER} -d ${DB_NAME} -t -c 'SELECT COUNT(*) FROM p_product;'"

echo -e "${GREEN}▶ 스토어별 상품 수:${NC}"
docker exec -i ${CONTAINER_NAME} bash -c "PGPASSWORD=${DB_PASSWORD} psql -U ${DB_USER} -d ${DB_NAME} -c 'SELECT store_name, COUNT(*) as product_count FROM p_product GROUP BY store_name ORDER BY store_name;'"

echo -e "\n${GREEN}========================================${NC}"
echo -e "${GREEN}✓ 샘플 데이터 Insert 완료!${NC}"
echo -e "${GREEN}========================================${NC}"
