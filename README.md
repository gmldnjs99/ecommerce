# E-commerce Backend Project

이 프로젝트는 Spring Boot 기반의 전자상거래 백엔드 API 서버입니다. 회원가입, 로그인, 상품 조회, 장바구니, 주문, 결제 등 일반 사용자 기능과 상품/주문 관리를 위한 관리자 기능을 포함하고 있습니다.

## 📦 기술 스택

* Java 17
* Spring Boot
* Spring Security (JWT 인증)
* JPA (Hibernate)
* MySQL
* Gradle

## 📁 프로젝트 구조 (일부)

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

### 1. MySQL 데이터베이스 생성

```sql
CREATE DATABASE ecommerce CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. application.properties 설정 예시

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### 3. 빌드 및 실행

```bash
./gradlew build
./gradlew bootRun
```

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
