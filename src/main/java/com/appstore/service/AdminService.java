package com.appstore.service;

import com.appstore.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appstore.repository.AdminRepository;


@Service
public class AdminService {

    // Class handles authentication of admin
    @Autowired
    AdminRepository adminRepository;

    public boolean authenticateAdminByNameAndPassword(String username, String password) {
        return adminRepository.existsByUsernameAndPassword(username, password);
    }

    public Admin getAdmin(String username){
        return adminRepository.getAdminByUsername(username);
    }

}