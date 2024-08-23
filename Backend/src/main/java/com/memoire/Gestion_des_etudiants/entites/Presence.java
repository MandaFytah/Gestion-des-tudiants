package com.memoire.Gestion_des_etudiants.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "presence")
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomEtudiant")
    private String nomEtudiant;

    @Column(name = "prenomEtudiant")
    private String prenomEtudiant;

    @Column(name = "nomcours")
    private String nomCours;

    @Column(name = "nomDepartement")
    private String nomDepartement;

    @Column(name = "niveau")
    private String niveau;

    @Column(name = "isPresent")
    private boolean isPresent;

    public boolean getPresent() {
        return isPresent;
    }
}