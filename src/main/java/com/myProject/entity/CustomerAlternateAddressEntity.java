package com.myProject.entity;

import com.myProject.dto.Customer;
import jakarta.persistence.*;

@Entity
@Table(name="CustomerAlternateAddress")
public class CustomerAlternateAddressEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long  id;
    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="country")
    private String country;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customerEntity;

    public Long getId() {
        return id;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
