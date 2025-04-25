package scalable.mini_projects.Mini_Project2.services;
import scalable.mini_projects.Mini_Project2.models.*;
import scalable.mini_projects.Mini_Project2.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scalable.mini_projects.Mini_Project2.repositories.TripRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip addTrip(Trip Trip) {
        return tripRepository.save(Trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found with ID: " + id));
    }

    public Trip updateTrip(Long id, Trip updatedTrip) {
        Trip existingTrip = getTripById(id);
        existingTrip.setTripDate(updatedTrip.getTripDate());
        existingTrip.setOrigin(updatedTrip.getOrigin());
        existingTrip.setDestination(updatedTrip.getDestination());
        existingTrip.setTripCost(updatedTrip.getTripCost());
        existingTrip.setCaptain(updatedTrip.getCaptain());
        existingTrip.setCustomer(updatedTrip.getCustomer());
        return tripRepository.save(existingTrip);
    }

    public void deleteTrip(Long id) {

        tripRepository.deleteById(id);
    }

    public List<Trip> findTripsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return tripRepository.findByTripDateBetween(startDate, endDate);
    }

    public List<Trip> findTripsByCaptainId(Long captainId) {
        return tripRepository.findByCaptainId(captainId);
    }
}