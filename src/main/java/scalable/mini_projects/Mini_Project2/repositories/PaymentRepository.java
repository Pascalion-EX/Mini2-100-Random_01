package scalable.mini_projects.Mini_Project2.repositories;

import org.springframework.stereotype.Repository;
import scalable.mini_projects.Mini_Project2.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Find payment by trip ID (exact match)
    Payment findByTripId(Long tripId);

    // Find payments above amount threshold
    @Query("SELECT p FROM Payment p WHERE p.amount >= :threshold")
    List<Payment> findPaymentsAboveAmount(@Param("threshold") Double threshold);

    // Find payments below amount threshold
    List<Payment> findByAmountLessThan(Double threshold);

    // Find payments by payment method
    List<Payment> findByPaymentMethod(String paymentMethod);

    // Find successful payments (status = true)
    List<Payment> findByPaymentStatusTrue();

    // Find failed payments (status = false)
    List<Payment> findByPaymentStatusFalse();

    // Find payments in amount range (inclusive)
    @Query("SELECT p FROM Payment p WHERE p.amount BETWEEN :min AND :max")
    List<Payment> findPaymentsInAmountRange(
            @Param("min") Double minAmount,
            @Param("max") Double maxAmount
    );

    // Calculate total payments amount by payment method
    @Query("SELECT p.paymentMethod, SUM(p.amount) FROM Payment p GROUP BY p.paymentMethod")
    List<Object[]> sumAmountsByPaymentMethod();
}