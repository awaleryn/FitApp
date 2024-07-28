package com.example.fitapp.dto;

import com.example.fitapp.utils.ProductType;
import lombok.Value;

@Value
public class ProductDto {

    String productName;
    String productBrand;
    double calories;
    double protein;
    double fat;
    double carbohydrates;
    ProductType productType;
}
