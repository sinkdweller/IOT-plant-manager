package com.example.ecom.security.service;

import com.example.ecom.dtos.LoginUserDto;
import com.example.ecom.dtos.RegisterUserDto;
import com.example.ecom.entity.User;
import com.example.ecom.repo.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationService {
    private final UserRepo userRepo;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepo userRepo,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
                user.setUsername(input.getUsername());
                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepo.save(user);
    }

    public User authenticate(LoginUserDto input) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    input.getUsername(), // <--- Change this
                    input.getPassword()
            )
    );

    return userRepo.findByUsername(input.getUsername()) // <--- Change this
                .orElseThrow();
    }
}