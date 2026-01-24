package com.example.ecom.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data                 // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor
public class LoginUserDto {
    private String email;
    private String username;
    private String password;
    
    // getters and setters here...
}