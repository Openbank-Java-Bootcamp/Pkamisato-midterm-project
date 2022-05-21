package com.ironhack.openbank_project.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThirdParty{

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private byte[] hashedKey;

    public ThirdParty(String name, String message) {
        this.name = name;
        this.hashedKey = getEncodedHashKey(message);
    }

    private byte[] getEncodedHashKey(String message) {
        MessageDigest digest = null;
        byte[] encodedHashKey = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            encodedHashKey = digest.digest(message.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return encodedHashKey;
    }

}
