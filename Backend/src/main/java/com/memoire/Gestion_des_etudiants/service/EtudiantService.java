package com.memoire.Gestion_des_etudiants.service;

import com.memoire.Gestion_des_etudiants.entites.Etudiant;
import com.memoire.Gestion_des_etudiants.DAO.EtudiantRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepositoryInterface etudiantRepository;
    public Optional<Etudiant> findById(float id) {

        return etudiantRepository.findById(id);
    }

    public Iterable<Etudiant> getAll() {
        Iterable<Etudiant> etudiants=etudiantRepository.loadEtudiant();
        return etudiants ;
    }

    public int max() {
        return 0;
    }

    public int nbre() {
        return  0;
    }

    public <S extends Etudiant> S save(S entity) {
        return etudiantRepository.createEtudiant(entity);
    }

    public <S extends Etudiant> S update(S entity) {

        return etudiantRepository.save(entity);
    }

    public void delete(float id) {
        etudiantRepository.deleteById(id);
    }

    public ResponseEntity<Map<String, String>> login(Map<String, String> requestMap) {
        return null;
    }
}
