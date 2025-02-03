package com.unir.forum.ms_books_catalogue.service;

import com.unir.forum.ms_books_catalogue.models.libros.Libro;
import com.unir.forum.ms_books_catalogue.repositorio.LibroRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
