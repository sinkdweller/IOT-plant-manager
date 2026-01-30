package com.example.ecom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecom.entity.User;
import com.example.ecom.dtos.ClaimDeviceDto;
import com.example.ecom.entity.Device;
import com.example.ecom.repo.DeviceRepo;
import com.example.ecom.repo.UserRepo;

@RestController
public class DeviceController {
    private final DeviceRepo deviceRepo;
    private final UserRepo userRepo;
    public DeviceController(DeviceRepo deviceRepo, UserRepo userRepo){
        this.deviceRepo=deviceRepo;
        this.userRepo=userRepo;
    }
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeDevice(@RequestParam String macAddress){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("User from token: (remove) " + username);

        User currentUser = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found in DB"));

        
        Device device = deviceRepo.findByMacAddress(macAddress).orElseThrow(()-> new RuntimeException("Device not found."));
        if (device.getUser() == null || !device.getUser().equals(currentUser)) {
            return ResponseEntity.status(409).body("You do not have permission to remove this device.");
        }
        deviceRepo.deleteByMacAddress(macAddress);
        System.out.println("Device deleted");
        return ResponseEntity.ok("Device "+macAddress + " deleted!");

    }
    @PostMapping("/claim")
    public ResponseEntity<String> claimDevice(@RequestBody ClaimDeviceDto dto) {
        System.out.println("  Starting claim for MAC: " + dto.getMacAddress());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("User from token: " + username);

        User currentUser = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found in DB"));
        System.out.println("Found User ID: " + currentUser.getId());

        Device device = deviceRepo.findByMacAddress(dto.getMacAddress())
            .orElseGet(() -> {
                System.out.println("Creating brand new device record");
                Device d = new Device();
                d.setMacAddress(dto.getMacAddress());
                return d;
            });

        if (device.getUser() != null && !device.getUser().equals(currentUser)) {
            System.out.println("Already owned by someone else!");
            return ResponseEntity.status(409).body("Already claimed by another user.");
        }

        device.setName(dto.getName());
        device.setUser(currentUser);
        
        deviceRepo.save(device);

        return ResponseEntity.ok("Device added!");
    }
    
}
