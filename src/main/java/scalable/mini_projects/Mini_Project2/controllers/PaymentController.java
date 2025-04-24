package scalable.mini_projects.Mini_Project2.controllers;

import scalable.mini_projects.Mini_Project2.models.Payment;
import scalable.mini_projects.Mini_Project2.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // 1. Add Payment
    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);
    }

    // 2. Get All Payments
    @GetMapping("/allPayments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // 3. Get Specific Payment by ID
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    // 4. Update Payment
    @PutMapping("/update/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        return paymentService.updatePayment(id, payment);
    }

    // 5. Delete Payment
    @DeleteMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "Payment with ID " + id + " deleted successfully.";
    }

    // 6. Find Payments by Trip ID
    @GetMapping("/findByTripId")
    public List<Payment> findPaymentsByTripId(@RequestParam Long tripId) {
        return paymentService.findPaymentsByTripId(tripId);
    }

    // 7. Find Payments with Amount Greater Than Threshold
    @GetMapping("/findByAmountThreshold")
    public List<Payment> findPaymentsWithAmountGreaterThan(@RequestParam Double threshold) {
        return paymentService.findByAmountThreshold(threshold);
    }
}
