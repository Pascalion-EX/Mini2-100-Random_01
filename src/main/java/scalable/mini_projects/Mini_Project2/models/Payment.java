package scalable.mini_projects.Mini_Project2.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double amount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;  // "CARD", "CASH", etc.

    @Column(name = "payment_status", nullable = false)
    private Boolean paymentStatus;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Payment(Double amount, String paymentMethod, Boolean paymentStatus, Trip trip) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.trip = trip;
        this.paymentDate = LocalDateTime.now();
    }

    public Payment() {

    }
    public Payment(Double amount, String paymentMethod, Boolean paymentStatus) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = LocalDateTime.now();
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", paymentDate=" + paymentDate +
                '}';
    }

    // Business logic methods
    public boolean isPaid() {
        return Boolean.TRUE.equals(paymentStatus);
    }

    public void markAsPaid() {
        this.paymentStatus = true;
        this.paymentDate = LocalDateTime.now();
    }
}