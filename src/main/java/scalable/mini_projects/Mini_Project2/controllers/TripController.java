package scalable.mini_projects.Mini_Project2.controllers;

import scalable.mini_projects.Mini_Project2.models.Trip;
import scalable.mini_projects.Mini_Project2.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // 9.3.2.1 Add Trip
    @PostMapping("/addTrip")
    public Trip addTrip(@RequestBody Trip Trip) {
        return tripService.addTrip(Trip);
    }

    // 9.3.2.2 Get All Trips
    @GetMapping("/allTrips")
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    // 9.3.2.3 Get Specific Trip
    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    // 9.3.2.4 Update Trip
    @PutMapping("/update/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip Trip) {
        return tripService.updateTrip(id, Trip);
    }

    // 9.3.2.5 Delete Trip
    @DeleteMapping("/delete/{id}")
    public String deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return "Trip with ID " + id + " deleted successfully.";
    }

    // 9.3.2.6 Find Trips Within a Date Range
    @GetMapping("/findByDateRange")
    public List<Trip> findTripsWithinDateRange(@RequestParam LocalDateTime startDate,
                                               @RequestParam LocalDateTime endDate) {
        return tripService.findTripsWithinDateRange(startDate, endDate);
    }

    // 9.3.2.7 Find Trips By Captain ID
    @GetMapping("/findByCaptainId")
    public List<Trip> findTripsByCaptainId(@RequestParam Long captainId) {
        return tripService.findTripsByCaptainId(captainId);
    }
}