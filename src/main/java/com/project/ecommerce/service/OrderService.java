package com.project.ecommerce.service;

import com.project.ecommerce.entity.*;
import com.project.ecommerce.repository.CartItemRepository;
import com.project.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;

    public Order createOrder(User user) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("장바구니가 비어 있습니다.");
        }

        List<OrderItem> orderItems = cartItems.stream().map(cartItem ->
                OrderItem.builder()
                        .product(cartItem.getProduct())
                        .quantity(cartItem.getQuantity())
                        .price(cartItem.getProduct().getPrice())
                        .build()
        ).collect(Collectors.toList());

        Order order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .items(orderItems)
                .status(OrderStatus.ORDERED)
                .build();

        // OrderItem에 order 세팅
        orderItems.forEach(item -> item.setOrder(order));

        // 저장
        Order savedOrder = orderRepository.save(order);

        // 장바구니 비우기
        cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }

    public List<Order> getUserOrders(User user) {
        return orderRepository.findByUser(user);
    }

    public Order getOrderDetail(User user, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
        if (!order.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("해당 주문에 접근할 수 없습니다.");
        }
        return order;
    }

    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order cancelOrder(User user, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));

        // 본인의 주문만 취소 가능ㅇ
        if (!order.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("해당 주문을 취소할 수 없습니다.");
        }

        // 이미 배송 중 또는 완료된 주문은 취소 불가
        if (order.getStatus() == OrderStatus.SHIPPING || order.getStatus() == OrderStatus.COMPLETED) {
            throw new IllegalArgumentException("배송 중이거나 완료된 주문은 취소할 수 없습니다.");
        }
        order.setStatus(OrderStatus.CANCELED);
        return orderRepository.save(order);
    }
}
