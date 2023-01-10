package org.micro.orderservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.micro.orderservice.entity.Order;
import org.micro.orderservice.external.client.PaymentService;
import org.micro.orderservice.external.client.ProductService;
import org.micro.orderservice.external.response.PaymentResponse;
import org.micro.orderservice.external.response.ProductResponse;
import org.micro.orderservice.model.OrderResponse;
import org.micro.orderservice.model.PaymentMode;
import org.micro.orderservice.repository.OrderRepository;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.Instant;
import java.util.Optional;

@SpringBootTest
public class OrderServiceImplTest {

    @Mock private OrderRepository orderRepository;
    @Mock private ProductService productService;
    @Mock private PaymentService paymentService;
    @InjectMocks OrderService orderService = new OrderServiceImpl();

    /*
    @DisplayName("Get order success scenario")
    @Test
    void test_when_order_success() {
        // mocking
        Order order = getMockOrder();
        Mockito.when(orderRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(order));

        Mockito.when(productService.fetchProductById(order.getProductId()).getBody())
                .thenReturn(getMockProductResponse());

        Mockito.when(paymentService.getTransactionDetails(order.getProductId()).getBody())
                .thenReturn(getMockPaymentResponse());

        // actual
        OrderResponse orderResponse = orderService.getOrderDetails(1);

        // Verification
        Mockito.verify(orderRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(orderService, Mockito.times(1)).getOrderDetails(ArgumentMatchers.anyLong());
        Mockito.verify(paymentService, Mockito.times(1)).getTransactionDetails(ArgumentMatchers.anyLong());

        // Assert
        Assertions.assertNotNull(orderResponse);
        Assertions.assertEquals(order.getId(), orderResponse.getOrderId());
    }
*/
    private PaymentResponse getMockPaymentResponse() {
        return PaymentResponse.builder()
                .transactionId(1)
                .paymentDate(Instant.now())
                .paymentMode(PaymentMode.CASH)
                .paymentTotal(699)
                .paymentStatus("Accepted")
                .orderId(1)
                .build();
    }

    private ProductResponse getMockProductResponse() {
        return ProductResponse.builder()
                .productId(2)
                .productName("iphone")
                .productCode("us-001")
                .productPrice(699)
                .productQuantity(5)
                .build();
    }

    private Order getMockOrder() {
        return Order.builder()
                .orderStatus("PLACED")
                .orderDate(Instant.now())
                .id(1)
                .orderTotalPrice(100)
                .orderProductQuantity(10)
                .productId(2)
                .build();
    }
}