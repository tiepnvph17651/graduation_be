package com.example.demo.model.request;

import com.example.demo.entity.ProductDetail;
import lombok.Data;

import java.util.List;

@Data
public class CartItemRequest {
    private Integer id;
//    private ProductDetail productDetail;
    private Integer quantity;
}
