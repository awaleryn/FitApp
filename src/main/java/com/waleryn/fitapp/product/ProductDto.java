package com.waleryn.fitapp.product;

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
