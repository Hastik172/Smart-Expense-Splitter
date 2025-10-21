package com.example.expensesplitter.service;

import com.example.expensesplitter.model.User;
import com.example.expensesplitter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User create(User u) {
        return userRepository.save(u);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
