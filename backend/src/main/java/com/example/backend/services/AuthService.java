package com.example.backend.services;

import com.example.backend.model.Role;
import com.example.backend.model.Users;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AuthService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Users registerUser(String email, String username, String password) {
        if (usersRepository.existsByEmail(email)) {
            throw new RuntimeException("Email déjà utilisé !");
        }

        Users user = new Users();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        // On attribue le rôle USER par défaut
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Rôle USER introuvable"));
        HashSet<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles.stream().toList());

        return usersRepository.save(user);
    }
}
