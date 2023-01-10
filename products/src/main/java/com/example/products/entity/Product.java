package com.example.products.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    //Entity is same as POJO but with id and @Entity annotation

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    @Column(unique=true)
    @NotBlank(message = "ProductRequest name is mandatory")
    @Length(max = 200, min = 1)
    private String productName;

    @Column(unique=true)
    @NotBlank(message = "ProductRequest code is mandatory")
    @Length(max = 250, min = 1)
    private String productCode;

    @NotNull
    private double productPrice;

    @NotNull
    private long productQuantity;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "image_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Photo> images;
}
