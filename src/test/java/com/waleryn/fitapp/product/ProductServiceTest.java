package com.waleryn.fitapp.product;

import com.waleryn.fitapp.exception.ProductAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.waleryn.fitapp.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private final Product product = createProductPepsi();
    private final Product product2 = createProductDrPepper();


    @Test
    public void shouldMapProductToProductDto() {
        productMapper = new ProductMapperImpl();

        ProductDto productDto = productMapper.toDto(product);

        assertNotNull(productDto);
        assertEquals(product.getProductName(), productDto.getProductName());
        assertEquals(product.getProductBrand(), productDto.getProductBrand());
        assertEquals(product.getCalories(), productDto.getCalories());
        assertEquals(product.getProtein(), productDto.getProtein());
        assertEquals(product.getFat(), productDto.getFat());
        assertEquals(product.getCarbohydrates(), productDto.getCarbohydrates());
    }


    @Test
    void shouldAddNewProductWhenProductDoesNotExist() {
        when(productRepository.findByProductNameIgnoreCase(product.getProductName()))
                .thenReturn(Optional.empty());

        when(productRepository.save(any(Product.class)))
                .thenReturn(product);

        ProductDto productDto = new ProductDto(
                "Pepsi",
                "PepsiCo",
                28,
                0,
                0,
                7,
                ProductType.BEVERAGE
        );

        when(productMapper.toDto(product))
                .thenReturn(productDto);

        ProductDto result = productService.addNewProduct(product);

        assertNotNull(result);
        assertEquals(product.getProductName(), result.getProductName());
        assertEquals(product.getProductBrand(), result.getProductBrand());
        assertEquals(product.getCalories(), result.getCalories());
        assertEquals(product.getProtein(), result.getProtein());
        assertEquals(product.getFat(), result.getFat());
        assertEquals(product.getCarbohydrates(), result.getCarbohydrates());

        verify(productRepository, times(ONE_TIME)).save(product);
        verify(productRepository, times(ONE_TIME)).findByProductNameIgnoreCase(product.getProductName());
        verify(productMapper, times(ONE_TIME)).toDto(product);
    }

    @Test
    void shouldThrowExceptionWhenProductAlreadyExists() {

        when(productRepository.findByProductNameIgnoreCase(product.getProductName()))
                .thenReturn(Optional.of(product));

        assertThrows(ProductAlreadyExistsException.class, () -> productService.addNewProduct(product));

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void shouldAddNewProductsList() {

        List<Product> productList = Arrays.asList(product, product2);

        when(productRepository.findByProductNameIgnoreCase(product.getProductName()))
                .thenReturn(Optional.empty());
        when(productRepository.findByProductNameIgnoreCase(product2.getProductName()))
                .thenReturn(Optional.empty());
        when(productRepository.saveAll(productList)).thenReturn(productList);


        List<ProductDto> savedProducts = productService.addNewProductsList(productList);
        List<ProductDto> productDtoList = productMapper.toDto(productList);

        assertEquals(productDtoList.size(), savedProducts.size());
        verify(productRepository, times(ONE_TIME)).findByProductNameIgnoreCase(product.getProductName());
        verify(productRepository, times(ONE_TIME)).findByProductNameIgnoreCase(product2.getProductName());
        verify(productRepository, times(ONE_TIME)).saveAll(productList);
    }
}