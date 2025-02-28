package com.unir.forum.ms_books_catalogue.service;

import com.unir.forum.ms_books_catalogue.models.libros.Libro;
import com.unir.forum.ms_books_catalogue.repositorio.LibroRepositorio;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepositorio libroRepositorio;
    private final ElasticsearchOperations elasticsearchOperations;

    public LibroService(LibroRepositorio libroRepositorio, ElasticsearchOperations elasticsearchOperations) {
        this.libroRepositorio = libroRepositorio;
        this.elasticsearchOperations = elasticsearchOperations;
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
        Criteria criteria = new Criteria();

        if (title != null && !title.isEmpty()) {
            criteria = criteria.and("title").matches(title);
        }
        if (author != null && !author.isEmpty()) {
            criteria = criteria.and("author").matches(author);
        }
        if (category != null && !category.isEmpty()) {
            criteria = criteria.and("category").is(category);
        }
        if (isbn != null && !isbn.isEmpty()) {
            criteria = criteria.and("isbn").is(isbn);
        }
        if (publicationDate != null) {
            criteria = criteria.and("publicationDate").is(publicationDate.toString());
        }
        if (rating != null) {
            criteria = criteria.and("rating").is(rating);
        }
        if (visibility != null) {
            criteria = criteria.and("visibility").is(visibility);
        }

        Query query = new CriteriaQuery(criteria);
        SearchHits<Libro> searchHits = elasticsearchOperations.search(query, Libro.class);

        return searchHits.stream().map(hit -> hit.getContent()).collect(Collectors.toList());
    }
}
