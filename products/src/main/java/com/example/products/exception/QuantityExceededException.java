package com.example.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Order quantity exceeded with this id")
public class QuantityExceededException extends BaseException {

    static final long serialVersionUID = -3387516993334229948L;

    public QuantityExceededException() {
        this("Could not find entity with given criteria");
    }

    public QuantityExceededException(String message) {
        super(message);
    }
}