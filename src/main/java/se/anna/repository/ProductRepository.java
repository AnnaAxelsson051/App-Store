package se.anna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.anna.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);
    @Query("SELECT DISTINCT p.category FROM Product p")
    public List<String> findAllCategories();

    public List<Product> findProductByName(String name);

    public List<Product> findProductsByCategory(String category);
}
