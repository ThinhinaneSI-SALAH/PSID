package com.project.givemehand.services;


import com.project.givemehand.models.entity.Demande;
import com.project.givemehand.models.entity.Adresse;
import com.project.givemehand.models.entity.Offre;
import com.project.givemehand.models.entity.User;
import com.project.givemehand.payload.MessageResponse;
import com.project.givemehand.payload.request.UserRequest;
import com.project.givemehand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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

    /*public User updateUser(User user) {
        return userRepository.save(user);
    }
*/
    public User updateUser(User user) {
        user.setAdresse(user.getAdresse());
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
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }


    /*public ResponseEntity<User> updateUser(Long id, UserRequest newUser)
    {
       User _user = userRepository.findById(id).get();

        if (userRepository.existsByEmail(_user.getEmail())) {
            //User _user= userData.get();

            _user.setFirstName(newUser.getFirstName());
            _user.setLastName(newUser.getLastName());
            _user.setPassword(newUser.getPassword());
            _user.setPhoneNumber(newUser.getPhoneNumber());
            _user.setFirstName(newUser.getFirstName());
            Adresse add = new Adresse(newUser.getStreet(),newUser.getZip(),newUser.getCity(), newUser.getCountry());

            _user.setAdresse(add);



            System.out.println("La mise à jours du user a bien été prise en compte!");
            return new ResponseEntity<User>(userRepository.save(_user), HttpStatus.OK);
        } else {
            System.out.println("Erreur lors de la mise à jours du critère !");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    } */

        
        public User findUserById(Long id){

            return userRepository.findById(id).get();

        }

    public void deleteuser(long id) {
        this.userRepository.deleteById(id);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
