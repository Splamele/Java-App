package com.example.backend.services;

import com.example.backend.model.Role;
import com.example.backend.model.Users;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Users registerUser(String email, String username, String password, String roleName) {
        if (usersRepository.existsByEmail(email)) {
            throw new RuntimeException("Email déjà utilisé");
        }

        Users user = new Users();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);

        // Récupérer le rôle depuis la base
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rôle " + roleName + " introuvable"));

        user.setRoles(List.of(role));

        return usersRepository.save(user);
    }
}
