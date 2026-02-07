package com.example.ecom.responses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                 // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor   
@NoArgsConstructor
public class OwnedDeviceResponse {
    private String macAddress; 
    private String name; 
}
