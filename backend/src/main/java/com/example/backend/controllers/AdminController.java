package com.example.backend.controllers;

import com.example.backend.model.Product;
import com.example.backend.model.Role;
import com.example.backend.model.Users;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // ----------------------- PRODUITS -----------------------

    // Liste tous les produits
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Ajouter un produit
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Modifier un produit
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Produit non trouvé !");
        }
        Product product = optionalProduct.get();
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(updatedProduct.getCategory());
        product.setStockQuantity(updatedProduct.getStockQuantity());
        product.setLienImage(updatedProduct.getLienImage());

        return productRepository.save(product);
    }

    // Supprimer un produit
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "Produit supprimé !";
    }

    // ----------------------- UTILISATEURS -----------------------

    // Liste tous les utilisateurs
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Modifier rôle ou info utilisateur
    @PutMapping("/users/{id}/role")
    public Users updateUserRole(@PathVariable Long id, @RequestParam String roleName) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé !"));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé !"));

        user.setRoles(List.of(role));
        return userRepository.save(user);
    }

}
