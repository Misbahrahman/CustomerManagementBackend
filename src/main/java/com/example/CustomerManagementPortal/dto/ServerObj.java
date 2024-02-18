package com.example.CustomerManagementPortal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ServerObj {

    String uuid;

    String first_name;

    String last_name;

    String street;

    String address;

    String city;

    String state;

    String email;

    String phone;
}
