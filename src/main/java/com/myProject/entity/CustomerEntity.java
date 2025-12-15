package com.myProject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long  id;
    @Column(name="role")
    private String role;

    @Column(name="full_name")
    private String fullName;
    @Column(name="email",unique = true,nullable = false)
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="address")
    private String address;
    @Column(name="password")
    private String password;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="country")
    private String country;
    @Column(name="zipcode")
    private  String zipcode;
    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<CustomerAlternateAddressEntity>  customerAlternateAddresses;

    public Long getId() {

        return id;
    }

    public String getRole() {
        return role;
    }

    public List<CustomerAlternateAddressEntity> getCustomerAlternateAddresses() {
        return customerAlternateAddresses;
    }

    public void setCustomerAlternateAddresses(List<CustomerAlternateAddressEntity> customerAlternateAddresses) {
        this.customerAlternateAddresses = customerAlternateAddresses;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
