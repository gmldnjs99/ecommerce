package com.project.ecommerce.dto.payment;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long orderId;
    private String paymentMethod;  // e.g. "CARD", "BANK_TRANSFER"
    private String cardNumber;      // 카드 결제시만 (간단히)
}