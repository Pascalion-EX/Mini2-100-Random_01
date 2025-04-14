package com.example.miniapp.repositories;


import com.example.miniapp.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaptainRepository extends JpaRepository<Captain, Long> {

    // Find by license number (exact match)
    Captain findByLicenseNumber(String licenseNumber);

    // Find all captains with average rating above threshold
    @Query("SELECT c FROM Captain c WHERE c.avgRatingScore >= :threshold")
    List<Captain> findAllWithRatingAbove(@Param("threshold") Double threshold);

    // Alternative method using derived query
    List<Captain> findByAvgRatingScoreGreaterThanEqual(Double threshold);

    // Find by license number with case-insensitive search
    Captain findByLicenseNumberIgnoreCase(String licenseNumber);

    // Find all captains ordered by average rating (descending)
    @Query("SELECT c FROM Captain c ORDER BY c.avgRatingScore DESC")
    List<Captain> findAllOrderByRatingDesc();
}