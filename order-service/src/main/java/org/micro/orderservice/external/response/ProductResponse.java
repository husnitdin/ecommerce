package org.micro.orderservice.external.response;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class ProductResponse {

    private long productId;
    private String productCode;
    private String productName;
    private double productPrice;
    private long productQuantity;
}
