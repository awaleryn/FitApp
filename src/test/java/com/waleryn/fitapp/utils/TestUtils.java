package com.waleryn.fitapp.utils;

import com.waleryn.fitapp.product.Product;
import com.waleryn.fitapp.product.ProductType;

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
                ProductType.BEVERAGE
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
                ProductType.BEVERAGE
        );
    }

    public static void assertResponseStatus(int result, int expectedStatus) {
        assertEquals(expectedStatus, result);
    }
}
