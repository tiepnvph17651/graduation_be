package com.example.demo.controller;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.model.request.CreatePaymentRequest;
import com.example.demo.model.request.PaymentRequest;
import com.example.demo.model.response.ResponseData;
import com.example.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/payment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PaymentController extends BaseController {
    private final PaymentService paymentService;

    @PostMapping("/validate")
    public ResponseEntity<ResponseData<Object>> validatePayment(@RequestBody PaymentRequest request) throws BusinessException {
        return ResponseEntity.ok()
                .body(new ResponseData<>().success(paymentService.validate(request, this.getUsername())));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResponseData<Object>> getPayment(@PathVariable String code) throws BusinessException {
        return ResponseEntity.ok()
                .body(new ResponseData<>().success(paymentService.getPayment(code)));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData<Object>> createPayment(@RequestBody CreatePaymentRequest request) throws BusinessException {
        return ResponseEntity.ok()
                .body(new ResponseData<>().success(paymentService.create(request, this.getUsername())));
    }

    @PostMapping("/send")
    public ResponseEntity<ResponseData<Object>> sendPayment(@RequestBody PaymentRequest request) throws BusinessException {
        return ResponseEntity.ok()
                .body(new ResponseData<>().success(paymentService.validate(request, this.getUsername())));
    }

    @GetMapping("/user-info")
    public ResponseEntity<ResponseData<Object>> getUserInfo() throws BusinessException {
        return ResponseEntity.ok()
                .body(new ResponseData<>().success(paymentService.getUserInfo(this.getUsername())));
    }
}
