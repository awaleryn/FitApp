package com.example.fitapp.utils;

import com.example.fitapp.product.Product;
import org.springframework.test.web.servlet.MvcResult;

import static com.example.fitapp.product.ProductType.BEVERAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    private TestUtils() {}

    public final static int ONE_TIME = 1;

    public static Product createProductPepsi() {
        return new Product(
                null,
                "Pepsi",
                "PepsiCo",
                28,
                0,
                0,
                7,
                BEVERAGE
        );
    }

    public static Product createProductDrPepper() {
        return new Product(
                null,
                "Dr pepper",
                "Orangina Schweppes",
                17,
                0,
                0,
                4.3,
                BEVERAGE
        );
    }

    public static void assertResponseStatus(int result, int expectedStatus) {
        assertEquals(expectedStatus, result);
    }
}
