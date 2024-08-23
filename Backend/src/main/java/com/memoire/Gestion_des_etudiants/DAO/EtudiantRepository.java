package com.memoire.Gestion_des_etudiants.DAO;
import com.memoire.Gestion_des_etudiants.entites.Etudiant;
import com.memoire.Gestion_des_etudiants.DAO.mapping.EtudiantColonneMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository//utilisé pour les classes qui interagissent avec la base de données
public class EtudiantRepository implements EtudiantRepositoryInterface {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Etudiant> loadEtudiant() {
        String sql="SELECT * FROM etudiants";

       List<Etudiant> etudiantList= jdbcTemplate.query(sql, new EtudiantColonneMapping());
    return etudiantList;
    }

    @Override
    public Optional<Etudiant> findByEmailId(String email) {
        String sql = "SELECT * FROM etudiants WHERE email = ?";
        try {
            Etudiant etudiant = jdbcTemplate.queryForObject(sql, new Object[]{email}, new EtudiantColonneMapping());
            return Optional.of(etudiant);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public <S extends Etudiant> S createEtudiant(S entity) {
        String sql="INSERT INTO etudiants (matricule," +
                " nom, prenom, adresse, dateNaissance, lieu, tel, email, classe, nomDepartement, annee," +
                " photo) VALUES(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        //expression lambda
        jdbcTemplate.update(connection->{
            PreparedStatement ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,entity.getMatricule());
            ps.setString(2,entity.getNom());
            ps.setString(3,entity.getPrenom());
            ps.setString(4,entity.getAdresse());
            ps.setString(5,entity.getDateNaissance().toString());
            ps.setString(6,entity.getLieu());
            ps.setString(7,entity.getTel());
            ps.setString(8,entity.getEmail());
            ps.setString(9,entity.getClasse());
            ps.setString(10,entity.getNomDepartement());
            ps.setString(11,entity.getAnnee());
            ps.setBytes(12,entity.getPhoto());
            return  ps;
        },keyHolder);
        entity.setIdEtudiant(keyHolder.getKey().longValue());
    return entity;
    }

    //update!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public <S extends Etudiant> S save(S entity) {
        String sql="UPDATE etudiants SET matricule= ?, " +
                "nom=?, prenom=?, adresse= ?, dateNaissance= ?,lieu= ?, tel= ?, email =?, classe= ?, nomDepartement= ?, " +
                "annee= ?, filename= ?, photo= ?, role=? WHERE idetudiant= ?";
        jdbcTemplate.update(sql,entity.getMatricule(),
                entity.getNom(),
                entity.getPrenom(),
                entity.getAdresse(),
                entity.getDateNaissance(),
                entity.getLieu(),
                entity.getTel(),
                entity.getEmail(),
                entity.getClasse(),
                entity.getDepartement(),
                entity.getAnnee(),
                entity.getPhoto(),
                entity.getIdEtudiant());
        return entity;
    }

    @Override
    public <S extends Etudiant> List<S> saveAll(Iterable<S> entities) {
        List<S> result=new ArrayList<>();
        for (S entity:entities){
            createEtudiant(entity);
            result.add(entity);
        }
        return  result;
    }

    @Override
    public boolean existsById(Float aFloat) {
        return false;
    }

    @Override
    public Iterable<Etudiant> findAll() {
        return null;
    }

    @Override
    public Iterable<Etudiant> findAllById(Iterable<Float> floats) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Float aFloat) {
        String sql="DELETE FROM etudiants WHERE idetudiant= ?";
        jdbcTemplate.update(sql,aFloat);

    }

    @Override
    public void delete(Etudiant entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Float> floats) {

    }

    @Override
    public void deleteAll(Iterable<? extends Etudiant> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<Etudiant> findById(Float aFloat) {
        String sql="SELECT * FROM etudiants WHERE idetudiant= ?";
        try{
            Etudiant etudiant=jdbcTemplate.queryForObject(sql,new Object[]{aFloat},new EtudiantColonneMapping());

            return Optional.ofNullable(etudiant);
        }catch (Exception e){
            return Optional.empty();
        }
    }


}
