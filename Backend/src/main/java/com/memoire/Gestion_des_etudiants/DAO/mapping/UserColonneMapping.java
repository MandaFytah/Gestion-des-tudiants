package com.memoire.Gestion_des_etudiants.DAO.mapping;

import com.memoire.Gestion_des_etudiants.entites.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserColonneMapping implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user= new User();
        user.setId((long) rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setTel(rs.getString("tel"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setStatus(rs.getString("status"));
        user.setRole(rs.getString("role"));
        return user;

    }
}
