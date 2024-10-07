package org.example;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionPass {

    public static String encrypt(String password){
        byte[] bytes = password.getBytes();
        var encodePassword = Base64.getEncoder().encodeToString(bytes);
        return encodePassword;
    }
    public static String decrypt(String password){
        byte[] decoder = Base64.getDecoder().decode(password);
        return new String(decoder, StandardCharsets.UTF_8);
    }
}
