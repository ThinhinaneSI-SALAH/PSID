package com.project.givemehand.services;

import com.project.givemehand.models.User;
import com.project.givemehand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> fetchAll() {
        return null;
    }
}
