package com.example.ecom.repo;

import com.example.ecom.entity.Device;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface DeviceRepo extends JpaRepository<Device, Long> {
    // Allows  Service to find the "Kitchen Fern" by its hardware ID
    Optional<Device> findByMacAddress(String macAddress);
    @Transactional
    @Modifying
    void deleteByMacAddress(String macAddress);
}