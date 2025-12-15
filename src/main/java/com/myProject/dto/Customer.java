package com.myProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class Customer {
    @NotEmpty
    String  firstName;
    @NotEmpty
    String lastName;
    @NotEmpty(message = "email should not be empty")
            @Email
    String email;
    @NotEmpty(message = "password should not be empty")
    String password;
    String address;
    String phone;
    String country;
    String city;
    String zipcode;

    public List<CustomerAlternateAddress> getCustomerAlternateAddresses() {
        return customerAlternateAddresses;
    }

    public void setCustomerAlternateAddresses(List<CustomerAlternateAddress> customerAlternateAddresses) {
        this.customerAlternateAddresses = customerAlternateAddresses;
    }

    String state;
    Long id;
    String role;
    List<CustomerAlternateAddress> customerAlternateAddresses;
    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String password, String address, String phone, String country, String city, String zipcode, String state, Long id, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.id = id;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
