package se.anna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.anna.model.Customer;
import se.anna.repository.CustomerRepository;
import se.anna.repository.OrderRepository;

@Service
@SessionScope
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    Customer selectedCustomer;
    String selectedCategory = "all";


    //If a customer exists return it otherwise create a new user
    public Customer getAuthenticatedCustomer(String username, String password) {
        if (customerRepository.existsByUsernameAndPassword(username, password)) {
            selectedCustomer = customerRepository.getCustomersByUsername(username);
        } else {
            selectedCustomer = customerRepository.save(new Customer(username, password));
        }
        return selectedCustomer;
    }

    //Getting the category that was selected
    public String getSelectedCategory() {
        return this.selectedCategory;
    }

}
