package com.example.miniapp.services;

import com.example.miniapp.models.Payment;
import com.example.miniapp.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // 1. Add Payment
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // 2. Get all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // 3. Get payment by ID
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with ID: " + id));
    }

    // 4. Update payment
    public Payment updatePayment(Long id, Payment updatedPayment) {

        Payment existingPayment = getPaymentById(id);

        existingPayment.setAmount(updatedPayment.getAmount());

        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());

        existingPayment.setPaymentStatus(updatedPayment.getPaymentStatus());

        existingPayment.setPaymentDate(updatedPayment.getPaymentDate());

        return paymentRepository.save(existingPayment);
    }

    // 5. Delete payment
    public void deletePayment(Long id) {
        Payment payment = getPaymentById(id);

        paymentRepository.delete(payment);
    }

    // 6. Find payments by trip ID
    public List<Payment> findPaymentsByTripId(Long tripId) {

        Payment payment = paymentRepository.findByTripId(tripId);

        if (payment != null) {
            return List.of(payment);
        } else {
            return List.of();
        }
    }


    // 7. Find payments with amount above threshold
    public List<Payment> findByAmountThreshold(Double threshold) {
        return paymentRepository.findPaymentsAboveAmount(threshold);
    }
}
