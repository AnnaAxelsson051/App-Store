package com.appstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appstore.model.Orders;
import com.appstore.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    //Handles admin interacting with orders plust saving customer order
    @Autowired
    OrderRepository orderRepository;

    public List<Orders> findUnhandledOrder(boolean isHandled) {
        return orderRepository.findByHandled(isHandled);
    }


    public Orders getOrderById(Long id) {
        return orderRepository.getProductOrderById(id);
    }

    public void saveOrder(Orders order){
        orderRepository.save(order);
    }

}
