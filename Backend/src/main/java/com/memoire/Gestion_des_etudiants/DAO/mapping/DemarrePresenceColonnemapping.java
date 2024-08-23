package com.memoire.Gestion_des_etudiants.DAO.mapping;

import com.memoire.Gestion_des_etudiants.entites.PresenceDemarre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DemarrePresenceColonnemapping implements RowMapper<PresenceDemarre> {
    @Override
    public PresenceDemarre mapRow(ResultSet rs, int rowNum) throws SQLException {
        PresenceDemarre prsDem=new PresenceDemarre();
        prsDem.setId((long) rs.getInt("id"));
        prsDem.setIdCours((long) rs.getInt("idCours"));
        prsDem.setIdDepartement((long) rs.getInt("idDepartement"));
        prsDem.setIdNiveau((long) rs.getInt("idNiveau"));
        return prsDem;
    }
}
