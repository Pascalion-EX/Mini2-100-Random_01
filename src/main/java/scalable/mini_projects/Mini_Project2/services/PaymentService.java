package scalable.mini_projects.Mini_Project2.services;

import scalable.mini_projects.Mini_Project2.models.Payment;
import scalable.mini_projects.Mini_Project2.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment addPayment(Payment Payment) {
        return paymentRepository.save(Payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.orElse(null);
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        return paymentRepository.findById(id).map(Payment -> {
            Payment.setAmount(updatedPayment.getAmount());
            Payment.setPaymentMethod(updatedPayment.getPaymentMethod());
            Payment.setPaymentStatus(updatedPayment.getPaymentStatus());
            Payment.setTrip(updatedPayment.getTrip());
            return paymentRepository.save(Payment);
        }).orElse(null);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public List<Payment> findPaymentsByTripId(Long tripId) {
        return paymentRepository.findByTripId(tripId);
    }

    public List<Payment> findByAmountThreshold(Double threshold) {
        return paymentRepository.findByAmountGreaterThan(threshold);
    }
}