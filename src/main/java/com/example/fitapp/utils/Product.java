package com.example.fitapp.utils;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String productName;

    private String productBrand;

    private double calories;

    private double protein;

    private double fat;

    private double carbohydrates;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

}
