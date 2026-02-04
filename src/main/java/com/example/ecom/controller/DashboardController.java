package com.example.ecom.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecom.entity.SensorReading;
import com.example.ecom.repo.SensorReadingRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
public class DashboardController {
    private final SensorReadingRepo readingRepo;
    public DashboardController(SensorReadingRepo readingRepo){
        this.readingRepo=readingRepo;
    }
    @GetMapping("/api/readings")
    public Page<SensorReading> getReadings(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("capturedAt").descending());
        return readingRepo.findAll(pageable);
    }
        
}
