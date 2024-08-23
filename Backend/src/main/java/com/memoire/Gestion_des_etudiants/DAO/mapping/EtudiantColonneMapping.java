package com.memoire.Gestion_des_etudiants.DAO.mapping;

import com.memoire.Gestion_des_etudiants.entites.Etudiant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EtudiantColonneMapping implements RowMapper<Etudiant> {
    @Override
    public Etudiant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Etudiant etudiant=new Etudiant();
        etudiant.setIdEtudiant(Float.valueOf(rs.getInt("Idetudiant")));
        etudiant.setMatricule(rs.getString("Matricule"));
        etudiant.setNom(rs.getString("Nom"));
        etudiant.setPrenom(rs.getString("Prenom"));
        etudiant.setAdresse(rs.getString("Adresse"));
        etudiant.setDateNaissance(rs.getDate("DateNaissance").toLocalDate());
        etudiant.setLieu(rs.getString("Lieu"));
        etudiant.setTel(rs.getString("Tel"));
        etudiant.setEmail(rs.getString("Email"));
        etudiant.setNomDepartement(rs.getString("NomDepartement"));
        etudiant.setClasse(rs.getString("Classe"));
        etudiant.setAnnee(rs.getString("Annee"));
        etudiant.setPhoto(rs.getBytes("Photo"));
        /*etudiant.setVille(rs.getString("ville"));
        etudiant.setCodePostal(rs.getString("codePostal"));
        etudiant.setPere(rs.getString("pere"));
        etudiant.setProfessionPere(rs.getString("professionPere"));
        etudiant.setCinPere(rs.getString("cinPere"));
        etudiant.setTelPere(rs.getString("telPere"));
        etudiant.setMere(rs.getString("mere"));
        etudiant.setProfessionMere(rs.getString("professionMere"));
        etudiant.setCinMere(rs.getString("cinMere"));
        etudiant.setCinMere(rs.getString("telMere"));
        etudiant.setTuteur(rs.getString("tuteur"));
        etudiant.setProfessionTuteur(rs.getString("professionTuteur"));
        etudiant.setCinTuteur(rs.getString("cinTuteur"));
        etudiant.setTelTuteur(rs.getString("telTuteur"));*/
       // etudiant.setBp(rs.getString("bp"));
        return etudiant;
    }
}
