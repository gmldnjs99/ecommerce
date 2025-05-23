package com.project.ecommerce.controller.admin;

import com.project.ecommerce.dto.UpdateOrderStatusRequest;
import com.project.ecommerce.entity.Order;
import com.project.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;

    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateOrderStatusRequest request
            ) {
        Order updated = orderService.updateOrderStatus(id, request.getStatus());
        return ResponseEntity.ok(updated);
    }
}
