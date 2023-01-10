package org.micro.orderservice.external.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.micro.orderservice.exception.CustomException;
import org.micro.orderservice.external.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/v1/products")
public interface ProductService {

    default void fallback(Throwable e){
        throw new CustomException("Payment service IS unavailable", "UNAVAILABLE", 500);
    }
    @PutMapping("/reduce-quantity/{id}")
    public ResponseEntity<String> reduceQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity);

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> fetchProductById(
            @PathVariable("id") long productId);
}