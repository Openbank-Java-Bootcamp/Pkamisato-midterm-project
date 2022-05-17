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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private byte[] hashedKey;
    public ThirdParty(String name, String message) throws NoSuchAlgorithmException {
        this.name = name;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHashKey = digest.digest(
                message.getBytes(StandardCharsets.UTF_8));
        this.hashedKey = encodedHashKey;

    }

}
