package com.example.miniapp.services;

import com.example.miniapp.models.Trip;
import com.example.miniapp.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    // 1. Add Trip
    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    // 2. Get all trips
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    // 3. Get trip by ID
    public Trip getTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found with ID: " + id));
    }

    // 4. Update trip
    public Trip updateTrip(Long id, Trip updatedTrip) {
        Trip existingTrip = getTripById(id); // Reuse logic
        existingTrip.setTripDate(updatedTrip.getTripDate());
        existingTrip.setOrigin(updatedTrip.getOrigin());
        existingTrip.setDestination(updatedTrip.getDestination());
        existingTrip.setTripCost(updatedTrip.getTripCost());
        existingTrip.setCaptain(updatedTrip.getCaptain());
        existingTrip.setCustomer(updatedTrip.getCustomer());
        return tripRepository.save(existingTrip);
    }

    // 5. Delete trip
    public void deleteTrip(Long id) {
        Trip existingTrip = getTripById(id);
        tripRepository.delete(existingTrip);
    }

    // 6. Find trips within date range
    public List<Trip> findTripsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return tripRepository.findTripsByDateRange(startDate, endDate);
    }

    // 7. Find trips by captain ID
    public List<Trip> findTripsByCaptainId(Long captainId) {
        return tripRepository.findByCaptainId(captainId);
    }
}
