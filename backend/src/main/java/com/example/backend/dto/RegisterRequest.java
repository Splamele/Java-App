package com.example.backend.dto;

import com.example.backend.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String role;
}
