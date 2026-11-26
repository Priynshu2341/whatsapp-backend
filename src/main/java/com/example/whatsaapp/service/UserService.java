package com.example.whatsaapp.service;

import com.example.whatsaapp.dto.request.RegisterRequest;
import com.example.whatsaapp.dto.response.UserResponse;
import com.example.whatsaapp.entity.User;
import com.example.whatsaapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public String createUser(RegisterRequest request){
            User user =  User
                .builder()
                .firstName(request.firstname())
                .lastName(request.lastname())
                .email(request.email())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(request.password()))
                .build();

              userRepository.save(user);
              return "User Created Successfully";

    }

}
