package com.project.givemehand.services;

import com.project.givemehand.models.entity.User;
import com.project.givemehand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * Contient les services du User
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    public User saveUser(User user) {
        return userRepository.save(user);

    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }


    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User findById(Long userID) {
        return userRepository.findById(userID).get();
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



}
