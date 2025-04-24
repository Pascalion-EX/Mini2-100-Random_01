package scalable.mini_projects.Mini_Project2.repositories;

import scalable.mini_projects.Mini_Project2.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {

    // Find ratings for specific entity (type + ID)
    @Query("{'entityType': ?0, 'entityId': ?1}")
    List<Rating> findByEntity(String entityType, Long entityId);

    // Find ratings above score threshold
    @Query("{'score': {'$gte': ?0}}")
    List<Rating> findByScoreGreaterThanEqual(int minScore);

    // Find ratings below score threshold
    @Query("{'score': {'$lte': ?0}}")
    List<Rating> findByScoreLessThanEqual(int maxScore);

    // Find ratings in score range (inclusive)
    @Query("{'score': {'$gte': ?0, '$lte': ?1}}")
    List<Rating> findByScoreBetween(int minScore, int maxScore);

    // Calculate average rating for an entity
    @Query(value = "{'entityType': ?0, 'entityId': ?1}", fields = "{'score': 1}")
    List<Rating> findScoresByEntity(String entityType, Long entityId);

    default double calculateAverageRating(String entityType, Long entityId) {
        List<Rating> ratings = findScoresByEntity(entityType, entityId);
        return ratings.stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0.0);
    }

    // Count ratings by entity
    long countByEntityTypeAndEntityId(String entityType, Long entityId);

    // Find ratings containing text in comment (case insensitive)
    @Query("{'comment': {$regex: ?0, $options: 'i'}}")
    List<Rating> findByCommentContaining(String text);

    // Find latest ratings with pagination
    List<Rating> findByEntityTypeOrderByRatingDateDesc(String entityType);
}