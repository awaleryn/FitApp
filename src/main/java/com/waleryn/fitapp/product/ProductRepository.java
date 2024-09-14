package com.waleryn.fitapp.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByProductType(ProductType productType);

    Optional<Product> findByProductNameIgnoreCase(String productName);
}
