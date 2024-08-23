package com.memoire.Gestion_des_etudiants.DAO;

import com.memoire.Gestion_des_etudiants.entites.PresenceDemarre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemarrePresenceRepositoryInt extends CrudRepository<PresenceDemarre,Long> {
}
