# ========================
# Build Stage
# ========================
FROM gradle:8.5-jdk21 AS build

# GitHub Packages 인증을 위한 ARG
ARG GITHUB_ACTOR
ARG GITHUB_TOKEN

WORKDIR /app

# Gradle 캐싱 최적화를 위해 의존성 파일만 먼저 복사
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle gradle
COPY gradlew ./

# 서브모듈 build.gradle.kts 복사
COPY product-api/build.gradle.kts product-api/

# gradle.properties 생성 및 의존성 다운로드 (단일 레이어로 통합하여 인증 안정성 확보)
RUN mkdir -p ~/.gradle && \
    echo "gpr.user=${GITHUB_ACTOR}" >> ~/.gradle/gradle.properties && \
    echo "gpr.key=${GITHUB_TOKEN}" >> ~/.gradle/gradle.properties && \
    ./gradlew dependencies --no-daemon || true

# 소스 코드 복사 (의존성 캐시 레이어와 분리)
COPY product-api/src product-api/src
COPY product-api/sql product-api/sql

# Gradle 빌드 (테스트 제외 - Docker 환경에서는 Testcontainers 사용 불가)
RUN ./gradlew :product-api:build -x test -x contractTest -x integrationTest --no-daemon

# ========================
# Runtime Stage
# ========================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# 빌드 결과물 복사
COPY --from=build /app/product-api/build/libs/*.jar app.jar

# 애플리케이션 실행을 위한 메타데이터
LABEL maintainer="c4ang-product-service"
LABEL description="C4ang Product Service - 상품 관리 마이크로서비스"

# 헬스체크
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:8083/actuator/health || exit 1

# 애플리케이션 실행
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
