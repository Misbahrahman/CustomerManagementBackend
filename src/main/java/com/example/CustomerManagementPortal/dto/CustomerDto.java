package com.example.CustomerManagementPortal.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    String first_name;
    String last_name;
    String street;
    String address;
    String city;
    String state;
    String email;
    String phone;
}
