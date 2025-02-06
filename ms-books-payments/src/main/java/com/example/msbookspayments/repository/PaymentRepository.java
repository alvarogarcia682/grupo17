package com.example.msbookspayments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.msbookspayments.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
