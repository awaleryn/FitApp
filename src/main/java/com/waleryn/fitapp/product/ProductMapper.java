package com.waleryn.fitapp.product;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {


    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    List<ProductDto> toDto(List<Product> productList);

    List<Product> toEntity(List<ProductDto> productDtoList);

}
