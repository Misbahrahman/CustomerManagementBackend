package com.example.CustomerManagementPortal.transformer;

import com.example.CustomerManagementPortal.dto.CustomerDto;
import com.example.CustomerManagementPortal.dto.ServerObj;
import com.example.CustomerManagementPortal.model.Customer;

public class CustomerTransformer {

    public static Customer getCustomerFromCustomerDto(CustomerDto customerDto){
        return Customer.builder()
                .first_name(customerDto.getFirst_name())
                .last_name(customerDto.getLast_name())
                .address(customerDto.getAddress())
                .phone(customerDto.getPhone())
                .state(customerDto.getState())
                .city(customerDto.getCity())
                .email(customerDto.getEmail())
                .street(customerDto.getStreet())
                .build();
    }

    public static CustomerDto getCustomerDtoFromCustomer(Customer customer){
        return CustomerDto.builder()
                .address(customer.getAddress())
                .last_name(customer.getLast_name())
                .first_name(customer.getFirst_name())
                .state(customer.getState())
                .street(customer.getStreet())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .city(customer.getCity())
                .build();
    }

    public static CustomerDto getCustomerDtoFromServerObj(ServerObj serverObj){
        return CustomerDto.builder()
                .address(serverObj.getAddress())
                .last_name(serverObj.getLast_name())
                .first_name(serverObj.getFirst_name())
                .state(serverObj.getState())
                .street(serverObj.getStreet())
                .phone(serverObj.getPhone())
                .email(serverObj.getEmail())
                .city(serverObj.getCity())
                .build();
    }



}
