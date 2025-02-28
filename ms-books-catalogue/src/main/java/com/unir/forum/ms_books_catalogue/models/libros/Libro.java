package com.unir.forum.ms_books_catalogue.models.libros;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Document(indexName = "libros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Libro {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Date)
    private LocalDate publicationDate;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String isbn;

    @Field(type = FieldType.Integer)
    private int rating;

    @Field(type = FieldType.Boolean)
    private boolean visibility;

    @Field(type = FieldType.Integer)
    private int stock;
}
