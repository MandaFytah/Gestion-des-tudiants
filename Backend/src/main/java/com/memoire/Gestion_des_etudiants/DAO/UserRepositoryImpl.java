package com.memoire.Gestion_des_etudiants.DAO;

import com.memoire.Gestion_des_etudiants.DAO.mapping.UserColonneMapping;
import com.memoire.Gestion_des_etudiants.DAO.mapping.UserNonDetailleColoneMapping;
import com.memoire.Gestion_des_etudiants.entites.User;
import com.memoire.Gestion_des_etudiants.entites.bref.UserNonDetaille;
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
@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Optional<User> findByEmailId(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserColonneMapping());
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public <S extends User> S save(S entity) {
        String sql="INSERT INTO user(name, tel, email, password, status, role)" +
                " VALUES(?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        //expression lambda
        jdbcTemplate.update(connection->{
            PreparedStatement ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,entity.getName());
            ps.setString(2,entity.getTel());
            ps.setString(3,entity.getEmail());
            ps.setString(4,entity.getPassword());
            ps.setString(5,entity.getStatus());
            ps.setString(6,entity.getRole());
            return  ps;
        },keyHolder);
        entity.setId(keyHolder.getKey().longValue());
        return entity;
    }
    @Override
    public List<UserNonDetaille> loadUserNonDetaille() {
        String sql="SELECT * FROM user WHERE role='user'";

        List<UserNonDetaille>userNonDetailles= jdbcTemplate.query(sql, new UserNonDetailleColoneMapping());
        return userNonDetailles;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        List<S> result=new ArrayList<>();
        for (S entity:entities){
            save(entity);
            result.add(entity);
        }
        return  result;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
