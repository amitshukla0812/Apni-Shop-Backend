package com.apnishop.backend.util;

import java.security.SecureRandom;

// Generates short random string ids, similar style to json-server's default ids.
// Used only when the frontend creates a new record without sending an id.
// If an id IS sent (e.g. during data migration from db.json), it is kept as-is.
public class IdGenerator {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
}
