package scalable.mini_projects.Mini_Project2.services;

import scalable.mini_projects.Mini_Project2.models.Captain;
import scalable.mini_projects.Mini_Project2.repositories.CaptainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaptainService {

    private final CaptainRepository captainRepository;

    @Autowired
    public CaptainService(CaptainRepository captainRepository) {
        this.captainRepository = captainRepository;
    }

    // 1. Add Captain
    public Captain addCaptain(Captain captain) {
        return captainRepository.save(captain);
    }

    // 2. Get all captains
    public List<Captain> getAllCaptains() {
        return captainRepository.findAll();
    }

    // 3. Get captain by ID
    public Captain getCaptainById(Long id) {
        return captainRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Captain not found with ID: " + id));
    }

    // 4. Get captains by rating threshold
    public List<Captain> getCaptainsByRating(Double ratingThreshold) {
        return captainRepository.findByAvgRatingScoreGreaterThanEqual(ratingThreshold);
    }

    // 5. Get captain by license number
    public Captain getCaptainByLicenseNumber(String licenseNumber) {
        return captainRepository.findByLicenseNumber(licenseNumber);
    }
}
