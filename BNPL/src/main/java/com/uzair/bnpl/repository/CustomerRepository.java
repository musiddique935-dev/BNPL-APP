package com.uzair.bnpl.repository;

import com.uzair.bnpl.domain.Customer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * In-memory repository for Customer objects.
 * Responsible only for storing and retrieving Customer data.
 * No business rules here — handled in service layer.
 */
public class CustomerRepository {

    // Map to store customers by their unique ID for fast lookup
    private final Map<String, Customer> customersById = new HashMap<>();

    // Map to store customers by email for quick duplicate checks and login
    private final Map<String, Customer> customersByEmail = new HashMap<>();

    // Set to track used CNICs to prevent duplicate registrations
    private final Set<String> usedCnics = new HashSet<>();

    /**
     * Save a new customer into repository.
     * Adds customer to ID map, email map, and CNIC set.
     * @param customer Customer object to save
     */
    public void save(Customer customer) {
        customersById.put(customer.getCustomerId(), customer);   // store by ID
        customersByEmail.put(customer.getEmail(), customer);     // store by email
        usedCnics.add(customer.getCnic());                      // track CNIC
    }

    /**
     * Check if an email is already registered.
     * @param email Email to check
     * @return true if email exists, false otherwise
     */
    public boolean existsByEmail(String email) {
        return customersByEmail.containsKey(email);
    }

    /**
     * Check if a CNIC is already registered.
     * @param cnic CNIC to check
     * @return true if CNIC exists, false otherwise
     */
    public boolean existsByCnic(String cnic) {
        return usedCnics.contains(cnic);
    }

    /**
     * Find a customer by email.
     * @param email Email of customer
     * @return Customer object or null if not found
     */
    public Customer findByEmail(String email) {
        return customersByEmail.get(email);
    }

    /**
     * Find a customer by unique ID.
     * @param customerId Customer ID
     * @return Customer object or null if not found
     */
    public Customer findById(String customerId) {
        return customersById.get(customerId);
    }
}