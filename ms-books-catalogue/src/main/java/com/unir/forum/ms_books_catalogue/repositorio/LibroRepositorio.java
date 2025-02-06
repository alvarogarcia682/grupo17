package com.unir.forum.ms_books_catalogue.repositorio;

import com.unir.forum.ms_books_catalogue.models.libros.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {


    @Query("SELECT l FROM Libro l WHERE " +
            "(:title IS NULL OR LOWER(l.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:author IS NULL OR LOWER(l.author) LIKE LOWER(CONCAT('%', :author, '%'))) " +
            "AND (:category IS NULL OR LOWER(l.category) LIKE LOWER(CONCAT('%', :category, '%'))) " +
            "AND (:isbn IS NULL OR LOWER(l.isbn) LIKE LOWER(CONCAT('%', :isbn, '%'))) " +
            "AND (:publicationDate IS NULL OR l.publicationDate = :publicationDate) " +
            "AND (:rating IS NULL OR l.rating = :rating) " +
            "AND (:visibility IS NULL OR l.visibility = :visibility)")
    List<Libro> buscarPorAtributos(@Param("title") String title,
                                   @Param("author") String author,
                                   @Param("category") String category,
                                   @Param("isbn") String isbn,
                                   @Param("publicationDate") LocalDate publicationDate,
                                   @Param("rating") Integer rating,
                                   @Param("visibility") Boolean visibility);

}
