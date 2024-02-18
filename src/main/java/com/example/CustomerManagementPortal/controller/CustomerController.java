package com.example.CustomerManagementPortal.controller;


import com.example.CustomerManagementPortal.dto.CustomerDto;
import com.example.CustomerManagementPortal.dto.ServerObj;
import com.example.CustomerManagementPortal.model.Customer;
import com.example.CustomerManagementPortal.service.CustomerService;
import com.example.CustomerManagementPortal.service.RemoteApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    RemoteApiService remoteApiService;

    @PostMapping("/add")
    ResponseEntity addCustomer(@RequestBody CustomerDto customerDto){
        try {
            String response = customerService.addCustomer(customerDto);
            return new ResponseEntity(response , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getById")
    ResponseEntity getCustomerById(@RequestParam int id){
        try {
            Customer customer = customerService.getCustomerById(id);
            return new ResponseEntity(customer , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/getAll")
    ResponseEntity getAll(@RequestParam(defaultValue = "0") Integer pageNoParam,
                          @RequestParam(defaultValue = "10") Integer pageQuantParam,
                          @RequestParam(defaultValue = "first_name") String sortParam,
                          @RequestParam(defaultValue = "") String searchParam){
        try {
            List<Customer> list = customerService.getAll(pageNoParam , pageQuantParam  , sortParam , searchParam);
            return new ResponseEntity(list , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete")
    ResponseEntity deleteCustomer(@RequestParam Integer id){
        try {
            String response = customerService.deleteCustomer(id);
            return new ResponseEntity(response , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/deleteAll")
    ResponseEntity deleteAll(){
        try {
            String response = customerService.deleteAll();
            return new ResponseEntity(response , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }


    public static final String loginurl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
    public static final String customersApi = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
    @GetMapping("/sync")
    public void getToken(){
        String requestBody = "{ \"login_id\": \"test@sunbasedata.com\", \"password\": \"Test@123\" }";
        String token = remoteApiService.callApi(loginurl, requestBody);
        String acessToken = token.substring(19, token.length()-3);
        remoteApiService.getCustomers(acessToken, customersApi);
//       todo run getAll Again in js

    }

}
