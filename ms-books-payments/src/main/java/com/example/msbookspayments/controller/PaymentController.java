package com.example.msbookspayments.controller;

import com.example.msbookspayments.model.Payment;
import com.example.msbookspayments.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment makePayment(@RequestParam String isbn, @RequestParam String customerName, @RequestParam double amount) {
        return paymentService.registerPayment(isbn, customerName, amount);
    }
}
