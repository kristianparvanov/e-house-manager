package com.ehousemanager.ehousemanager.controllers;

import com.ehousemanager.ehousemanager.dtos.users.CreateUserDto;
import com.ehousemanager.ehousemanager.dtos.users.UserDto;
import com.ehousemanager.ehousemanager.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Tag(name = "User")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create a user", description = "Returns a user.")
    @PostMapping("/create")
    public UserDto create(@Valid @RequestBody CreateUserDto request) {
        return userService.create(request);
    }

    @Operation(summary = "Get all users", description = "Returns a page of users.")
    @GetMapping
    public Page<UserDto> readAll(Pageable pageable) {
        return userService.readAll(pageable);
    }

    @Operation(summary = "Get a user by id", description = "Returns a user information by id.")
    @GetMapping("/{id}")
    public UserDto read(@PathVariable UUID id) {
        return userService.read(id);
    }

    @Operation(summary = "Get a user by email", description = "Returns a user")
    @GetMapping("/search-by-email")
    public UserDto searchByAddressLike(@RequestParam String email) {
        return userService.searchByEmail(email);
    }

    @Operation(summary = "Get a user by name like", description = "Returns a user")
    @GetMapping("/search-by-name")
    public Page<UserDto> searchByAddressLike(@RequestParam String name, Pageable pageable) {
        return userService.searchByNameLike(name, pageable);
    }

    @Operation(summary = "Update a user", description = "Returns a user.")
    @PostMapping("/{id}/update")
    public UserDto update(@PathVariable UUID id,
                          @Valid @RequestBody CreateUserDto request) {
        return userService.update(id, request);
    }
}
