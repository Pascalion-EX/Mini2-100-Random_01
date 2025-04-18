package com.example.miniapp.services;

import com.example.miniapp.models.Rating;
import com.example.miniapp.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    // 1. Add Rating
    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    // 2. Update Rating
    public Rating updateRating(String id, Rating updatedRating) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rating not found with ID: " + id));

        existingRating.setScore(updatedRating.getScore());
        existingRating.setComment(updatedRating.getComment());
        existingRating.setRatingDate(updatedRating.getRatingDate());

        return ratingRepository.save(existingRating);
    }

    // 3. Delete Rating
    public void deleteRating(String id) {
        if (!ratingRepository.existsById(id)) {
            throw new IllegalArgumentException("Rating not found with ID: " + id);
        }
        ratingRepository.deleteById(id);
    }

    // 4. Get Ratings By Entity
    public List<Rating> getRatingsByEntity(Long entityId, String entityType) {
        return ratingRepository.findByEntity(entityType.toUpperCase(), entityId);
    }

    // 5. Find Ratings Above a Specific Value
    public List<Rating> findRatingsAboveScore(int minScore) {
        return ratingRepository.findByScoreGreaterThanEqual(minScore);
    }
}
