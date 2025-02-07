package com.example.msbookspayments.controller;

import com.example.msbookspayments.model.Payment;
import com.example.msbookspayments.model.PaymentRequest;
import com.example.msbookspayments.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment makePayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.registerPayment(paymentRequest.getIsbn(),
                paymentRequest.getCustomerName(), paymentRequest.getAmount());
    }
}
