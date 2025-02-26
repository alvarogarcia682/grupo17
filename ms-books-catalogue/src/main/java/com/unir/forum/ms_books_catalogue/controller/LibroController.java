package com.unir.forum.ms_books_catalogue.controller;

import com.unir.forum.ms_books_catalogue.models.libros.Libro;
import com.unir.forum.ms_books_catalogue.service.LibroService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    /**
     * Obtiene todos los libros en Elasticsearch
     */
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        return ResponseEntity.ok(libroService.obtenerTodosLosLibros());
    }

    /**
     * Guarda un nuevo libro en Elasticsearch
     */
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.crearLibro(libro));
    }

    /**
     * Modifica parcialmente un libro existente en Elasticsearch
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Libro> libroPatch(@PathVariable String id, @RequestBody Libro libroPatched) {
        Libro libroModificado = libroService.libroPatch(id, libroPatched);
        return libroModificado != null ? ResponseEntity.ok(libroModificado) : ResponseEntity.notFound().build();
    }

    /**
     * Elimina un libro de Elasticsearch por su ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarLibro(@PathVariable String id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.ok(Map.of("message", "Libro eliminado"));
    }

    /**
     * Búsqueda usando múltiples filtros en Elasticsearch
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscarLibros(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publicationDate,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Boolean visibility) {

        List<Libro> libros = libroService.buscarLibros(title, author, category, isbn, publicationDate, rating, visibility);
        return ResponseEntity.ok(libros);
    }
}
