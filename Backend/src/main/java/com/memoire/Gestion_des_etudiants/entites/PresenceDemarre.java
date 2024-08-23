package com.memoire.Gestion_des_etudiants.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "demarrePresence")
public class PresenceDemarre {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idCours")
    private Long idCours;
    @Column(name = "idDepartement")
    private Long idDepartement;
    @Column(name = "idNiveau")
    private Long idNiveau;

    @Override
    public String toString() {
        return "PresenceDemarre{" +
                "id=" + id +
                ", idCours=" + idCours +
                ", idDepartement=" + idDepartement +
                ", idNiveau=" + idNiveau +
                '}';
    }
}
