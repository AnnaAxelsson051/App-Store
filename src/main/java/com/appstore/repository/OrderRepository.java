package com.appstore.repository;

import com.appstore.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByHandled(boolean isHandled);
    Orders getProductOrderById(Long id);
}