package com.example.ecom.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                 // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor   // Generates the full constructor User
@NoArgsConstructor
public class LoginResponse {

    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

 // Getters and setters...
}