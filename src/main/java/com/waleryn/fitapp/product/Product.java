package com.waleryn.fitapp.product;

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

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productBrand;

    @Column(nullable = false)
    private double calories;

    @Column(nullable = false)
    private double protein;

    @Column(nullable = false)
    private double fat;

    @Column(nullable = false)
    private double carbohydrates;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

}
