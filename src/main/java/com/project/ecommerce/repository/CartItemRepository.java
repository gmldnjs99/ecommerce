package com.project.ecommerce.repository;

import com.project.ecommerce.entity.CartItem;
import com.project.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
}
