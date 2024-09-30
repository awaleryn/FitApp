package com.waleryn.fitapp;

import com.waleryn.fitapp.auth.AuthenticationRequest;
import com.waleryn.fitapp.auth.AuthenticationResponse;
import com.waleryn.fitapp.auth.AuthenticationService;
import com.waleryn.fitapp.auth.RegisterRequest;
import com.waleryn.fitapp.product.Product;
import com.waleryn.fitapp.product.ProductService;
import com.waleryn.fitapp.product.ProductType;
import com.waleryn.fitapp.token.Token;
import com.waleryn.fitapp.token.TokenRepository;
import com.waleryn.fitapp.user.UserDto;
import com.waleryn.fitapp.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TestDataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenRepository tokenRepository;

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

            UserDto user = authenticationService.register(registerRequest);
            authenticationService.authenticate(new AuthenticationRequest(registerRequest.getEmail(), registerRequest.getPassword()));

            List<Token> tokenList = tokenRepository.findAllValidTokensByUser(user.getId());

            AuthenticationResponse response = new AuthenticationResponse(
                    tokenList.get(0).getToken(),
                    tokenList.get(0).getToken()
            );

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
