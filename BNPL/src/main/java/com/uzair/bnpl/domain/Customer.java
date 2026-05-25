package com.uzair.bnpl.domain;

import com.uzair.bnpl.domain.enums.CustomerStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Customer {

    private final String customerId;
    private final String cnic;
    private final LocalDateTime createdAt;

    private String name;
    private String email;
    private String password;
    private String phone;
    private BigDecimal creditLimit;
    private BigDecimal availableLimit;
    private CustomerStatus status;

    // Constructor For Object Creation with mandatory fields
    public Customer(String customerId,
                    String name,
                    String email,
                    String password,
                    String cnic,
                    String phone,
                    BigDecimal creditLimit,
                    BigDecimal availableLimit,
                    CustomerStatus status,
                    LocalDateTime createdAt) {

        this.customerId = requireText(customerId, "Customer ID is required");
        this.name = requireText(name, "Name is required");
        this.email = requireText(email, "Email is required");
        this.password = requireText(password, "Password is required");
        this.cnic = requireText(cnic, "CNIC is required");
        this.phone = requireText(phone, "Phone is required");
        this.creditLimit = requirePositiveAmount(creditLimit, "Credit limit must be positive");
        this.availableLimit = requireNonNegativeAmount(availableLimit, "Available limit cannot be negative");
        this.status = Objects.requireNonNull(status, "Customer status is required");
        this.createdAt = Objects.requireNonNull(createdAt, "Created date is required");
    }


    // Inconsistency checking functions
    private static String requireText(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return value.trim();
    }
    private static BigDecimal requirePositiveAmount(BigDecimal value, String message) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }
    private static BigDecimal requireNonNegativeAmount(BigDecimal value, String message) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }


    // Getting fixed fields
    public String getCustomerId() {
        return customerId;
    }
    public String getCnic() {
        return cnic;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    //Getters & Setters FOr Dynamically changing values
    public String getName() {
        return name;
    }
    public void updateName(String name) {
        this.name = requireText(name, "Name is required");
    }

    public String getEmail() {
        return email;
    }
    public void updateEmail(String email) {
        this.email = requireText(email, "Email is required");
    }

    public String getPassword() {
        return password;
    }
    public void updatePassword(String password) {
        this.password = requireText(password, "Password is required");
    }

    public String getPhone() {
        return phone;
    }
    public void updatePhone(String phone) {
        this.phone = requireText(phone, "Phone is required");
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }
    public void updateCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = requirePositiveAmount(creditLimit, "Credit limit must be positive");
    }

    public BigDecimal getAvailableLimit() {
        return availableLimit;
    }
    public void updateAvailableLimit(BigDecimal availableLimit) {
        this.availableLimit = requireNonNegativeAmount(availableLimit, "Available limit cannot be negative");
    }

    public CustomerStatus getStatus() {
        return status;
    }
    public void updateStatus(CustomerStatus status) {
        this.status = Objects.requireNonNull(status, "Customer status is required");
    }
}