package com.waleryn.fitapp.product;

import com.waleryn.fitapp.exception.InvalidProductTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

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
    ResponseEntity<ProductDto> addProductToDatabase(@RequestBody Product product) {
        return new ResponseEntity<>(
                productService.addNewProduct(product),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/list")
    ResponseEntity<List<ProductDto>> addProductsListToDatabase(@RequestBody List<Product> productList) {
        return new ResponseEntity<>(
                productService.addNewProductsList(productList),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/recommend")
    ResponseEntity<String> getProductRecommendation() {
        return new ResponseEntity<>(
                productService.getProductRecommendation(),
                HttpStatus.OK
        );
    }
}
