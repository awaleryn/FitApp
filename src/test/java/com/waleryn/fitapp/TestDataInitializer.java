package com.waleryn.fitapp;

import com.waleryn.fitapp.auth.AuthenticationResponse;
import com.waleryn.fitapp.auth.AuthenticationService;
import com.waleryn.fitapp.auth.RegisterRequest;
import com.waleryn.fitapp.product.Product;
import com.waleryn.fitapp.product.ProductService;
import com.waleryn.fitapp.product.ProductType;
import com.waleryn.fitapp.user.UserRepository;
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
