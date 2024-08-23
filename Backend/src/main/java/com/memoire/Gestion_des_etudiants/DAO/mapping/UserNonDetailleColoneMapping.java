package com.memoire.Gestion_des_etudiants.DAO.mapping;

import com.memoire.Gestion_des_etudiants.entites.bref.UserNonDetaille;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserNonDetailleColoneMapping implements RowMapper<UserNonDetaille> {
    @Override
    public UserNonDetaille mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserNonDetaille us=new UserNonDetaille();
        us.setId( rs.getInt("id"));
        us.setName(rs.getString("name"));
        us.setTel(rs.getString("tel"));
        us.setEmail(rs.getString("email"));
        us.setStatus(rs.getString("status"));
    return us;
    }
}
