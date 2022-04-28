package com.example.demo.util;

import lombok.NoArgsConstructor;

import java.security.SecureRandom;

@NoArgsConstructor
public class GenerateCodeUtil {
    public static String generateCode() {
        String code;

        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            int c = random.nextInt(9000) + 1000;
            code = String.valueOf(c);
        } catch (Exception exception) {
            throw new RuntimeException("Problem when generating random code");
        }
        return code;
    }
}
