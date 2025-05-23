package com.project.ecommerce.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponse {
    private String paymentId;
    private String status;  // e.g. "SUCCESS", "FAILURE"
    private String message;
}