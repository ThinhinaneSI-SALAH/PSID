package com.project.givemehand.controller;

import com.project.givemehand.models.entity.Demande;
import com.project.givemehand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")

/**
 * Elle represente le controleur de la classe User Service
 */
public class UserServiceController {


    @Autowired
    private UserService service;

    @RequestMapping(path ="/mesdemandes/{id_user}", method = RequestMethod.GET)
    public Set<Demande> getServiceRequestByUserId(@PathVariable Long id_user)
    {
        return service.getServiceRequestByUserId(id_user);
    }
}
