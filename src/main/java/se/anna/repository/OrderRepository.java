package se.anna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.anna.model.Orders;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByHandled(boolean isHandled);
    Orders getProductOrderById(Long id);
}