package com.kodnest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(nullable = false, unique = true)
    private String categoryName;

 // Default Constructor
    public Category() {
        // TODO Auto-generated constructor stub
    }

    // Constructor with categoryId and categoryName
    public Category(Integer categoryId, String categoryName) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Constructor with categoryName only
    public Category(String categoryName) {
        super();
        this.categoryName = categoryName;
    }
    // Getters and Setters

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}