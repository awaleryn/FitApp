package com.example.fitapp;

import com.example.fitapp.auth.AuthenticationResponse;
import com.example.fitapp.auth.AuthenticationService;
import com.example.fitapp.auth.RegisterRequest;
import com.example.fitapp.product.Product;
import com.example.fitapp.product.ProductService;
import com.example.fitapp.product.ProductType;
import com.example.fitapp.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestDataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;

    public static String testJwtToken;

    @PostConstruct
    public void init() {
        if (userRepository.findByEmail("testuser@gmail.com").isEmpty()) {
            RegisterRequest registerRequest = new RegisterRequest(
                    "testName",
                    "testLastName",
                    "testuser@gmail.com",
                    "testPassword"
            );

            AuthenticationResponse response = authenticationService.register(registerRequest);
            testJwtToken = response.getAccessToken();
        }

        productService.addNewProduct(new Product(
                null,
                "testProduct",
                "testBrand",
                500,
                25 ,
                45,
                4,
                ProductType.SPICE));
    }
}
