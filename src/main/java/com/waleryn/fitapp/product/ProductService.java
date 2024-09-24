package com.waleryn.fitapp.product;

import com.waleryn.fitapp.exception.ProductAlreadyExistsException;
import com.waleryn.fitapp.gpt.ChatGptServiceHelper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.waleryn.fitapp.utils.Constants.PRODUCT_RECOMMENDATION;

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

    public ProductDto addNewProduct(Product product) {
        if (productRepository.findByProductNameIgnoreCase(product.getProductName()).isPresent()) {
            throw new ProductAlreadyExistsException("Product already exists.");
        }
        productRepository.save(product);

        return productMapper.toDto(product);
    }

    protected List<ProductDto> addNewProductsList(List<Product> productList) {
        List<String> newProductNames = productList.stream()
                .map(Product::getProductName)
                .toList();

        boolean hasDuplicates = newProductNames.stream()
                .anyMatch(productName -> productRepository.findByProductNameIgnoreCase(productName)
                        .isPresent());

        if (hasDuplicates) {
            throw new ProductAlreadyExistsException("One or more products already exist.");
        }

        productRepository.saveAll(productList);

        return productMapper.toDto(productList);
    }

    protected String getProductRecommendation() {
        return chatGptServiceHelper.getChatGptResponse(PRODUCT_RECOMMENDATION);
    }
}
