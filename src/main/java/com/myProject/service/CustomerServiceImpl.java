package com.myProject.service;

import com.myProject.dao.CustomerRepository;
import com.myProject.dto.*;
import com.myProject.entity.CustomerAlternateAddressEntity;
import com.myProject.entity.CustomerEntity;
import com.myProject.expectionhandling.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private RestTemplate restTemplate=new RestTemplate();

    private final String url= "http://localhost:8081";

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String saveCustomer(Customer customer) {
/*        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFullName(customer.getFirstName() + " " + customer.getLastName());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setPhone(customer.getPhone());
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setPassword(encoder.encode(customer.getPassword()));
        customerEntity.setCountry(customer.getCountry());
        customerEntity.setCity(customer.getCity());
        customerEntity.setState(customer.getState());
        customerEntity.setRole(customer.getRole().toUpperCase());
        customerEntity.setZipcode(customer.getZipcode());
        List<CustomerAlternateAddressEntity> customerAlternateAddressEntityList = new ArrayList<>();
        for (CustomerAlternateAddress customerAlternateAddress:customer.getCustomerAlternateAddresses()){
            CustomerAlternateAddressEntity customerAlternateAddressEntity = new CustomerAlternateAddressEntity();
            customerAlternateAddressEntity.setAddress(customerAlternateAddress.getAddress());
            customerAlternateAddressEntity.setCity(customerAlternateAddress.getCity());
            customerAlternateAddressEntity.setCountry(customerAlternateAddress.getCountry());
            customerAlternateAddressEntity.setState(customerAlternateAddress.getState());
            customerAlternateAddressEntity.setCustomerEntity(customerEntity);
            customerAlternateAddressEntityList.add(customerAlternateAddressEntity);
        }
       customerEntity.setCustomerAlternateAddresses(customerAlternateAddressEntityList);*/
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        List<CustomerAlternateAddressEntity> customerAlternateAddresses = customerEntity.getCustomerAlternateAddresses();
        customerAlternateAddresses.forEach(addr-> addr.setCustomerEntity(customerEntity));
        customerEntity.setCustomerAlternateAddresses(customerAlternateAddresses);
        customerRepository.save(customerEntity);
        return "Customer saved Successfully";
    }

    @Override
    public PageResponse<Customer> getAllCustomer(int page, int size, String sortBy, String sortDirection,String category,String filterValue) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CustomerEntity> customer = null;
        if (category.equalsIgnoreCase("city")) {
            customer = customerRepository.findByCity(filterValue, pageable);
        } else {
            customer = customerRepository.findAll(pageable);
        }
   /* String fieldFilteringValue[]=fieldFiltering.split(",");
        String fullName=null;

        for (String field : fieldFilteringValue) {
            if (field.equalsIgnoreCase("fullName")) {
                fullName=entity.getF
            }
        }*/


        Page<Customer> CustomerPage =  customer.map(entity ->

                new Customer(
                        entity.getFullName(),
                        null,
                        entity.getEmail(),
                        entity.getPassword(),
                        entity.getAddress(),
                        entity.getPhone(),
                        entity.getCountry(),
                        entity.getCity(),
                        entity.getZipcode(),
                        entity.getState(),
                        entity.getId(),
                        entity.getRole()
                        ));
        return new PageResponse<>(CustomerPage);


        /*List<Customer> customerList = new ArrayList<>();
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        for (CustomerEntity customerEntity : customerEntityList) {
            Customer customer = new Customer();
            customer.setCity(customerEntity.getCity());
            customer.setCountry(customerEntity.getCountry());
            customer.setEmail(customerEntity.getEmail());
            customer.setZipcode(customerEntity.getZipcode());
            String name[] = customerEntity.getFullName().split(" ");
            customer.setFirstName(name[0]);
            customer.setLastName(name[1]);
            customer.setPhone(customerEntity.getPhone());
            customer.setAddress(customerEntity.getAddress());
            customer.setPassword(customerEntity.getPassword());
            customer.setState(customerEntity.getState());
            customerList.add(customer);
        }
        return customerList;
*/
    }

    @Override
    public String deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return "Customer deleted Successfully";
    }

    @Override
    public PageResponse<ShoppingDto> getAllShoppingData(int page, int size,String sortBy,String sortDirection) {
        String subUrl = url+"/getAllShoppingData?page={page}&size={size}&sortBy={sortBy}&sortDirection={sortDirection}";
        ResponseEntity<PageResponse<ShoppingDto>> response=null;
        /*response.getBody();*/
        try {



            response = restTemplate.exchange(
                    subUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<PageResponse<ShoppingDto>>() {
                    },
                    page,
                    size,
                    sortBy,
                    sortDirection
            );

        }
        catch(ResourceNotFoundException exception){
            throw new ResourceNotFoundException(exception.getMessage());
        }
        catch(Exception exception){
            throw exception;
        }
        return response.getBody();
    }

    @Override
    public String saveShoppingData(ShoppingDto shoppingDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ShoppingDto> entity = new HttpEntity<>(shoppingDto, headers);
        restTemplate.postForObject(url+"/saveShoppingData", entity, String.class);

        return "ShoppingData saved Successfully";
    }

    @Override
    public String updateShoppingData(ShoppingDto shoppingDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ShoppingDto> entity = new HttpEntity<>(shoppingDto, headers);
        restTemplate.put(url+"/updateShoppingData", entity);

        return "ShoppingData updated Successfully";
    }

    @Override
    public String deleteShoppingDataById(Long id) {
        restTemplate.delete(url+"/deleteShoppingDataById/"+id);
        return "deleted successfully";
    }

    @Override
    public Optional<CustomerEntity> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public String updateCustomer(Customer customer) {
        if (customer.getId() != null) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setFullName(customer.getFirstName() + " " + customer.getLastName());
            customerEntity.setEmail(customer.getEmail());
            customerEntity.setPhone(customer.getPhone());
            customerEntity.setAddress(customer.getAddress());
            customerEntity.setPassword(customer.getPassword());
            customerEntity.setCountry(customer.getCountry());
            customerEntity.setCity(customer.getCity());
            customerEntity.setState(customer.getState());
            customerEntity.setZipcode(customer.getZipcode());
            customerEntity.setId(customer.getId());
            customerEntity.setRole(customer.getRole().toUpperCase());
            customerRepository.save(customerEntity);
        }
        return "Customer updated Successfully";
    }
}
