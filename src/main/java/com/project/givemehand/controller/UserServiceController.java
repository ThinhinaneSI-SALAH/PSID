package com.project.givemehand.controller;

import com.project.givemehand.models.entity.User;
import com.project.givemehand.services.OffreService;
import com.project.givemehand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Elle represente le controleur de la classe User Service
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api_user")
public class UserServiceController {
    @Autowired
    private UserService service;
    @RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(Long userID)
    {

        return service.findById(userID);
    }
}
