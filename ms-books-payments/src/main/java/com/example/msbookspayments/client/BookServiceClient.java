package com.example.msbookspayments.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gateway-acl")  // Nombre del microservicio en Eureka o gateway
public interface BookServiceClient {

    @PostMapping("/ms-books-catalogue/api/libros/validar")
    boolean validateBook(@RequestParam(required = false)  String isbn);
}
