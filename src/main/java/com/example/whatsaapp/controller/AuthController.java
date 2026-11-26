package com.example.whatsaapp.controller;

import com.example.whatsaapp.dto.request.LoginRequest;
import com.example.whatsaapp.dto.request.RegisterRequest;
import com.example.whatsaapp.dto.response.LoginResponse;
import com.example.whatsaapp.entity.User;
import com.example.whatsaapp.repository.UserRepository;
import com.example.whatsaapp.security.CustomUserDetails;
import com.example.whatsaapp.security.JwtUtility;
import com.example.whatsaapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetails customUserDetails;
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody RegisterRequest request){
      return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest request){
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
          var user = customUserDetails.loadUserByUsername(request.email());
          var userId = userRepository.findByEmail(user.getUsername()).get().getUserId();

          String accessToken = jwtUtility.createAccessToken(user);
          String refreshToken = jwtUtility.createRefreshToken(user);
          return ResponseEntity.ok(new LoginResponse(accessToken,refreshToken,userId));
    }


}
