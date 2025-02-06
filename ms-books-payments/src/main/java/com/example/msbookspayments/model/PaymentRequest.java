package com.example.msbookspayments.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
public class PaymentRequest {
    private String isbn;
    private String customerName;
    private double amount;
}