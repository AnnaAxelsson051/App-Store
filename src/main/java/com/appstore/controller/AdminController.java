package com.appstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.appstore.service.AdminService;

@Controller
public class AdminController {

    //Handels admin login

    @Autowired
    AdminService adminService;

    @GetMapping("/adminauth")
    public String authenticateAdmin() {
        return "admin_auth";
    }

    @PostMapping("/adminauth")
    public String authenticateAdmin(@RequestParam String username, @RequestParam String password, Model model){
        if (adminService.authenticateAdminByNameAndPassword(username, password)) {
            model.addAttribute("adminName",  adminService.getAdmin(username).getUsername());
            return "admin_home";
        } else {
            return "index";
        }
    }

    @GetMapping("/adminpage")
    public String displayAdminHomePage(){
        return "admin_home";
    }

}