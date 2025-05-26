# 📚 E-commerce API 명세서

## ✅ 인증 (Auth)

### POST /api/users/register

* 회원가입
* 요청

```json
{
  "email": "user@example.com",
  "password": "1234",
  "name": "홍길동"
}
```

### POST /api/users/login

* 로그인 (JWT 반환)
* 요청

```json
{
  "email": "user@example.com",
  "password": "1234"
}
```

* 응답

```json
{
  "token": "eyJhbGci..."
}
```

---

## 🛒 상품 (Product)

### GET /api/products

* 상품 목록 조회

### GET /api/products/{id}

* 상품 상세 조회

---

## 🧺 장바구니 (Cart)

### POST /api/cart

* 장바구니에 상품 추가

```json
{
  "productId": 1,
  "quantity": 2
}
```

### DELETE /api/cart/{id}

* 장바구니 항목 삭제

### GET /api/cart

* 내 장바구니 조회

---

## 📦 주문 (Order)

### POST /api/orders

* 주문 생성

```json
{
  "cartItems": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 3,
      "quantity": 1
    }
  ]
}
```

### GET /api/orders

* 내 주문 목록 조회

### GET /api/orders/{id}

* 주문 상세 조회

---

## 💳 결제 (Payment)

### POST /api/payments

* 결제 요청

```json
{
  "orderId": 1,
  "paymentMethod": "CARD"
}
```

* 응답

```json
{
  "status": "SUCCESS",
  "transactionId": "abc123xyz"
}
```

---

## 🛠 관리자(Admin)

### 상품 관리

* `POST /api/admin/products` 상품 등록

```json
{
  "name": "키보드",
  "price": 45000,
  "description": "기계식 키보드",
  "stock": 100
}
```

* `PUT /api/admin/products/{id}` 상품 수정
* `DELETE /api/admin/products/{id}` 상품 삭제

### 주문 관리

* `PUT /api/admin/orders/{id}` 주문 상태 변경

```json
{
  "status": "SHIPPED"
}
```

---

> 🔐 인증이 필요한 API는 `Authorization: Bearer <JWT>` 헤더를 포함해야 합니다.
> ⚙️ 관리자 API는 관리자 권한을 가진 사용자만 접근할 수 있습니다.
