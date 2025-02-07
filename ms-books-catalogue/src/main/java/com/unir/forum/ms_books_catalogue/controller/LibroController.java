package com.unir.forum.ms_books_catalogue.controller;

import com.unir.forum.ms_books_catalogue.models.libros.Libro;
import com.unir.forum.ms_books_catalogue.service.LibroService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> obtenerTodosLosLibros() {
        return libroService.obtenerTodosLosLibros();
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.crearLibro(libro));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Libro> libroPatch(@PathVariable Long id, @RequestBody Libro libroPatched) {
        Libro libroModificado = libroService.libroPatch(id, libroPatched);
        return libroModificado != null ? ResponseEntity.ok(libroModificado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Libro eliminado"));
    }


    @GetMapping("/buscar")
    public List<Libro> buscarLibros(@RequestParam(required = false) String title,
                                    @RequestParam(required = false) String author,
                                    @RequestParam(required = false) String category,
                                    @RequestParam(required = false) String isbn,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publicationDate,
                                    @RequestParam(required = false) Integer rating,
                                    @RequestParam(required = false) Boolean visibility) {
        return libroService.buscarLibros(title, author, category, isbn, publicationDate, rating, visibility);
    }

    @PostMapping("/validar")
    public boolean validar(@RequestParam String isbn) {
        System.out.println(isbn);
        return !libroService.buscarLibros(null, null, null, isbn, null, null, null).isEmpty();
    }


}
