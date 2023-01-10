package com.example.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found")
public class ProductNotFoundException extends BaseException {

    static final long serialVersionUID = -3387516993334229948L;
    public ProductNotFoundException() {
        this("Could not find entity with given criteria");
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
