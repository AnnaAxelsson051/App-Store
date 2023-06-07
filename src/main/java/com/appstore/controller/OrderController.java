package com.appstore.controller;

import com.appstore.model.Orders;
import com.appstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    //Class handles admin viewing orders and marking them as handeled
    @Autowired
    OrderService orderService;

    @GetMapping("/unhandled")
    public String displayUnhandledOrders(Model model) {
        List<Orders> unhandledOrders = orderService.findUnhandledOrder(false);
        model.addAttribute("ordersUnhandled", unhandledOrders);
        return "orders_unhandled";
    }
    @GetMapping("/handled")
    public String displayHandledOrders(Model model) {
        List<Orders> unhandledOrders = orderService.findUnhandledOrder(true);
        model.addAttribute("ordersHandled", unhandledOrders);
        return "orders_handled";
    }
    @PostMapping("/markOrderHandled")
    public String setOrderAsHandled(@RequestParam Long id, Model model){
        Orders order = orderService.getOrderById(id);
        order.setHandled(true);
        orderService.saveOrder(order);
        model.addAttribute("orderHandled", order.getId());
        return "order_set_handled";
    }
}
