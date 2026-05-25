package com.uzair.bnpl.dto;

public class RegistrationDTO
{
    private String name;
    private String email;
    private String password;
    private String cnic;
    private String phone;

    public RegistrationDTO(String name,
                           String email,
                           String password,
                           String cnic,
                           String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cnic = cnic;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getCnic() { return cnic; }
    public String getPhone() { return phone; }
}