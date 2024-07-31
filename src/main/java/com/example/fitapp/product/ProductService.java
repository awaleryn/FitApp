package com.example.fitapp.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper
    ) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getProductList() {
        List<Product> productList = productRepository.findAll();
        return productMapper.toDto(productList);
    }

    public List<ProductDto> getProductListBasedOnType(ProductType productType) {
        List<Product> productList = productRepository.findAllByProductType(productType);
        return productMapper.toDto(productList);
    }

    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> addNewProductsList(List<Product> productList) {
        return productRepository.saveAll(productList);
    }
}
