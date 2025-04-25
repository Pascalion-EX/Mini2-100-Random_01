package scalable.mini_projects.Mini_Project2.repositories;
import scalable.mini_projects.Mini_Project2.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
//MA7DASH YALMES EL REPOS law fi error mesh hab2a hina
//Please think twice 2abl mat8iar 7aga

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByTripDateBetween(LocalDateTime start, LocalDateTime end);
    List<Trip> findByCaptainId(Long captainId);
}
