package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentId;   // 외부 결제 시스템 ID (mock)

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String method;

    private String status;      // SUCCESS, FAILURE 등

    private LocalDateTime paidAt;
}