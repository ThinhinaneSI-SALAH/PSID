package com.project.givemehand.services;

import com.project.givemehand.models.entity.Demande;
import com.project.givemehand.models.entity.User;
import com.project.givemehand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Set;

@Service
/**
 * Contient les services du User
 */
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    public Set<Demande> getServiceRequestByUserId(Long id_user) {
        Set<Demande> demandes =  this.userRepository.findById(id_user).get().getDemandes();
        return demandes;
    }


}
