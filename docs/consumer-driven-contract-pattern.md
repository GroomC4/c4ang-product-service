# Consumer-Driven Contract (CDC) 패턴

## 개요

Consumer-Driven Contract는 마이크로서비스 간의 통합을 검증하기 위한 테스트 패턴으로, **Consumer(소비자)가 계약을 주도**하는 방식입니다.

## 패턴 흐름

### 1. Consumer가 Contract 작성
- Consumer가 자신이 **필요로 하는** API/이벤트의 명세(Contract)를 작성
- "나는 이런 요청을 보내면 이런 응답이 필요해"라는 기대치를 정의

### 2. Producer에게 Contract 전달
- Contract를 Producer 팀/서비스에 공유
- 보통 Git repo, Pact Broker, 또는 공유 artifact 저장소를 통해 전달

### 3. Producer가 Contract 검증 및 Stub 생성
- Producer는 자신의 API가 Contract를 충족하는지 **검증 테스트** 실행
- 검증 통과 시 **Stub(Mock Server)** 을 생성하여 배포
- Spring Cloud Contract의 경우 `stub-runner`용 JAR 생성

### 4. Consumer가 Stub으로 통합 테스트
- Consumer는 Producer의 실제 서비스 대신 **Stub**을 사용하여 테스트
- 실제 서비스 없이도 계약이 지켜지는지 검증 가능

## 역할 정리

| 구분 | 역할 |
|------|------|
| **Contract 작성 주체** | Consumer (소비자 주도) |
| **Contract 검증 주체** | Producer |
| **Stub 생성 주체** | Producer |
| **Stub 사용 주체** | Consumer |

## 장점

- **Consumer가 실제로 필요한 것만** 계약에 포함되므로, Producer가 불필요한 호환성을 유지할 필요가 없음
- 서비스 간 독립적인 배포 가능
- 통합 테스트의 신뢰성 향상
- 빠른 피드백 루프

## 도구

- **Pact**: 다양한 언어 지원, Pact Broker를 통한 중앙 집중식 계약 관리
- **Spring Cloud Contract**: Spring 생태계에 최적화, Groovy/YAML DSL 지원
