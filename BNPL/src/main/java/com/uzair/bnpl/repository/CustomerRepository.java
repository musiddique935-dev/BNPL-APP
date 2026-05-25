package com.uzair.bnpl.repository;

// Save Customers - Check Duplicate Email / ID - Find by Email

import com.uzair.bnpl.domain.Customer;

import java.util.Map;
import java.util.Set;

public class CustomerRepository
{
    Map<String, Customer> customersByID;
    Map<String, Customer> customerByEmail;
    Set<String> usedCnis;
}
