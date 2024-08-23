package com.memoire.Gestion_des_etudiants.DAO.mapping;

import com.memoire.Gestion_des_etudiants.entites.Niveau;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NiveauColonneMapping implements RowMapper<Niveau> {
    @Override
    public Niveau mapRow(ResultSet rs, int rowNum) throws SQLException {
        Niveau niveau= new Niveau();
        niveau.setId((long) rs.getInt("id"));
        niveau.setNom(rs.getString("nom"));
        return niveau;
    }
}
