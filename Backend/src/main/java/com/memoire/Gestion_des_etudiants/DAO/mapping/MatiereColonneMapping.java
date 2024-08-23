package com.memoire.Gestion_des_etudiants.DAO.mapping;

import com.memoire.Gestion_des_etudiants.entites.Matiere;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatiereColonneMapping implements RowMapper<Matiere> {
    @Override
    public Matiere mapRow(ResultSet rs, int rowNum) throws SQLException {
        Matiere matiere=new Matiere();
        matiere.setIdMatiere((long) rs.getInt("id"));
        matiere.setCode(rs.getString("code"));
        matiere.setLibelle(rs.getString("libelle"));
        matiere.setNom(rs.getString("nom"));
        matiere.setProf(rs.getString("prof"));
        return matiere;
    }
}
