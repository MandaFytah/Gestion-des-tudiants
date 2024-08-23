package com.memoire.Gestion_des_etudiants.DAO.mapping;

import com.memoire.Gestion_des_etudiants.entites.Departement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartementColonneMapping implements RowMapper<Departement> {
    @Override
    public Departement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Departement dep=new Departement();
        dep.setIdDepartement((long) rs.getInt("iddepartement"));
        dep.setCode(rs.getString("code"));
        dep.setNom(rs.getString("nom"));
        dep.setTel(rs.getString("tel"));
        dep.setNomEtablissement(rs.getString("nomEtablissement"));
        dep.setLibelle(rs.getString("libelle"));
        return dep;
    }
}
