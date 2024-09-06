package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository  extends JpaRepository<CartItem, Integer>{
    List<CartItem> findByProductDetail(ProductDetail productDetail);


    List<CartItem> findByCart(Cart cart);
}
