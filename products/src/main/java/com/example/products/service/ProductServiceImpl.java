package com.example.products.service;

import com.example.products.entity.Product;
import com.example.products.exception.ProductAlreadyExistsException;
import com.example.products.exception.ProductNotFoundException;
import com.example.products.exception.QuantityExceededException;
import com.example.products.model.ProductRequest;
import com.example.products.model.ProductResponse;
import com.example.products.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
//@CacheConfig(cacheNames = {"ProductRequest"})
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long saveProduct(ProductRequest productRequest) {

        log.info("Adding product");

        Optional<Product> entity = productRepository.findProductByProductNameOrAndProductCode(
                productRequest.getProductName(), productRequest.getProductCode());

        if (entity.isPresent()) {
            try {
                throw new ProductAlreadyExistsException(
                        "Product with this name: " + productRequest.getProductName() + " already exist");
            } catch (ProductAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
        }

        Product product = Product.builder()
                .productCode(productRequest.getProductCode())
                .productName(productRequest.getProductName())
                .productPrice(productRequest.getProductPrice())
                .productQuantity(productRequest.getProductQuantity()).build();

        productRepository.save(product);
        log.info("Product created");
        return product.getProductId();
    }

    @Override
    public ProductResponse fetchProductById(long productId) {
        log.info("Get the product for the productId: {}", productId);
        Product product = null;
        try {
            product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(
                            "Provided productRequest doesn't exist with this id: " + productId));
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

        ProductResponse productResponse = new ProductResponse();
        copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public List<ProductResponse> fetchProductList() {
        List<Product> productEntities = productRepository.findAll();

        List<ProductResponse> responses =
                productEntities
                        .stream()
                        .map(product -> {
                            ProductResponse productResponse = new ProductResponse();
                            copyProperties(product, productResponse);
                            return productResponse;
                        })
                        .collect(Collectors.toList());
        return responses;
    }

    @Override
    public ProductResponse fetchProductByCode(String productCode) {
        Product product = null;
        try {
            product = productRepository.findProductByProductCodeIgnoreCase(productCode)
                    .orElseThrow(() -> new ProductNotFoundException(
                            "Provided product doesn't exist with this code: " + productCode));
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

        ProductResponse response = new ProductResponse();
        copyProperties(product, response);
        return response;
    }

    @Override
    public ProductResponse fetchProductByName(String productName) {
        Product product = null;
        try {
            product = productRepository.findProductByProductNameIgnoreCase(productName)
                    .orElseThrow(() -> new ProductNotFoundException(
                            "Provided product doesn't exist with this name: " + productName));
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

        ProductResponse response = new ProductResponse();
        copyProperties(product, response);
        return response;
    }

    @Override
    public ProductResponse updateProductById(long productId, ProductRequest productRequest) {

        Product product = null;
        try {
            product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(
                            "Provided productRequest doesn't exist with this id: " + productId));
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (Objects.nonNull(productRequest.getProductCode()) && !"".equalsIgnoreCase(productRequest.getProductCode())) {
            product.setProductCode(productRequest.getProductCode());
        }

        if (Objects.nonNull(productRequest.getProductName()) && !"".equalsIgnoreCase(productRequest.getProductName())) {
            product.setProductName(productRequest.getProductName());
        }

        if (productRequest.getProductPrice() <= 0) {
            product.setProductPrice(productRequest.getProductPrice());
        }

        if (productRequest.getProductQuantity() < 0) {
            product.setProductQuantity(productRequest.getProductQuantity());
        }

        productRepository.save(product);

        ProductResponse response = new ProductResponse();
        copyProperties(product, response);
        return response;
    }

    @Override
    public void deleteProductById(long productId) {
        Product product = null;
        try {
            product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(
                            "Provided product doesn't exist with this id: " + productId));
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
        productRepository.deleteById(productId);
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduced quantity {} with id: {}", productId, quantity);

        Product product = null;
        try {
            product =
                    productRepository.findById(productId)
                            .orElseThrow(() -> new ProductNotFoundException(
                                    "Provided product doesn't exist with this id: " + productId));
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (product.getProductQuantity() < quantity) {
            try {
                throw new QuantityExceededException("Given quantity is more than what is in stock");
            } catch (QuantityExceededException e) {
                throw new RuntimeException(e);
            }
        }

        product.setProductQuantity(product.getProductQuantity() - quantity);

        productRepository.save(product);

        log.info("Product quantity updated to {}", product.getProductQuantity());
    }
}