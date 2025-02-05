package com.unir.forum.ms_books_catalogue.service;

import com.unir.forum.ms_books_catalogue.models.libros.Libro;
import com.unir.forum.ms_books_catalogue.repositorio.LibroRepositorio;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
@Service
public class LibroService {

    private final LibroRepositorio libroRepositorio;

    public LibroService(LibroRepositorio libroRepositorio) {
        this.libroRepositorio = libroRepositorio;
    }

    // Obtener todos los libros
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepositorio.findAll();
    }

    // Crear un nuevo libro
    public Libro crearLibro(Libro libro) {
        return libroRepositorio.save(libro);
    }

    // Modificar un libro
    public Libro libroPatch(Long id, Libro libroPatched) {
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
}
