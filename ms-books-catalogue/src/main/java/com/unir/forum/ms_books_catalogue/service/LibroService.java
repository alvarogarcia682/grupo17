package com.unir.forum.ms_books_catalogue.service;

import com.unir.forum.ms_books_catalogue.models.libros.Libro;
import com.unir.forum.ms_books_catalogue.repositorio.LibroRepositorio;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepositorio libroRepositorio;
    private final ElasticsearchRestTemplate elasticsearchTemplate;  // Motor de búsqueda

    public LibroService(LibroRepositorio libroRepositorio, ElasticsearchRestTemplate elasticsearchTemplate) {
        this.libroRepositorio = libroRepositorio;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    public List<Libro> obtenerTodosLosLibros() {
        return (List<Libro>) libroRepositorio.findAll();
    }

    public Libro crearLibro(Libro libro) {
        return libroRepositorio.save(libro);
    }

    public Libro libroPatch(String id, Libro libroPatched) {
        return libroRepositorio.findById(id).map(libro -> {
            if (libroPatched.getTitle() != null) libro.setTitle(libroPatched.getTitle());
            if (libroPatched.getAuthor() != null) libro.setAuthor(libroPatched.getAuthor());
            if (libroPatched.getPublicationDate() != null) libro.setPublicationDate(libroPatched.getPublicationDate());
            if (libroPatched.getCategory() != null) libro.setCategory(libroPatched.getCategory());
            if (libroPatched.getIsbn() != null) libro.setIsbn(libroPatched.getIsbn());
            if (libroPatched.getRating() != 0) libro.setRating(libroPatched.getRating());
            if (libroPatched.getStock() != 0) libro.setStock(libroPatched.getStock());
            libro.setVisibility(libroPatched.isVisibility());

            return libroRepositorio.save(libro);
        }).orElse(null);
    }

    public void eliminarLibro(String id) {
        libroRepositorio.deleteById(id);
    }
    public List<Libro> buscarLibros(String title, String author, String category, String isbn,
                                    LocalDate publicationDate, Integer rating, Boolean visibility) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        if (title != null && !title.isEmpty()) {
            queryBuilder.must(QueryBuilders.matchQuery("title", title));
        }
        if (author != null && !author.isEmpty()) {
            queryBuilder.must(QueryBuilders.matchQuery("author", author));
        }
        if (category != null && !category.isEmpty()) {
            queryBuilder.must(QueryBuilders.termQuery("category.keyword", category));
        }
        if (isbn != null && !isbn.isEmpty()) {
            queryBuilder.must(QueryBuilders.termQuery("isbn.keyword", isbn));
        }
        if (publicationDate != null) {
            queryBuilder.must(QueryBuilders.termQuery("publicationDate", publicationDate.toString()));
        }
        if (rating != null) {
            queryBuilder.must(QueryBuilders.termQuery("rating", rating));
        }
        if (visibility != null) {
            queryBuilder.must(QueryBuilders.termQuery("visibility", visibility));
        }

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Libro> searchHits = elasticsearchTemplate.search(searchQuery, Libro.class);

        return searchHits.stream().map(hit -> hit.getContent()).collect(Collectors.toList());
    }
}
