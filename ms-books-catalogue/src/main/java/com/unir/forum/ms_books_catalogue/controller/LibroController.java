package com.unir.forum.ms_books_catalogue.controller;


import com.unir.forum.ms_books_catalogue.models.libros.Libro;
import com.unir.forum.ms_books_catalogue.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")

public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // Endpoint para obtener todos los libros
    @GetMapping
    public List<Libro> obtenerTodosLosLibros() {
        return libroService.obtenerTodosLosLibros();
    }

    // Endpoint para crear un nuevo libro
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro libroCreado = libroService.crearLibro(libro);
        return ResponseEntity.ok(libroCreado);
    }
}
