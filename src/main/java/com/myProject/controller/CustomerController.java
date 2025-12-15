package com.myProject.controller;

import com.myProject.dao.CustomerRepository;
import com.myProject.dto.Customer;
import com.myProject.dto.PageResponse;
import com.myProject.dto.ShoppingDto;
import com.myProject.entity.CustomerEntity;
import com.myProject.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @PostMapping("/saveCustomer")
    public String saveCustomer(@RequestBody Customer customer) {
        Optional<CustomerEntity> customerEntity =customerService.findByEmail(customer.getEmail()) ;
        if(!customerEntity.isEmpty()){
            return "Email address already registered";
        }
        customerService .saveCustomer(customer);
        return "User registered successfully";
    }

    @GetMapping("/getAllCustomer")
        public PageResponse<Customer> getAllCustomer(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5")int size,@RequestParam String sortBy,@RequestParam String sortDirection,@RequestParam String category,@RequestParam String filterValue ) {
            return customerService.getAllCustomer(page,size,sortBy,sortDirection,category,filterValue);


    }

   /* @GetMapping("/users")
    public String users(Model model) {
        List<CustomerEntity> customers = customerRepository.findAll();
        List<Customer>customerList=customers.stream().map((customer)->convertEntityToCustomer(customer)).collect(Collectors.toList());
        model.addAttribute("users",customerList);
        return "users";
    }*/
 /*   @PostMapping("/update")
    public String updateUser(@RequestParam Long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        Optional<CustomerEntity> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            CustomerEntity customerEntity = customer.get();
            customerEntity.setFullName(firstName + " " + lastName);
            customerEntity.setEmail(email);
            customerRepository.save(customerEntity);
        }
        return "Users updated successfully";
    }*/

    @DeleteMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("id") Long id) {

        return customerService.deleteCustomer(id);
    }
    @PutMapping("/updateCustomer")

    public String updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);

    }
    @GetMapping("/getAllShoppingData")
    public PageResponse<ShoppingDto> getAllShoppingData(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5")int size,@RequestParam String sortBy,@RequestParam String sortDirection) {
        return customerService.getAllShoppingData(page,size,sortBy,sortDirection);
    }
    @PostMapping("/saveShoppingData")
    public String saveShoppingData(@RequestBody ShoppingDto shoppingDto) {
        return customerService.saveShoppingData(shoppingDto);
    }
    @PutMapping("/updateShoppingData")
    public String updateShoppingData(@RequestBody ShoppingDto shoppingDto) {
        return customerService.updateShoppingData(shoppingDto);
    }
    @DeleteMapping("/deleteShoppingDataById")
    public String deleteShoppingDataById(@RequestParam("id")Long id) {
        return customerService.deleteShoppingDataById(id);
    }
}

