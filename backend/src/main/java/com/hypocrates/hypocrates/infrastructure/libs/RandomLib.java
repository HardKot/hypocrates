package com.hypocrates.hypocrates.infrastructure.libs;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

@Component
@Slf4j
@AllArgsConstructor
public class RandomLib {
    private static final String digits = "0123456789";
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase(Locale.ROOT);

    private static final String chars = upper + lower;
    private static final String all = upper + lower + digits;
    private static final String digitsCharsLower = lower + digits;


    public String randomCode() {
        var random = new SecureRandom();
        var buf = new StringBuilder();
        for (int idx = 0; idx < 6; ++idx)
            buf.append(digits.charAt(random.nextInt(digits.length())));
        return buf.toString();
    }

    public String randomString(int length) {
        var random = new SecureRandom();
        var buf = new StringBuilder();

        for (int idx = 0; idx < length; ++idx)
            buf.append(digitsCharsLower.charAt(random.nextInt(digitsCharsLower.length())));
        return buf.toString();
    }

    public String randomLowerChars(int length) {
        return getRandom(length, lower);
    }

    private String getRandom(int length, String chars) {
        var random = new SecureRandom();
        var buf = new StringBuilder();

        for (int idx = 0; idx < length; ++idx) {
            int index = random.nextInt(chars.length());
            buf.append(chars.charAt(index));
        }
        return buf.toString();
    }
}
