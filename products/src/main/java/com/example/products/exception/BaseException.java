package com.example.products.exception;

/**
 * Author: husnitdin@gmail.com
 * Date: 2022-12-25
 * Time: 23:37
 */
public class BaseException extends Exception {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
