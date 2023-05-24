package se.anna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.anna.model.Admin;
import se.anna.repository.AdminRepository;


@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;


    public boolean authenticateAdminByNameAndPassword(String username, String password) {
        return adminRepository.existsByUsernameAndPassword(username, password);
    }


    public Admin getAdmin(String username){
        return adminRepository.getAdminByUsername(username);
    }

}