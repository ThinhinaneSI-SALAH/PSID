package com.project.givemehand.services;

import com.project.givemehand.models.entity.*;
import com.project.givemehand.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
/**
 * Cette classe contient les services de la demande
 */
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    public List<Demande> getAllServiceRequest() {
        return  requestRepository.findAll();
    }

    public Demande getServiceRequest(long id) {
        return  requestRepository.getOne(id);
    }

    public ResponseEntity<Demande> addRequestService(Demande demande)
    {
        Demande createdDemande = this.requestRepository.save(demande);

        return new ResponseEntity<Demande>(this.requestRepository.saveAndFlush(createdDemande), HttpStatus.OK);
    }

    public void deleteServiceRequest(Long requestId) {
        Demande deletedRequest=this.requestRepository.findById(requestId).get();
        boolean isPresent = this.requestRepository.findById(requestId).isPresent();
        if(isPresent) {
            this.requestRepository.deleteById(requestId);
        }
    }

    public Demande getServiceRequest(Long id) {
        Demande demande = this.requestRepository.findById(id).get();
        return demande;
    }

    public List<Demande> filterRequest(Filtre f) {
        List<Demande>  demandes = requestRepository.findAll();
        List<Demande> demandesRetenues= new ArrayList<Demande>();

        for (Demande req : demandes){
            String statRequest = req.getStatut();
            Statut  statutFilter = f.getStatut();
            Date date = req.getDateDemande();
            Date dateFilter = f.getDateFiltre();
            int medaillesReq = req.getOffre().getNbMedailles();
            int medaillesFilter = f.getMedailles();

            if( statRequest.toUpperCase().equals(statutFilter.toString().toUpperCase()) && ( medaillesReq == medaillesFilter)
                    && ( date.before(dateFilter)|| date.equals(dateFilter)))
            {
               demandesRetenues.add(req);

            }
        }

        return demandesRetenues;

    }

    public ResponseEntity<Demande> updateRequestService(Long id, Demande demande) {
        Optional<Demande> requestServiceData = requestRepository.findById(id);

        if (requestServiceData .isPresent()) {
            Demande _request = requestServiceData .get();
            _request.setStatut(Statut.valueOf(demande.getStatut()));
            System.out.println("The request service is updated successfully !");
            return new ResponseEntity<>(requestRepository.save(_request), HttpStatus.OK);
        } else {
            System.out.println("Error, the request service is not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
