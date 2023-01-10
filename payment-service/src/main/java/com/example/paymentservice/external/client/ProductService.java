package com.example.paymentservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/v1/products")
public interface ProductService {

    @PutMapping("/reduce-quantity/{id}")
    public ResponseEntity<String> reduceQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity);
}