package com.example.backend.repository;

import com.example.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Recherche par nom (insensible à la casse)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Recherche par catégorie (insensible à la casse)
    List<Product> findByCategoryContainingIgnoreCase(String category);

    // Recherche par nom ET catégorie
    List<Product> findByNameContainingAndCategoryContainingIgnoreCase(String name, String category);
}
