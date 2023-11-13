package org.project.backapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.project.backapi.dto.request.AuthRequest;
import org.project.backapi.dto.response.AuthResponse;
import org.project.backapi.dto.request.RegisterRequest;
import org.project.backapi.dto.response.UserInfoResponse;
import org.project.backapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfoResponse> getUserInfo(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.split(" ")[1].trim();
        return new ResponseEntity<>(authService.getUserInfo(jwt), HttpStatus.OK);
    }

}
