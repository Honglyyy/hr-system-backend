package com.ly.hrsystembackend.controller;

import com.ly.hrsystembackend.dto.AuthRequest;
import com.ly.hrsystembackend.dto.UserRequest;
import com.ly.hrsystembackend.service.AuthService;
import com.ly.hrsystembackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @PreAuthorize("hasAllRoles('ADMIN', 'HR')")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

}
