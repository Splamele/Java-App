package com.example.backend.controllers;

import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // ----------------------- LISTER LES PRODUITS -----------------------
    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).getContent();
    }

    // ----------------------- DÉTAIL D’UN PRODUIT -----------------------
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé !"));
    }

    // ----------------------- RECHERCHE PAR NOM OU CATÉGORIE -----------------------
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
    ) {
        if (name != null && category != null) {
            return productRepository.findByNameContainingAndCategoryContainingIgnoreCase(name, category);
        } else if (name != null) {
            return productRepository.findByNameContainingIgnoreCase(name);
        } else if (category != null) {
            return productRepository.findByCategoryContainingIgnoreCase(category);
        } else {
            return productRepository.findAll();
        }
    }
}
