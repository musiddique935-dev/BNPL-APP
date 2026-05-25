package com.uzair.bnpl.domain;

import com.uzair.bnpl.domain.enums.CustomerStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer
{
    private String CustomerID;
    private String Name;
    private String Email;
    private String Password;
    private String CNIC;
    private String Phone;

    private BigDecimal CreditLimit;
    private BigDecimal AvailableLimit;

    private CustomerStatus Status;
    private LocalDateTime CreatedAt;
}
