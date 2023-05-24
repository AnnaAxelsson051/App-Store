package se.anna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.anna.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public boolean existsByUsernameAndPassword(String username, String password);

    public Customer getCustomersByUsername(String username);
}