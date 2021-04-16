package com.project.givemehand.services;


import com.project.givemehand.models.entity.Demande;
import com.project.givemehand.models.entity.Adresse;
import com.project.givemehand.models.entity.User;
import com.project.givemehand.payload.MessageResponse;
import com.project.givemehand.payload.request.UserRequest;
import com.project.givemehand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.Console;
import java.util.List;

import java.util.Set;
import java.util.Optional;


/**
 * Contient les services du User
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;


    public Set<Demande> getServiceRequestByUserId(Long id_user) {
        Set<Demande> demandes =  this.userRepository.findById(id_user).get().getDemandes();
        return demandes;
    }


    public User saveUser(User user) {
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

    public ResponseEntity<User> findUserByEmail(String email) {

       User user  = userRepository.findByEmail(email).get();
        return ResponseEntity.ok().body(user);
       // return userRepository.findByEmail(email).get();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }


    public ResponseEntity<User> updateUser(String email, UserRequest newUser)
    {
       User _user = userRepository.findByEmail(email).get();

            _user.setFirstName(newUser.getFirstName());
            _user.setLastName(newUser.getLastName());
            _user.setPassword(newUser.getPassword());
            _user.setPhoneNumber(newUser.getPhoneNumber());

           /* System.out.println("Adresse ancien" + _user.getAdresse().toString());

           // _user.setAdresse(new Adresse(newU));

            _user.getAdresse().setCity("TEST");
        _user.getAdresse().setZip(newUser.getZip());
        _user.getAdresse().setCountry(newUser.getCountry());
        _user.getAdresse().setStreet(newUser.getStreet());
        System.out.println("Nouvelle addresse user" + newUser.getCity()

        + newUser.getCountry() + newUser.getStreet() + newUser.getZip()); */



        System.out.println("La mise à jours du user a bien été prise en compte!");
            return new ResponseEntity<User>(userRepository.save(_user), HttpStatus.OK);
       /* } else {
            System.out.println("Erreur lors de la mise à jours du critère !");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } */
        }
    public User findUserById(Long id){

        return userRepository.findById(id).get();

      //  return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

}
