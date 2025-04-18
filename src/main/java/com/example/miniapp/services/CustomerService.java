package com.example.miniapp.services;

import com.example.miniapp.models.Customer;
import com.example.miniapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // 1. Add Customer
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // 2. Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // 3. Get customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
    }

    // 4. Update customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        return customerRepository.save(existingCustomer);
    }

    // 5. Delete customer
    public void deleteCustomer(Long id) {
        Customer existingCustomer = getCustomerById(id);
        customerRepository.delete(existingCustomer);
    }

    // 6. Find customers by email domain
    public List<Customer> findCustomersByEmailDomain(String domain) {
        return customerRepository.findByEmailDomain(domain);
    }

    // 7. Find customers by phone prefix
    public List<Customer> findCustomersByPhonePrefix(String prefix) {
        return customerRepository.findByPhonePrefix(prefix);
    }
}
