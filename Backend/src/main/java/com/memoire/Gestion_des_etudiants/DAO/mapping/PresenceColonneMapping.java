package com.memoire.Gestion_des_etudiants.DAO.mapping;

import com.memoire.Gestion_des_etudiants.entites.Presence;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PresenceColonneMapping implements RowMapper<Presence> {
    @Override
    public Presence mapRow(ResultSet rs, int rowNum) throws SQLException {
        Presence presence= new Presence();
        presence.setId((long) rs.getInt("id"));
        presence.setNomEtudiant(rs.getString("nomEtudiant"));
        presence.setPrenomEtudiant(rs.getString("prenomEtudiant"));
        presence.setNomCours(rs.getString("nomcours"));
        presence.setNomDepartement(rs.getString("nomDepartement"));
        presence.setNiveau(rs.getString("niveau"));
        presence.setPresent(rs.getBoolean("isPresent"));

        return presence;
    }
}
