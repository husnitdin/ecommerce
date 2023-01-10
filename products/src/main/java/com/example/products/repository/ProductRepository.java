package com.example.products.repository;

import com.example.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // JPA naming documentation
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

    Optional<Product> findProductByProductCodeIgnoreCase(String productCode);

    Optional<Product> findProductByProductNameIgnoreCase(String productName);

//    Optional<Product> findByProductNameIgnoreCaseOrAndProductCodeIgnoreCase(String productName, String productCode);
    Optional<Product> findProductByProductNameOrAndProductCode(String productName, String productCode);

}