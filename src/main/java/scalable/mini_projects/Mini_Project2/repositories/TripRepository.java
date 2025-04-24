package scalable.mini_projects.Mini_Project2.repositories;


import scalable.mini_projects.Mini_Project2.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    // Find trips within date range (inclusive)
    @Query("SELECT t FROM Trip t WHERE t.tripDate BETWEEN :startDate AND :endDate")
    List<Trip> findTripsByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    // Find trips by captain ID
    List<Trip> findByCaptainId(Long captainId);

    // Find trips by customer ID
    List<Trip> findByCustomerId(Long customerId);

    // Find trips by origin or destination
    @Query("SELECT t FROM Trip t WHERE t.origin LIKE %:location% OR t.destination LIKE %:location%")
    List<Trip> findByLocation(@Param("location") String location);

    // Find trips by captain ID within date range
    @Query("SELECT t FROM Trip t WHERE t.captain.id = :captainId AND t.tripDate BETWEEN :start AND :end")
    List<Trip> findByCaptainAndDateRange(
            @Param("captainId") Long captainId,
            @Param("start") LocalDateTime startDate,
            @Param("end") LocalDateTime endDate
    );

    // Find trips with payment status
    @Query("SELECT t FROM Trip t JOIN t.payment p WHERE p.paymentStatus = :status")
    List<Trip> findByPaymentStatus(@Param("status") Boolean status);

    // Count trips by captain
    @Query("SELECT COUNT(t) FROM Trip t WHERE t.captain.id = :captainId")
    long countByCaptainId(@Param("captainId") Long captainId);
}