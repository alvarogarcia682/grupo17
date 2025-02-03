package com.unir.forum.ms_books_catalogue.repositorio;

import com.unir.forum.ms_books_catalogue.models.libros.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    // Buscar por título (parcial, ignorando mayúsculas/minúsculas)
    List<Libro> findByTitleContainingIgnoreCase(String title);

    // Buscar por autor (parcial, ignorando mayúsculas/minúsculas)
    List<Libro> findByAuthorContainingIgnoreCase(String author);

    // Buscar por categoría exacta
    List<Libro> findByCategory(String category);

    // Filtrar por fecha de publicación dentro de un rango
    List<Libro> findByPublicationDateBetween(LocalDate startDate, LocalDate endDate);

    // Buscar por ISBN
    List<Libro> findByIsbn(String isbn);

    // Filtrar por rating mínimo
    List<Libro> findByRatingGreaterThanEqual(int rating);

    // Buscar por visibilidad
    List<Libro> findByVisibility(boolean visibility);

    // Buscar por stock disponible
    List<Libro> findByStockGreaterThan(int stock);
}