package com.example.demo.controller;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.model.response.ResponseData;
import com.example.demo.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/auth/colors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping("/getAllColors")
    public ResponseEntity<ResponseData<Object>> getAllSize() throws BusinessException {
        return ResponseEntity.ok()
                .body(new ResponseData<>().success(colorService.getAllColors()));
    }
}