package com.example.msbookspayments.client;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-books-catalogue")
public interface BookServiceClient {

    @PostMapping("/api/libros/validar")
    boolean validateBook(@RequestParam  String isbn);
}
