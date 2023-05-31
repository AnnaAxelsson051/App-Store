package com.appstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    @Min(0)
    private double price;

    @Column(name = "description")
    private String description;

    //@Column(name = "image")

    public Product() {
    }

    public Product(String name, String category, double price) {
        this.name=name;
        this.category=category;
        this.price=price;
    }

    public Product(String name, String category, double price, String description) {
        this.name=name;
        this.category=category;
        this.price=price;
        this.description=description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
