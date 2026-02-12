package com.example.ecom.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="pend_claims")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendClaims {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private long id;
    private String macAddress;
    private String generatedCode;
    
}
