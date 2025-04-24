package scalable.mini_projects.Mini_Project2.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "ratings")
public class Rating {



    public enum EntityType {
        CAPTAIN, CUSTOMER, TRIP
    }

    @Id
    private String id;

    private Long entityId;  // References ID from PostgreSQL

    private EntityType entityType;

    private Integer score;  // Rating 1-5

    private String comment;

    private LocalDateTime ratingDate;

    public Rating(long entityId, String customer, int score, String comment, LocalDateTime now) {
        this.entityId = entityId;
        this.entityType = EntityType.CUSTOMER;
        this.score = score;
        this.comment = comment;
        this.ratingDate = now;
    }
    public Rating(Long entityId, EntityType entityType, Integer score, String comment) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.score = score;
        this.comment = comment;
        this.ratingDate = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(LocalDateTime ratingDate) {
        this.ratingDate = ratingDate;
    }

    // Validation method
    public boolean isValidScore() {
        return score != null && score >= 1 && score <= 5;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id='" + id + '\'' +
                ", entityId=" + entityId +
                ", entityType=" + entityType +
                ", score=" + score +
                ", ratingDate=" + ratingDate +
                '}';
    }
}
