package com.example.products.service;

import com.example.products.model.ProductRequest;
import com.example.products.model.ProductResponse;
import java.util.List;

public interface ProductService {

    long saveProduct(ProductRequest productRequest);

    ProductResponse fetchProductById(long productId);
    List<ProductResponse> fetchProductList();

    ProductResponse fetchProductByCode(String productCode);

    ProductResponse fetchProductByName(String productName);

    ProductResponse updateProductById(long productId, ProductRequest productRequest);

    void deleteProductById(long productId);
    void reduceQuantity(long productId, long quantity);
}
