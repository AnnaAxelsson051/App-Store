package com.appstore.service;

import com.appstore.model.Product;
import com.appstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    //The class handles both customer interaction with products in store
    // and admin adding/updating products

    /* Section handles customer interactions with products in store*/
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

    /*Section handles admin updating products*/

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

    public void updateProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            Product existingProduct = productRepository.findProductByName(product.getName()).get(0);
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setDescription(product.getDescription());
            productRepository.save(existingProduct);
        }
    }
}
