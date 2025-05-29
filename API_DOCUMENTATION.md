# 이커머스 프로젝트 API 명세서

## 사용자 인증 (JWT)

| 엔드포인트         | HTTP 메서드 | 요청 예시                                      | 응답 예시                                               | 상태 코드           | 인증/권한                     |
| ----------------- | ---------- | --------------------------------------------- | ------------------------------------------------------ | ------------------ | ------------------------ |
| /api/auth/signup  | POST       | {"username":"user1","password":"pass123","email":"user1@example.com"} | {"id":1,"username":"user1","email":"user1@example.com"} | 201, 400           | 없음                     |
| /api/auth/login   | POST       | {"username":"user1","password":"pass123"}     | {"accessToken":"ey...","refreshToken":"ey..."}         | 200, 401           | 없음                     |
| /api/users/me     | GET        | 없음                                           | {"id":1,"username":"user1","email":"user1@example.com"} | 200, 401           | JWT 인증 필요 (ROLE_USER) |


## 상품 관리

| 엔드포인트             | HTTP 메서드 | 요청 예시                                           | 응답 예시                                               | 상태 코드                 | 인증/권한                |
| --------------------- | ---------- | -------------------------------------------------- | ------------------------------------------------------ | ----------------------- | ----------------------- |
| /api/products         | GET        | 없음                                                | `[{"id":1,"name":"Book","price":10000},{"id":2,"name":"Phone","price":20000}]` | 200                     | 없음                    |
| /api/products/{id}    | GET        | 없음                                                | `{"id":1,"name":"Book","price":10000,"description":"A book","categoryId":1}`   | 200, 404                | 없음                    |
| /api/products         | POST       | `{"name":"Book","description":"A book","price":10000,"categoryId":1}`        | `{"id":3,"name":"Book","description":"A book","price":10000,"categoryId":1}`  | 201, 400, 401, 403      | JWT 인증 필요 (ROLE_ADMIN) |
| /api/products/{id}    | PUT        | `{"name":"New Book","price":12000}`                   | `{"id":1,"name":"New Book","description":"A book","price":12000,"categoryId":1}` | 200, 400, 404, 401, 403 | JWT 인증 필요 (ROLE_ADMIN) |
| /api/products/{id}    | DELETE     | 없음                                                | 없음                                                   | 204, 404, 401, 403      | JWT 인증 필요 (ROLE_ADMIN) |

## 카테고리(구현예정)

| 엔드포인트               | HTTP 메서드 | 요청 예시               | 응답 예시                        | 상태 코드               | 인증/권한                |
| ----------------------- | ---------- | ---------------------- | ------------------------------ | --------------------- | ----------------------- |
| /api/categories         | GET        | 없음                   | `[{"id":1,"name":"Books"},{"id":2,"name":"Electronics"}]` | 200                   | 없음                    |
| /api/categories/{id}    | GET        | 없음                   | `{"id":1,"name":"Books"}`          | 200, 404              | 없음                    |
| /api/categories         | POST       | `{"name":"New Category"}` | `{"id":3,"name":"New Category"}`   | 201, 400, 401, 403    | JWT 인증 필요 (ROLE_ADMIN) |
| /api/categories/{id}    | PUT        | `{"name":"Updated Name"}` | `{"id":1,"name":"Updated Name"}`   | 200, 400, 404, 401, 403 | JWT 인증 필요 (ROLE_ADMIN) |
| /api/categories/{id}    | DELETE     | 없음                   | 없음                             | 204, 404, 401, 403    | JWT 인증 필요 (ROLE_ADMIN) |

## 장바구니

| 엔드포인트       | HTTP 메서드 | 요청 예시                            | 응답 예시                                                              | 상태 코드           | 인증/권한                 |
| --------------- | ---------- | ----------------------------------- | --------------------------------------------------------------------- | ------------------ | ---------------------- |
| /api/cart        | GET        | 없음                                 | `{"items":[{"productId":1,"name":"Book","quantity":2,"price":10000}],"totalPrice":20000}` | 200, 401          | JWT 인증 필요 (ROLE_USER) |
| /api/cart        | POST       | `{"productId":1,"quantity":2}`         | `{"items":[{"productId":1,"name":"Book","quantity":2,"price":10000}],"totalPrice":20000}` | 200, 400, 401     | JWT 인증 필요 (ROLE_USER) |
| /api/cart/{id}   | PUT        | `{"quantity":3}`                       | `{"items":[{"productId":1,"name":"Book","quantity":3,"price":10000}],"totalPrice":30000}` | 200, 400, 404, 401 | JWT 인증 필요 (ROLE_USER) |
| /api/cart/{id}   | DELETE     | 없음                                 | 없음                                                                  | 204, 404, 401     | JWT 인증 필요 (ROLE_USER) |

## 주문

| 엔드포인트     | HTTP 메서드 | 요청 예시                | 응답 예시                                                                     | 상태 코드           | 인증/권한                 |
| ------------- | ---------- | ----------------------- | --------------------------------------------------------------------------- | ------------------ | ---------------------- |
| /api/orders    | GET        | 없음                     | `[{"id":1,"status":"CREATED","totalPrice":20000,"date":"2025-05-28"}]`           | 200, 401          | JWT 인증 필요 (ROLE_USER) |
| /api/orders/{id} | GET      | 없음                     | `{"id":1,"items":[{"productId":1,"name":"Book","quantity":2,"price":10000}],"status":"CREATED","totalPrice":20000,"date":"2025-05-28"}` | 200, 404, 401     | JWT 인증 필요 (ROLE_USER) |
| /api/orders    | POST       | `{"address":"123 Road"}`   | `{"id":2,"status":"CREATED","totalPrice":30000}`                                | 201, 400, 401     | JWT 인증 필요 (ROLE_USER) |
| /api/orders/{id} | PUT      | `{"status":"CANCELLED"}`   | `{"id":1,"status":"CANCELLED","totalPrice":20000,"date":"2025-05-28"}`          | 200, 400, 404, 401 | JWT 인증 필요 (ROLE_USER) |
