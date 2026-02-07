package com.example.ecom.repo;

import com.example.ecom.entity.Device;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface DeviceRepo extends JpaRepository<Device, Long> {

    Page<Device> findAllByUserId(Long userId, Pageable pageable);    
    Optional<Device> findByMacAddress(String macAddress);
    @Transactional
    @Modifying
    void deleteByMacAddress(String macAddress);
}