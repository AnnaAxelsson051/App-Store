package com.appstore.repository;

import com.appstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public boolean existsByUsernameAndPassword(String username, String password);

    public Customer getCustomersByUsername(String username);
}