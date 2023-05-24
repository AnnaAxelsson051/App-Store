package se.anna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.anna.model.Orders;
import se.anna.service.OrderService;

import java.util.List;

@Controller
public class OrderController {

    //Handles viewing orders for admin and marking them as handeled
    @Autowired
    OrderService orderService;

    @GetMapping("/unhandled")
    public String displayUnhandledOrders(Model m) {
        List<Orders> unhandledOrders = orderService.findUnhandledOrder(false);
        m.addAttribute("ordersUnhandled", unhandledOrders);
        return "orders_unhandled";
    }
    

}
