package com.example.ecom.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data                 // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor
public class RegisterUserDto {
    private String email;
    
    private String password;
    
    private String username;
    
}