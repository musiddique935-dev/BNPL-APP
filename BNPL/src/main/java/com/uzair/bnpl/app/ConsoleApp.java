package com.uzair.bnpl.app;

import com.uzair.bnpl.dto.LoginDTO;
import com.uzair.bnpl.dto.RegistrationDTO;
import com.uzair.bnpl.domain.Customer;
import com.uzair.bnpl.repository.CustomerRepository;
import com.uzair.bnpl.service.CustomerService;

import java.util.Scanner;

/**
 * ConsoleApp - main entry point for BNPL console-based application.
 * Demonstrates professional separation of concerns:
 * - CustomerIO handles input
 * - CustomerService handles business logic
 * - CustomerRepository handles storage
 */
public class ConsoleApp {

    public static void main(String[] args) {

        // Initialize repository and service
        CustomerRepository repository = new CustomerRepository();
        CustomerService service = new CustomerService(repository);
        CustomerIO io = new CustomerIO();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to Mini BNPL Console App ===");

        while (running) {
            // Display menu
            System.out.println("\nPlease select an option:");
            System.out.println("1. Register");
            System.out.println("2. Verify OTP");
            System.out.println("3. Login");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Registration flow
                    try {
                        RegistrationDTO regDTO = io.readRegistrationInput();// getting users info - name/email etc
                        Customer newCustomer = service.registerCustomer(regDTO);// verifies user inputs and create or reject
                        System.out.println("Registration successful! Customer ID: " + newCustomer.getCustomerId());
                        System.out.println("Check console for OTP simulation.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Registration failed: " + e.getMessage());
                    }
                    break;

                case "2":
                    // OTP verification flow
                    try {
                        System.out.print("Enter Customer ID for OTP verification: ");
                        String customerId = scanner.nextLine().trim();
                        System.out.print("Enter OTP: ");
                        String otpInput = scanner.nextLine().trim();

                        service.verifyOtp(customerId, otpInput);
                        System.out.println("Customer successfully verified and activated!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("OTP Verification failed: " + e.getMessage());
                    }
                    break;

                case "3":
                    // Login flow
                    try {
                        LoginDTO loginDTO = io.readLoginInput();
                        Customer loggedIn = service.loginCustomer(loginDTO);
                        System.out.println("Login successful! Welcome, " + loggedIn.getName());
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Login failed: " + e.getMessage());
                    }
                    break;

                case "4":
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1-4.");
            }
        }

        scanner.close();
    }
}
