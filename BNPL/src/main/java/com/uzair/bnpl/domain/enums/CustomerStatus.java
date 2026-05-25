package com.uzair.bnpl.domain.enums;

/**
 * Represents the current state of a Customer in the system.
 */
public enum CustomerStatus {
    PENDING_VERIFICATION, // Newly registered, waiting for OTP/email verification
    ACTIVE,               // Verified and active
    INACTIVE,             // Manually deactivated or blocked
    SUSPENDED,            // Suspicious activity or temporary hold
    DELETED               // Account removed
}