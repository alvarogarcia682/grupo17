package com.example.msbookspayments.service;

import com.example.msbookspayments.client.BookServiceClient;
import com.example.msbookspayments.model.Payment;
import com.example.msbookspayments.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookServiceClient bookServiceClient;

    public PaymentService(PaymentRepository paymentRepository, BookServiceClient bookServiceClient) {
        this.paymentRepository = paymentRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public Payment registerPayment(String isbn, String customerName, double amount) {
        if (!bookServiceClient.validateBook(isbn)) {
            throw new RuntimeException("El libro no es válido o no está disponible.");
        }

        Payment payment = new Payment(null, isbn, customerName, LocalDateTime.now(), amount);
        return paymentRepository.save(payment);
    }
}
