package com.example.CustomerManagementPortal.service;


import com.example.CustomerManagementPortal.dto.CustomerDto;
import com.example.CustomerManagementPortal.exception.CustomException;
import com.example.CustomerManagementPortal.model.Customer;
import com.example.CustomerManagementPortal.repository.CustomerRepoForGet;
import com.example.CustomerManagementPortal.transformer.CustomerTransformer;
import com.sun.jdi.Mirror;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.CustomerManagementPortal.repository.CustomerRepoforAdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepoforAdd customerRepoforAdd;

    @Autowired
    CustomerRepoForGet customerRepoForGet;


    public String addCustomer(CustomerDto customerDto) {
        Customer customer = CustomerTransformer.getCustomerFromCustomerDto(customerDto);

        //check for Duplicates(******EMAIL ID******)
        String email = customer.getEmail();
        if(customerRepoforAdd.findByEmail(email) != null) System.out.println("Already Exists");
        Customer savedCustomer = customerRepoforAdd.save(customer);
        return savedCustomer.getFirst_name() + " " + savedCustomer.getLast_name() + " has been saved in Database";
    }

    //One single Service for sorting , searching And paging
    public List<Customer> getAll(Integer pageNoParam,
                                    Integer pageQuantParam ,
                                    String sortParam ,
                                    String searchParam) {

        PageRequest pageRequest = PageRequest.of(pageNoParam, pageQuantParam , Sort.by(sortParam).ascending());
        Page<Customer> response = customerRepoForGet.find(searchParam ,pageRequest);
        List<Customer> list = response.getContent();
        return list;
    }

    public String deleteCustomer(Integer id) {
        Optional<Customer> response = customerRepoforAdd.findById(id);
        if(response.isEmpty())throw new CustomException("No Such Customer Found");
        Customer customer = response.get();
        customerRepoforAdd.delete(customer);
        return customer.getFirst_name() + " " + customer.getLast_name() + " Has been Deleted";
    }

    public Customer getCustomerById(int id) {
        Optional<Customer> response = customerRepoforAdd.findById(id);
        if(response.isEmpty())throw new CustomException("Invalid ID");
        return response.get();

    }


    public void merge(List<Object> customers) {

    }

    public String deleteAll() {
        customerRepoforAdd.deleteAll();
        return "Khallas";
    }
}
