package com.example.miniapp.controllers;

import com.example.miniapp.models.Trip;
import com.example.miniapp.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // 1. Add Trip
    @PostMapping("/addTrip")
    public Trip addTrip(@RequestBody Trip trip) {
        return tripService.addTrip(trip);
    }

    // 2. Get All Trips
    @GetMapping("/allTrips")
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    // 3. Get Trip By ID
    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    // 4. Update Trip
    @PutMapping("/update/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        return tripService.updateTrip(id, trip);
    }

    // 5. Delete Trip
    @DeleteMapping("/delete/{id}")
    public String deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return "Trip with ID " + id + " deleted successfully.";
    }

    // 6. Find Trips Within a Date Range
    @GetMapping("/findByDateRange")
    public List<Trip> findTripsWithinDateRange(@RequestParam LocalDate startDate,
                                               @RequestParam LocalDate endDate) {
        // Convert LocalDate to LocalDateTime for repository call
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);
        return tripService.findTripsWithinDateRange(start, end);
    }

    // 7. Find Trips By Captain ID
    @GetMapping("/findByCaptainId")
    public List<Trip> findTripsByCaptainId(@RequestParam Long captainId) {
        return tripService.findTripsByCaptainId(captainId);
    }
}
