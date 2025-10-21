package com.example.expensesplitter.dto;

public class UserDto {
    public Long id;
    public String name;
    public String email;
    public String avatarUrl;

    public UserDto() {}

    public UserDto(Long id, String name, String email, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }
}
