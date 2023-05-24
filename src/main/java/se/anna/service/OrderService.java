package se.anna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.anna.model.Orders;
import se.anna.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Orders> findUnhandledOrder(boolean isHandled) {
        return orderRepository.findByHandled(isHandled);
    }
    

}
