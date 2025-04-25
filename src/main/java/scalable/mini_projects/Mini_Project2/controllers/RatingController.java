package scalable.mini_projects.Mini_Project2.controllers;

import scalable.mini_projects.Mini_Project2.models.Rating;
import scalable.mini_projects.Mini_Project2.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // 9.5.2.1 Add Rating
    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating Rating) {
        return ratingService.addRating(Rating);
    }

    // 9.5.2.2 Update Rating
    @PutMapping("/update/{id}")
    public Rating updateRating(@PathVariable String id, @RequestBody Rating updatedRating) {
        return ratingService.updateRating(id, updatedRating);
    }

    // 9.5.2.3 Delete Rating
    @DeleteMapping("/delete/{id}")
    public String deleteRating(@PathVariable String id) {
        ratingService.deleteRating(id);
        return "Rating with ID " + id + " deleted successfully.";
    }

    // 9.5.2.4 Find Ratings For a Specific Entity
    @GetMapping("/findByEntity")
    public List<Rating> findRatingsByEntity(@RequestParam Long entityId, @RequestParam String entityType) {
        return ratingService.getRatingsByEntity(entityId, entityType);
    }

    // 9.5.2.5 Find Ratings Above a Specific Value
    @GetMapping("/findAboveScore")
    public List<Rating> findRatingsAboveScore(@RequestParam int minScore) {
        return ratingService.findRatingsAboveScore(minScore);
    }
}