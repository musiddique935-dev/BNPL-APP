package com.uzair.bnpl.app;

import com.uzair.bnpl.dto.LoginDTO;
import com.uzair.bnpl.dto.RegistrationDTO;

import java.util.Scanner;

/**
 * Handles console input for Customer registration and login.
 * Does not contain business logic — only reads data.
 */
public class CustomerIO
{

    private final Scanner scanner;

    public CustomerIO()
    {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads registration input from user.
     * Returns a DTO with all fields needed for registration.
     */



    public RegistrationDTO readRegistrationInput()
    {
        System.out.println("=== Customer Registration ===");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter CNIC: ");
        String cnic = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        return new RegistrationDTO(name, email, password, cnic, phone);
    }

    /**
     * Reads login input from user.
     * Returns email and password in a simple object.
     */
    public LoginDTO readLoginInput()
    {
        System.out.println("=== Customer Login ===");

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        return new LoginDTO(email, password);
    }
}