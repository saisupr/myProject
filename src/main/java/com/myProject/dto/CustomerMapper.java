package com.myProject.dto;

import com.myProject.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
//this is customer mapper
    Customer toDto(CustomerEntity customerEntity);

    @Mapping(target = "password", expression = "java(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(customer.getPassword()))")
    CustomerEntity toEntity(Customer customer);
}
