package com.example.miniapp.controllers;

import com.example.miniapp.models.Rating;
import com.example.miniapp.services.RatingService;
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

    // 1. Add Rating
    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating rating) {
        return ratingService.addRating(rating);
    }

    // 2. Update Rating
    @PutMapping("/update/{id}")
    public Rating updateRating(@PathVariable String id, @RequestBody Rating updatedRating) {
        return ratingService.updateRating(id, updatedRating);
    }

    // 3. Delete Rating
    @DeleteMapping("/delete/{id}")
    public String deleteRating(@PathVariable String id) {
        ratingService.deleteRating(id);
        return "Rating with ID " + id + " deleted successfully.";
    }

    // 4. Find Ratings For a Specific Entity
    @GetMapping("/findByEntity")
    public List<Rating> findRatingsByEntity(@RequestParam Long entityId,
                                            @RequestParam String entityType) {
        return ratingService.getRatingsByEntity(entityId, entityType);
    }

    // 5. Find Ratings Above a Specific Score
    @GetMapping("/findAboveScore")
    public List<Rating> findRatingsAboveScore(@RequestParam int minScore) {
        return ratingService.findRatingsAboveScore(minScore);
    }
}
