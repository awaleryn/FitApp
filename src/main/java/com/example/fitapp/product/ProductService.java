package com.example.fitapp.product;

import com.example.fitapp.exception.ProductAlreadyExistsException;
import com.example.fitapp.gpt.ChatGptServiceHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.fitapp.utils.Constants.PRODUCT_RECOMMENDATION;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ChatGptServiceHelper chatGptServiceHelper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ChatGptServiceHelper chatGptServiceHelper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.chatGptServiceHelper = chatGptServiceHelper;
    }

    protected List<ProductDto> getProductList() {
        List<Product> productList = productRepository.findAll();
        return productMapper.toDto(productList);
    }

    protected List<ProductDto> getProductListBasedOnType(ProductType productType) {
        List<Product> productList = productRepository.findAllByProductType(productType);
        return productMapper.toDto(productList);
    }

    protected Product addNewProduct(Product product) {
        if (productRepository.findByProductNameIgnoreCase(product.getProductName()).isPresent()) {
            throw new ProductAlreadyExistsException("Product already exists.");
        }

        return productRepository.save(product);
    }

    protected List<Product> addNewProductsList(List<Product> productList) {
        List<String> newProductNames = productList.stream()
                .map(Product::getProductName)
                .toList();

        boolean hasDuplicates = newProductNames.stream()
                .anyMatch(productName -> productRepository.findByProductNameIgnoreCase(productName)
                        .isPresent());

        if (hasDuplicates) {
            throw new ProductAlreadyExistsException("One or more products already exist.");
        }

        return productRepository.saveAll(productList);
    }

    protected String getProductRecommendation() {
        return chatGptServiceHelper.getChatGptResponse(PRODUCT_RECOMMENDATION);
    }
}
