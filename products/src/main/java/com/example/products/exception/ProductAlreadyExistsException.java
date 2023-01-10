package com.example.products.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Product already exists with this id")
public class ProductAlreadyExistsException extends BaseException {

    static final long serialVersionUID = -3387516993334229948L;
    public ProductAlreadyExistsException() {
        this("Could not find entity with given criteria");
    }
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}