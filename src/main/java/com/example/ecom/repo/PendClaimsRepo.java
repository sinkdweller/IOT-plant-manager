package com.example.ecom.repo;
import com.example.ecom.entity.PendClaims;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PendClaimsRepo extends JpaRepository<PendClaims, Long> {
    Optional<PendClaims> findByMacAddress(String macAddress);
}
