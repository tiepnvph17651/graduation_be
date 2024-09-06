package com.example.demo.controller;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.model.response.ResponseData;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/auth/products/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    @Autowired
    private ProductService productService;

    @GetMapping("/findTop4New")
    public ResponseEntity<ResponseData<Object>> get() throws BusinessException {
        return ResponseEntity.ok()
                .body(new ResponseData<>().success(productService.getTop4NewestProducts()));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseData<Object>> getDetail(@PathVariable("id") Integer id) throws BusinessException {
        return ResponseEntity.ok()
                .body(new ResponseData<>().success(productService.getProduct(id)));
    }
}
