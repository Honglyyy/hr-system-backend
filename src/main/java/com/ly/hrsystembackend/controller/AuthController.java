package com.ly.hrsystembackend.controller;

import com.ly.hrsystembackend.annotation.CurrentUser;
import com.ly.hrsystembackend.dto.authdto.AuthRequest;
import com.ly.hrsystembackend.dto.userdto.UserChangePasswordRequest;
import com.ly.hrsystembackend.dto.userdto.UserRequest;
import com.ly.hrsystembackend.dto.userdto.UserResponse;
import com.ly.hrsystembackend.model.User;
import com.ly.hrsystembackend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @PostMapping("/change-password")
    public ResponseEntity<UserResponse> changePassword(@Valid @RequestBody UserChangePasswordRequest userChangePasswordRequest, @CurrentUser User currentUser) {
        return ResponseEntity.ok(authService.changePassword(currentUser, userChangePasswordRequest));
    }
}
