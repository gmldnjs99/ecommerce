package com.project.ecommerce.controller;

import com.project.ecommerce.dto.UpdateOrderStatusRequest;
import com.project.ecommerce.entity.Order;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.createOrder(user));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.getUserOrders(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderDetail(@AuthenticationPrincipal User user, @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderDetail(user, id));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(orderService.cancelOrder(user, id));
    }

    // 배송 시작
    @PatchMapping("/{id}/ship")
    public ResponseEntity<Order> startShipping(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.startShipping(id));
    }

    // 배송 완료
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Order> completeShipping(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.completeShipping(id));
    }
}
