# E-commerce Backend Project

이 프로젝트는 Spring Boot 기반의 전자상거래 백엔드 API 서버입니다. 회원가입, 로그인, 상품 조회, 장바구니, 주문, 결제 등 일반 사용자 기능과 상품/주문 관리를 위한 관리자 기능을 포함하고 있습니다.

## 📦 기술 스택

* Java 17
* Spring Boot
* Spring Security (JWT 인증)
* JPA (Hibernate)
* H2 / MySQL (환경에 따라 선택 가능)
* Gradle

## 📁 프로젝트 구조

```
ecommerce/
├── src/
│   └── main/
│       └── java/
│           └── com/project/ecommerce/
│               ├── controller/                # REST API 컨트롤러
│               ├── dto/                      # 요청/응답 DTO 클래스
│               ├── entity/                   # JPA 엔티티
│               ├── repository/               # JPA 리포지토리
│               ├── service/                  # 비즈니스 로직
│               └── config/                   # 보안 및 필터 설정
├── build.gradle
├── settings.gradle
└── README.md
```

## ▶️ 실행 방법

### 1. 빌드 및 실행

```bash
./gradlew build
./gradlew bootRun
```

### 2. 테스트 DB (H2) 접속

`http://localhost:8080/h2-console`

* JDBC URL: `jdbc:h2:mem:testdb`
* ID/PW: `root` / ****

## 🛠 주요 기능

### 사용자 기능

* 회원가입, 로그인 (JWT 인증)
* 상품 목록 조회, 상세 조회
* 장바구니 담기/삭제
* 주문 생성, 내 주문 목록 확인
* 결제 요청

### 관리자 기능 (`/admin`)

* 상품 등록, 수정, 삭제
* 주문 상태 변경

## 📬 API 명세

API 명세는 `API_DOCUMENTATION.md` 파일을 참고하세요.

---

> 이 프로젝트는 학습 및 포트폴리오용으로 제작되었습니다.
