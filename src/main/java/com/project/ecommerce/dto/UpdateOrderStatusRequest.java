package com.project.ecommerce.dto;

import com.project.ecommerce.entity.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    private OrderStatus status;
}
