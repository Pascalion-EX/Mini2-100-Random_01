package scalable.mini_projects.Mini_Project2.repositories;
import scalable.mini_projects.Mini_Project2.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//MA7DASH YALMES EL REPOS law fi error mesh hab2a hina
//Please think twice 2abl mat8iar 7aga

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    List<Rating> findByEntityIdAndEntityType(Long entityId, String entityType);
    List<Rating> findByScoreGreaterThan(Integer score);
}
