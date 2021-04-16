package com.project.givemehand.controller;
import com.project.givemehand.models.entity.Adresse;
import com.project.givemehand.models.entity.Role;
import com.project.givemehand.models.entity.ERole;

import com.project.givemehand.models.entity.User;
import com.project.givemehand.payload.MessageResponse;
import com.project.givemehand.payload.request.LoginRequest;
import com.project.givemehand.payload.request.UserRequest;

import com.project.givemehand.repository.RoleRepository;
import com.project.givemehand.repository.UserRepository;
import com.project.givemehand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;

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




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth")
public class UserServiceController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;
    Set<Role> roles = new HashSet<>();

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {


        if (userRepository.existsByEmail(loginRequest.getEmail())==false) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email incorrecte !"));
        }
        else {
            User userDB = service.findByEmail(loginRequest.getEmail());

                if ( userDB.getPassword().equals(loginRequest.getPassword())){
                    return ResponseEntity.ok(new MessageResponse("Connected"));


                }
                else {
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Incorrect Password !"));


                }

        }

    }



    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> userRegister(@RequestBody UserRequest userRequest ) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        int medailles = 0;
        Adresse add = new Adresse(userRequest.getStreet(),userRequest.getZip(), userRequest.getCity(), userRequest.getCountry());

        User user = new User(userRequest.getFirstName(),userRequest.getLastName(),
                userRequest.getPassword(),userRequest.getEmail(),userRequest.getPhoneNumber(),medailles);

        System.out.println("********** User ************ " + "Firstname" + userRequest.getFirstName()

        + "Lastname" + userRequest.getLastName()
        );
        System.out.println("********** User ************ " + "Zip" + add.toString()

        );
              //  encoder.encode( userRequest.getPassword()));


        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_PARTICULIER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));


        roles.add(userRole);
        user.setRoles(roles);
        user.setAdresse(add);
        service.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));


    }



    @PutMapping("/updateUser/{email}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserRequest user)
    {
        return service.updateUser(id,user);
    }

    @RequestMapping(value = "/findUserByemail/{email}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findByEmail(@PathVariable ("email") String email ) throws com.projet.korector.Exceptions.ResourceNotFoundException {

        return service.findUserByEmail(email);
    }
     public User findById(Long userID)
    {

        return service.findById(userID);
    }
  /*
    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable ("userId") Long userId) {
        service.deleteById(userId);

    }
    @RequestMapping(value = "/findAllStudent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List <User> >  findAllStudent()
    {
        List<User> allUsers = userRepository.findAll();
        ArrayList<User> studentsResponse = new ArrayList<User>();
        for(User user : allUsers){
            for(Role role : user.getRoles()){
                if(role.getName().toString() == ("ROLE_ETUDIANT")){
                    // usersResponse[allUsers.get()] = new List<User>();
                    studentsResponse.add(user) ;
                }
            }

        }
        return new  ResponseEntity <List <User> >(studentsResponse, HttpStatus.OK);
    }
    @RequestMapping(value = "/findAllProf", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<List <User> > findAllProf()
    {
        List<User> allProfs = userRepository.findAll();
        ArrayList<User> profsResponse = new ArrayList<User>();
        for(User user : allProfs){
            for(Role role : user.getRoles()){
                if(role.getName().toString() == ("ROLE_ENSEIGNANT")){
                    profsResponse.add(user) ;
                }
            }

        }
        return new  ResponseEntity <List <User> >(profsResponse, HttpStatus.OK);

    }
    @RequestMapping(value = "/findAllClasses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllClasses()
    {
        Integer nb_classes = 0;
        return ResponseEntity.ok(new MessageResponse("Test"));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public User findById(Long userID) {

        return service.findById(userID);
    }
    @RequestMapping(value = "/findByUsername", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public Optional<User> findByUsername(String username) {
        return service.findByUsername(username);

    }
*/


}


