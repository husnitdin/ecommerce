package com.example.paymentservice.controller;

import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/payments")
@Log4j2
public class PaymentV1Controller {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/make-payment")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {
        long l = paymentService.makeAPayment(paymentRequest);
        log.info("make a payment with order id: {}", l);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<PaymentResponse> getTransactionDetails(@PathVariable("id") long orderId) {
        PaymentResponse response = paymentService.getTransactionDetails(orderId);
        log.info("retrieving a payment details for order id: {}", response.getTransactionId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}