package com.example.msbookspayments.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-books-catalogue")  // Nombre del microservicio en Eureka
public interface BookServiceClient {

    @GetMapping("/books/validate")
    boolean validateBook(@RequestParam String isbn);
}
