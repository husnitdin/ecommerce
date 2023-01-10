package org.micro.orderservice.external.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.micro.orderservice.exception.CustomException;
import org.micro.orderservice.external.requests.PaymentRequest;
import org.micro.orderservice.external.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/v1/payments")
public interface PaymentService {

    @PostMapping("/make-payment")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

    default String fallback(Throwable e){
        throw new CustomException("Payment service IS unavailable", "UNAVAILABLE", 500);
    }


    @GetMapping ("/{id}")
    public ResponseEntity<PaymentResponse> getTransactionDetails(@PathVariable("id") long orderId);
}