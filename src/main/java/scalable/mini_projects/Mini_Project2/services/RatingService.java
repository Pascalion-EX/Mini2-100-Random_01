package scalable.mini_projects.Mini_Project2.services;

import scalable.mini_projects.Mini_Project2.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scalable.mini_projects.Mini_Project2.models.Rating;
import scalable.mini_projects.Mini_Project2.repositories.RatingRepository;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating addRating(Rating Rating) {
        return ratingRepository.save(Rating);
    }

    public Rating updateRating(String id, Rating updatedRating) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with ID: " + id));
        existingRating.setScore(updatedRating.getScore());
        existingRating.setComment(updatedRating.getComment());
        existingRating.setEntityId(updatedRating.getEntityId());
        existingRating.setEntityType(updatedRating.getEntityType());
        return ratingRepository.save(existingRating);
    }

    public void deleteRating(String id) {
        if (!ratingRepository.existsById(id)) {
            throw new RuntimeException("Rating not found with ID: " + id);
        }
        ratingRepository.deleteById(id);
    }

    public List<Rating> getRatingsByEntity(Long entityId, String entityType) {
        return ratingRepository.findByEntityIdAndEntityType(entityId, entityType);
    }

    public List<Rating> findRatingsAboveScore(int minScore) {
        return ratingRepository.findByScoreGreaterThan(minScore);
    }
}
