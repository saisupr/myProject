package com.myProject.service;

import com.myProject.dto.Customer;
import com.myProject.dto.PageResponse;
import com.myProject.dto.ShoppingDto;
import com.myProject.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    String saveCustomer(Customer customer);
    String updateCustomer(Customer customer);
    PageResponse<Customer> getAllCustomer(int page, int size, String sortBy, String sortDirection,String category,String filterValue);
    String deleteCustomer(Long id);
    PageResponse<ShoppingDto> getAllShoppingData(int page, int size, String sortBy, String sortDirection);
    String saveShoppingData( ShoppingDto shoppingDto);
    String updateShoppingData( ShoppingDto shoppingDto);
    String deleteShoppingDataById(Long id);
    Optional<CustomerEntity> findByEmail(String email);
}
