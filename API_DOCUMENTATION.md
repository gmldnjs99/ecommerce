# 이커머스 프로젝트 API 명세서

---

## 1. 회원가입 (Register User)

| 항목           | 내용                                                                                                                                                                        |
| ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Endpoint** | `POST /register`                                                                                                                                                          |
| **설명**       | 새로운 사용자 회원가입                                                                                                                                                              |
| **요청 바디**    | `json<br>{<br>  "username": "string",<br>  "password": "string",<br>  "email": "string"<br>}<br>`<br>- `username`: 3\~20자<br>- `password`: 8자 이상<br>- `email`: 유효한 이메일 형식 |
| **응답**       | - **200 OK**<br>  `text<br>회원가입이 완료되었습니다.<br>`<br>- **400 Bad Request**<br>  - 유효성(validation) 실패 시 자동 리턴                                                                 |
| **인증**       | 불필요                                                                                                                                                                       |

---

## 2. 로그인 (Login User)

| 항목           | 내용                                                                                                                                  |
| ------------ | ----------------------------------------------------------------------------------------------------------------------------------- |
| **Endpoint** | `POST /login`                                                                                                                       |
| **설명**       | 회원 로그인 후 JWT 토큰 발급                                                                                                                  |
| **요청 바디**    | `json<br>{<br>  "username": "string",<br>  "password": "string"<br>}<br>`                                                           |
| **응답**       | - **200 OK**<br>  `json<br>"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."`<br>  (JWT 토큰 문자열) <br>- **401 Unauthorized**<br>  - 인증 정보 불일치 |
| **인증**       | 불필요                                                                                                                                 |

---

## 3. 내 정보 조회 (Get My Profile)

| 항목           | 내용                                                                                                                                                                                       |
| ------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Endpoint** | `GET /me`                                                                                                                                                                                |
| **설명**       | 인증된 사용자의 상세 정보 조회                                                                                                                                                                        |
| **요청 헤더**    | `Authorization: Bearer {JWT_TOKEN}`                                                                                                                                                      |
| **응답**       | - **200 OK**<br>  `json<br>{<br>  "id": 1,<br>  "username": "string",<br>  "email": "string",<br>  "roles": ["ROLE_USER"]<br>}<br>`<br>- **401 Unauthorized**<br>  - 토큰 없거나 만료/유효하지 않을 때 |
| **인증**       | JWT Bearer 토큰 필요                                                                                                                                                                         |

---

### DTO 스키마

#### UserRegisterRequest

| 필드         | 타입     | 제약조건                        | 설명      |
| ---------- | ------ | --------------------------- | ------- |
| `username` | String | `@NotBlank`, `@Size(3,20)`  | 사용자 아이디 |
| `password` | String | `@NotBlank`, `@Size(min=8)` | 비밀번호    |
| `email`    | String | `@NotBlank`, `@Email`       | 이메일     |

#### UserLoginRequest

| 필드         | 타입     | 제약조건        | 설명      |
| ---------- | ------ | ----------- | ------- |
| `username` | String | `@NotBlank` | 사용자 아이디 |
| `password` | String | `@NotBlank` | 비밀번호    |

---

### 응답 엔티티 (Entity)

#### User

| 필드         | 타입           | 설명                     |
| ---------- | ------------ | ---------------------- |
| `id`       | Long         | 사용자 고유번호               |
| `username` | String       | 사용자 아이디                |
| `email`    | String       | 이메일                    |
| `roles`    | List<String> | 사용자 권한(예: `ROLE_USER`) |

---

## 1. 전체 상품 조회 (Get All Products)

| 항목           | 내용                                                                                                                                                                                                                                                                                                                                                                                  |
| ------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Endpoint** | `GET /`                                                                                                                                                                                                                                                                                                                                                                             |
| **설명**       | 등록된 모든 상품 목록을 조회                                                                                                                                                                                                                                                                                                                                                                    |
| **요청 파라미터**  | 없음                                                                                                                                                                                                                                                                                                                                                                                  |
| **요청 예시**    | `http<br>GET /api/products HTTP/1.1<br>Host: api.example.com<br>Accept: application/json`                                                                                                                                                                                                                                                                                           |
| **응답**       | - **200 OK**<br>  `json<br>[<br>  {<br>    "id": 1,<br>    "name": "Laptop",<br>    "description": "High-end gaming laptop",<br>    "price": 1500.00,<br>    "stock": 10<br>  },<br>  {<br>    "id": 2,<br>    "name": "Mouse",<br>    "description": "Wireless mouse",<br>    "price": 25.00,<br>    "stock": 50<br>  }<br>]`<br>- **204 No Content**<br>  - 상품이 하나도 없을 경우 (선택 구현) |
| **인증**       | 필요 없음                                                                                                                                                                                                                                                                                                                                                                               |

---

## 2. 단일 상품 조회 (Get Product by ID)

| 항목           | 내용                                                                                                                                                                                                           |
| ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Endpoint** | `GET /{id}`                                                                                                                                                                                                  |
| **설명**       | ID에 해당하는 단일 상품 정보를 조회                                                                                                                                                                                        |
| **경로 변수**    | `id` (Long) — 조회할 상품의 고유번호                                                                                                                                                                                   |
| **요청 예시**    | `http<br>GET /api/products/1 HTTP/1.1<br>Host: api.example.com<br>Accept: application/json`                                                                                                                  |
| **응답**       | - **200 OK**<br>  `json<br>{<br>  "id": 1,<br>  "name": "Laptop",<br>  "description": "High-end gaming laptop",<br>  "price": 1500.00,<br>  "stock": 10<br>}`<br>- **404 Not Found**<br>  - 해당 ID의 상품이 없을 경우 |
| **인증**       | 필요 없음                                                                                                                                                                                                        |

---

### Entity 스키마

#### Product

| 필드            | 타입      | 설명                 |
| ------------- | ------- | ------------------ |
| `id`          | Long    | 상품 고유번호            |
| `name`        | String  | 상품명                |
| `description` | String  | 상품 설명              |
| `price`       | Double  | 상품 가격 (예: 1500.00) |
| `stock`       | Integer | 재고 수량              |

---

> **참고**:
>
> * 응답에는 `price`와 `stock` 등의 필드가 포함되며, 필요 시 추가 필드를 `Product` 엔티티에 정의하여 포함할 수 있습니다.

