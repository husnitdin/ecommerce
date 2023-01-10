package com.example.paymentservice.service;

import com.example.paymentservice.entity.TransactionDetails;
import com.example.paymentservice.exception.CustomException;
import com.example.paymentservice.model.PaymentMode;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public long makeAPayment(PaymentRequest paymentRequest) {
        log.info("Payment details: {}", paymentRequest);

        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .paymentTransactionNumber(paymentRequest.getPaymentTransactionNumber())
                .paymentTotal(paymentRequest.getPaymentTotal())
                .build();

        paymentRepository.save(transactionDetails);

        log.info("Payment completed with tranasction id: {}", transactionDetails.getId());

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getTransactionDetails(long orderId) {

        TransactionDetails transaction
                = paymentRepository.findByOrderId(orderId);

       return PaymentResponse.builder()
                .transactionId(transaction.getId())
                .paymentMode(PaymentMode.valueOf( transaction.getPaymentMode()))
                .paymentDate(transaction.getPaymentDate())
                .orderId(transaction.getOrderId())
                .paymentStatus(transaction.getPaymentStatus())
                .paymentTotal(transaction.getPaymentTotal())
                .build();
    }
}