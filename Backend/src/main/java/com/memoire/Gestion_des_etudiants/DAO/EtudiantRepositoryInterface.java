package com.memoire.Gestion_des_etudiants.DAO;

import com.memoire.Gestion_des_etudiants.entites.Etudiant;
import com.memoire.Gestion_des_etudiants.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepositoryInterface extends CrudRepository<Etudiant, Float> {
    List<Etudiant> loadEtudiant();
    Optional<Etudiant> findByEmailId(@Param("email") String email);

    <S extends Etudiant> S createEtudiant(S entity);

}
