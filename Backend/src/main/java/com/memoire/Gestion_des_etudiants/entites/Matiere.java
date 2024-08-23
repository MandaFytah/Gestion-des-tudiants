package com.memoire.Gestion_des_etudiants.entites;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "matiere")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long IdMatiere;
    @Column(name= "code")
    private String code;
    @Column(name = "nom")
    public  String nom;
    @Column(name = "libelle")
    private String libelle;
    @ManyToOne(fetch = FetchType.LAZY)

    @Column(name= "prof")
    private String prof;


    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getIdMatiere() {
        return IdMatiere;
    }

    public void setIdMatiere(Long idMatiere) {
        IdMatiere = idMatiere;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Matiere{" +
                "IdMatiere=" + IdMatiere +
                ", code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", libelle='" + libelle + '\'' +
                ", prof='" + prof + '\'' +
                '}';
    }
}
