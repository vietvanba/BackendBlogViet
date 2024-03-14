package com.blog.authservice.services;

import com.blog.authservice.entities.Account;
import com.blog.authservice.exceptions.CannotSave;
import com.blog.authservice.payload.AuthenticationRequest;
import com.blog.authservice.payload.AuthenticationResponse;
import com.blog.authservice.payload.RegisterRequest;
import com.blog.authservice.payload.RegisterResponse;
import com.blog.authservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    public RegisterResponse register(RegisterRequest request) {
        var user = Account.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .birthday(request.getBirthday())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .personalAddress(null)
                .createdTime(new Date())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .avatar(request.getAvatar())
                .active(request.getActive())
                .noteActive(request.getNoteActive())
                .build();
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CannotSave("Email already exists.\n" +
                    "Please choose another email or reset your password");
        }
        if (userRepository.existsById(user.getUsername())) {
            throw new CannotSave("The username already exists. \nPlease choose another username");
        }
        if (userRepository.existsById(user.getPhoneNumber())) {
            throw new CannotSave("Phone number already exists. \nPlease choose another phone number");
        }
        try {
            userRepository.save(user);
            return RegisterResponse.builder()
                    .username(user.getUsername())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .birthday(user.getBirthday())
                    .avatar(user.getAvatar())
                    .email(user.getEmail())
                    .active(user.getActive())
                    .noteActive(user.getNoteActive())
                    .phoneNumber(user.getPhoneNumber())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .build();
        } catch (Exception e) {
            throw new CannotSave(e.getMessage());
        }
    }

//    public AuthenticationResponse signIn(AuthenticationRequest request) {
//        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
//
//    }
}
