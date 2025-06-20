package com.project.ecommerce.service;

import com.project.ecommerce.entity.Product;
import com.project.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    // 관리자용: 상품 수정
    public Product update(Long id, Product updateData) {
        Product product = findById(id);
        product.setName(updateData.getName());
        product.setDescription(updateData.getDescription());
        product.setPrice(updateData.getPrice());
        product.setStock(updateData.getStock());
        return productRepository.save(product);
    }

    // 관리자용: 상품 삭제
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
