package com.example.miniapp.controllers;

import com.example.miniapp.models.Captain;
import com.example.miniapp.services.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/captain")
public class CaptainController {

    private final CaptainService captainService;

    @Autowired
    public CaptainController(CaptainService captainService) {
        this.captainService = captainService;
    }

    // 1. Add Captain
    @PostMapping("/addCaptain")
    public Captain addCaptain(@RequestBody Captain captain) {
        return captainService.addCaptain(captain);
    }

    // 2. Get All Captains
    @GetMapping("/allCaptains")
    public List<Captain> getAllCaptains() {
        return captainService.getAllCaptains();
    }

    // 3. Get Captain By ID
    @GetMapping("/{id}")
    public Captain getCaptainById(@PathVariable Long id) {
        return captainService.getCaptainById(id);
    }

    // 4. Filter Captains By Rating
    @GetMapping("/filterByRating")
    public List<Captain> getCaptainsByRating(@RequestParam Double ratingThreshold) {
        return captainService.getCaptainsByRating(ratingThreshold);
    }

    // 5. Filter Captain By License Number
    @GetMapping("/filterByLicenseNumber")
    public Captain getCaptainByLicenseNumber(@RequestParam String licenseNumber) {
        return captainService.getCaptainByLicenseNumber(licenseNumber);
    }
}
