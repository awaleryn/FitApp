package com.example.fitapp.password;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordRandomGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = LOWER.toUpperCase();
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()_+[]{}|;:,.<>?";

    private static final String ALL_CHARS = LOWER + UPPER + DIGITS + SPECIAL;
    private static final Random RANDOM = new SecureRandom();

    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            password.append(ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length())));
        }
        return password.toString();
    }

}
