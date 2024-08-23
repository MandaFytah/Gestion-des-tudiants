package com.memoire.Gestion_des_etudiants.DAO;

import com.memoire.Gestion_des_etudiants.entites.User;
import com.memoire.Gestion_des_etudiants.entites.bref.UserNonDetaille;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByEmailId(@Param("email") String email);
    public List<UserNonDetaille> loadUserNonDetaille();
}
