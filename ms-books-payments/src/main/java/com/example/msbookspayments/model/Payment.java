package com.example.msbookspayments.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    private String category;
    private String isbn;
    private int rating;
    private boolean visibility;
    private int stock;
}
