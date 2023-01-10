package com.example.products.controller;

import com.example.products.model.ProductRequest;
import com.example.products.model.ProductResponse;
import com.example.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductV1Controller {

    @Qualifier("productServiceImpl")
    @Autowired
    private ProductService productService;

//    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("")
    public ResponseEntity<Long> saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        long l = productService.saveProduct(productRequest);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> fetchProductById(@PathVariable("id") long productId) {
        ProductResponse productResponse = productService.fetchProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
    @GetMapping(value = "")
    public ResponseEntity<List<ProductResponse>> fetchProductList() {
        List<ProductResponse> requestList = productService.fetchProductList();
        return new ResponseEntity<>(requestList, HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @GetMapping("/code/{code}")
    public ResponseEntity<ProductResponse> fetchProductByCode(@PathVariable("code") String productCode) {
        ProductResponse productResponse = productService.fetchProductByCode(productCode);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponse> fetchProductByName(@PathVariable("name") String productName) {
        ProductResponse productResponse = productService.fetchProductByName(productName);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProductByCode(@PathVariable("id") long productId,
                                                               @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProductById(productId, productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductByCode(@PathVariable("id") long productId) {
        productService.deleteProductById(productId);
        String message = "ProductRequest deleted successfully with id " + productId;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @PutMapping ("/reduce-quantity/{id}")
    public ResponseEntity<String> reduceQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity) {
        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>("Quantity of "+productId+" reduced to "+quantity, HttpStatus.OK);
    }
}