
package com.memoire.Gestion_des_etudiants.DAO;

import com.memoire.Gestion_des_etudiants.entites.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenceRepository extends CrudRepository<Presence, Long> {
}