package org.project.backapi.service;

import lombok.RequiredArgsConstructor;
import org.project.backapi.domain.*;
import org.project.backapi.enums.UserRole;
import org.project.backapi.dto.request.AuthRequest;
import org.project.backapi.dto.response.AuthResponse;
import org.project.backapi.dto.request.RegisterRequest;
import org.project.backapi.dto.response.UserInfoResponse;
import org.project.backapi.exception.UserAuthenticationException;
import org.project.backapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        try {
            if (userRepository.existsByEmail(request.getEmail()))
                throw new UserAuthenticationException("User already exists!");
            User user = User.builder()
                    .email(request.getEmail())
                    .fullname(request.getFullname())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .pseudo(request.getPseudo())
                    .userRole(UserRole.valueOf(request.getRole().toUpperCase()))
                    .teacherSpeciality(request.getTeacherSpeciality())
                    .build();
            userRepository.save(user);
            var token = jwtService.generateToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        } catch (UserAuthenticationException e) {
            return AuthResponse.builder()
                    .token(e.getMessage())
                    .build();
        }
    }

public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public UserInfoResponse getUserInfo(String token) {
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(username).orElseThrow();
        return UserInfoResponse.builder()
                .email(user.getEmail())
                .fullname(user.getFullname())
                .pseudo(user.getPseudo())
                .id(user.getId())
                .build();
    }
}
