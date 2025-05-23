package com.project.ecommerce.entity;

public enum OrderStatus {
    ORDERED,        // 주문 완료 (기본값)
    PAID,           // 결제 완료
    SHIPPING,       // 배송 중
    COMPLETED,      // 배송 완료
    CANCELED        // 주문 취소
}
