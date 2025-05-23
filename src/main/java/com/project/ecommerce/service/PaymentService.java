package com.project.ecommerce.service;

import com.project.ecommerce.dto.payment.PaymentRequest;
import com.project.ecommerce.dto.payment.PaymentResponse;
import com.project.ecommerce.entity.Order;
import com.project.ecommerce.entity.OrderStatus;
import com.project.ecommerce.entity.Payment;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (order.getStatus() != OrderStatus.ORDERED) {
            return new PaymentResponse(null, "FAILURE", "주문이 이미 결제되었거나, 취소되었습니다.");
        }

        // Mock 결제 처리 (실제 결제 API 호출 대체)
        boolean paymentSuccess = mockPaymentProcessing(request);

        String paymentId = UUID.randomUUID().toString();

        Payment payment = Payment.builder()
                .paymentId(paymentId)
                .order(order)
                .method(request.getPaymentMethod())
                .status(paymentSuccess ? "SUCCESS" : "FAILURE")
                .paidAt(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        if (paymentSuccess) {
            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
            return new PaymentResponse(paymentId, "SUCCESS", "결제가 완료되었습니다.");
        } else {
            return new PaymentResponse(paymentId, "FAILURE", "결제가 실패했습니다.");
        }
    }

    private boolean mockPaymentProcessing(PaymentRequest request) {
        // 단순히 카드번호가 16자리면 성공 처리 (mock)
        if ("CARD".equalsIgnoreCase(request.getPaymentMethod()) && request.getCardNumber() != null) {
            return request.getCardNumber().length() == 16;
        }
        // 기타 결제수단 무조건 성공 처리
        return true;
    }
}