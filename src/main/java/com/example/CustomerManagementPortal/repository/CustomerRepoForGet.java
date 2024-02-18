package com.example.CustomerManagementPortal.repository;

import com.example.CustomerManagementPortal.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepoForGet extends PagingAndSortingRepository<Customer , Integer> {
    @Query(value = "SELECT * FROM Customer WHERE Customer.first_name LIKE %:key% OR Customer.last_name LIKE %:key% OR Customer.id = :key", nativeQuery = true)
    Page<Customer> find(@Param("key") String searchParam, PageRequest pageRequest);

}
