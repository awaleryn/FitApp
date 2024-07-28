package com.example.fitapp.controller;

import com.example.fitapp.dto.ProductDto;
import com.example.fitapp.exception.InvalidProductTypeException;
import com.example.fitapp.service.ProductService;
import com.example.fitapp.utils.Product;
import com.example.fitapp.utils.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    ResponseEntity<List<ProductDto>> getListOfProducts() {
        return new ResponseEntity<>(
                productService.getProductList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/selected")
    ResponseEntity<List<ProductDto>> getListOfProductsBasedOnProductType(@RequestParam String type) {
        try {
            ProductType productType = ProductType.valueOf(type.toUpperCase());
            return new ResponseEntity<>(
                    productService.getProductListBasedOnType(productType),
                    HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            throw new InvalidProductTypeException("Invalid product type: " + type);
        }
    }

    @PostMapping
    ResponseEntity<Product> addProductToDatabase(@RequestBody Product product) {
        return new ResponseEntity<>(
                productService.addNewProduct(product),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/list")
    ResponseEntity<List<Product>> addProductsListToDatabase(@RequestBody List<Product> productList) {
        return new ResponseEntity<>(
                productService.addNewProductsList(productList),
                HttpStatus.CREATED
        );
    }
}
