package se.anna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.anna.model.Product;
import se.anna.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    Product product;

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.findProductsByCategory(category);
    }

    public List<Product> getProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    public List<String> getCategories() {
        return productRepository.findAllCategories();
    }

    public List<Product> getProducts(String category) {
        if (category.equals("all")) {
            return getAllProducts();
        } else {
            return getProductByCategory(category);
        }
    }

    /*Admin updating products*/

    public void setProduct(Product product) {
        this.product = product;
    }

    public String SaveProductIfNew(Product product){
        if(productRepository.existsByName(product.getName())){
            updateProduct(product);
            return "Updated product, " + product.getName();
        } else {
            productRepository.save(product);
            return "Added product, " + product.getName();
        }
    }

    private void updateProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            Product existingProduct = productRepository.findProductByName(product.getName()).get(0);
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setDescription(product.getDescription());
            productRepository.save(existingProduct);
        }
    }
}
