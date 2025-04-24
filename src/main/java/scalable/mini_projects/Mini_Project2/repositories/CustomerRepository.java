package scalable.mini_projects.Mini_Project2.repositories;

import scalable.mini_projects.Mini_Project2.models.Customer;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find customers by email domain (e.g., "@gmail.com")
    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:domain")
    List<Customer> findByEmailDomain(@Param("domain") String domain);

    // Find customers by phone number prefix
    @Query("SELECT c FROM Customer c WHERE c.phoneNumber LIKE :prefix%")
    List<Customer> findByPhonePrefix(@Param("prefix") String prefix);

    // Exact match by email
    Customer findByEmail(String email);

    // Case-insensitive email search
    Customer findByEmailIgnoreCase(String email);

    // Find customers by name containing string (case-insensitive)
    List<Customer> findByNameContainingIgnoreCase(String namePart);

    // Count customers by email domain
    @Query("SELECT COUNT(c) FROM Customer c WHERE c.email LIKE %:domain")
    long countByEmailDomain(@Param("domain") String domain);
}