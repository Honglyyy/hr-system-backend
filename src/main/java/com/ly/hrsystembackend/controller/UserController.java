package com.ly.hrsystembackend.controller;

import com.ly.hrsystembackend.dto.userDto.UserRequest;
import com.ly.hrsystembackend.dto.userDto.UserResponse;
import com.ly.hrsystembackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public ResponseEntity<Page<UserResponse>> getAllUser(
            @PageableDefault(size = 5, page = 0, sort = "createdAt")
            Pageable pageable) {
        return ResponseEntity.ok(userService.getUsers(pageable));
    }
}
