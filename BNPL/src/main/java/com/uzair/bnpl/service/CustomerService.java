package com.uzair.bnpl.service;

import com.uzair.bnpl.domain.Customer;
import com.uzair.bnpl.domain.enums.CustomerStatus;
import com.uzair.bnpl.repository.CustomerRepository;
import com.uzair.bnpl.dto.RegistrationDTO;
import com.uzair.bnpl.dto.LoginDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.Map;

/**
 * CustomerService handles registration, login, and OTP verification logic.
 * Demonstrates realistic business rules for fintech registration.
 */
public class CustomerService
{

    private final CustomerRepository repository;

    // In-memory OTP storage (for console simulation)
    private final Map<String, String> otpStorage = new java.util.HashMap<>();

    // Dependency Injection of repositary and make it mandatory to create service with repo
    public CustomerService(CustomerRepository repository)
    {
        this.repository = repository;
    }

    /**
     * Registers a new customer, generates OTP, and sets status to PENDING_VERIFICATION
     */
    public Customer registerCustomer(RegistrationDTO dto) {

        // Validate required fields
        if (dto.getName() == null || dto.getName().isBlank()) throw new IllegalArgumentException("Name is required");
        if (dto.getEmail() == null || dto.getEmail().isBlank()) throw new IllegalArgumentException("Email is required");
        if (dto.getPassword() == null || dto.getPassword().isBlank()) throw new IllegalArgumentException("Password is required");
        if (dto.getCnic() == null || dto.getCnic().isBlank()) throw new IllegalArgumentException("CNIC is required");

        // Check duplicates
        if (repository.existsByEmail(dto.getEmail())) throw new IllegalArgumentException("Email already registered");
        if (repository.existsByCnic(dto.getCnic())) throw new IllegalArgumentException("CNIC already registered");

        // Generate system fields
        String customerId = "CUST-" + UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();
        BigDecimal defaultCreditLimit = BigDecimal.valueOf(100000);
        BigDecimal availableLimit = defaultCreditLimit;
        CustomerStatus status = CustomerStatus.PENDING_VERIFICATION;

        // Create Customer object
        Customer customer = new Customer(customerId, dto.getName(), dto.getEmail(), dto.getPassword(), dto.getCnic(),
                dto.getPhone(), defaultCreditLimit, availableLimit, status, createdAt);

        // Save in repository
        repository.save(customer);

        // Generate OTP (for simulation, 6-digit number)
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpStorage.put(customer.getCustomerId(), otp);

        // Simulate sending OTP via email (console)
        System.out.println("[SIMULATION] OTP sent to " + customer.getEmail() + ": " + otp);

        return customer;
    }

    /**
     * Verifies OTP entered by user and activates customer
     */
    public void verifyOtp(String customerId, String otpInput) {
        String actualOtp = otpStorage.get(customerId);
        if (actualOtp == null) throw new IllegalArgumentException("No OTP found for this customer");
        if (!actualOtp.equals(otpInput)) throw new IllegalArgumentException("Invalid OTP");

        // OTP correct, activate customer
        Customer customer = repository.findById(customerId);
        if (customer == null) throw new IllegalArgumentException("Customer not found");
        customer.updateStatus(CustomerStatus.ACTIVE);

        // Remove OTP as it is used
        otpStorage.remove(customerId);
        System.out.println("Customer verified and activated.");
    }

    /**
     * Logs in a customer by checking email and password.
     */
    public Customer loginCustomer(LoginDTO dto) {
        Customer customer = repository.findByEmail(dto.getEmail());
        if (customer == null) throw new IllegalArgumentException("Email not registered");
        if (!customer.getPassword().equals(dto.getPassword())) throw new IllegalArgumentException("Invalid password");

        if (customer.getStatus() != CustomerStatus.ACTIVE) {
            throw new IllegalStateException("Customer is not active. Verify OTP first or contact support.");
        }
        return customer;
    }
}