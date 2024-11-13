package com.example.mesadigitalwebservices;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {
    public static String generateSecretKey(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[byteLength];
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    public static void main(String[] args) {
        String secretKey = generateSecretKey(64); // 256 bits
        System.out.println("Generated Secret Key: " + secretKey);
    }
}