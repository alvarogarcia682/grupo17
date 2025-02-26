package com.unir.forum.ms_books_catalogue.repositorio;

import com.unir.forum.ms_books_catalogue.models.libros.Libro;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibroRepositorio extends ElasticsearchRepository<Libro, String> {

    List<Libro> findByTitleContaining(String title);
    List<Libro> findByAuthorContaining(String author);
    List<Libro> findByCategory(String category);
    List<Libro> findByIsbn(String isbn);
    List<Libro> findByPublicationDate(LocalDate publicationDate);
    List<Libro> findByRating(int rating);
    List<Libro> findByVisibility(boolean visibility);
}
