package com.unir.forum.ms_books_catalogue.models.libros;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "libros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Libro {

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
