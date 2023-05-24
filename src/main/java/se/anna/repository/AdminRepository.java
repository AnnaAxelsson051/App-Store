package se.anna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.anna.model.Admin;

import java.util.List;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByUsernameAndPassword(String username, String password);

    Admin getAdminByUsername(String username);

}
