package com.appstore.repository;

import com.appstore.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByUsernameAndPassword(String username, String password);

    Admin getAdminByUsername(String username);

}
