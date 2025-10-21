package com.example.expensesplitter.controller;

import com.example.expensesplitter.dto.UserDto;
import com.example.expensesplitter.model.User;
import com.example.expensesplitter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> list() {
        return userService.listAll().stream().map(u -> new UserDto(u.getId(), u.getName(), u.getEmail(), u.getAvatarUrl())).collect(Collectors.toList());
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        User u = new User();
        u.setName(dto.name);
        u.setEmail(dto.email);
        u.setAvatarUrl(dto.avatarUrl);
        User saved = userService.create(u);
        return new UserDto(saved.getId(), saved.getName(), saved.getEmail(), saved.getAvatarUrl());
    }
}
