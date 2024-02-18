package com.example.CustomerManagementPortal.repository;

import com.example.CustomerManagementPortal.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepoforAdd extends JpaRepository<Customer,Integer> {


    List<Customer> findByEmail(String email);
}
