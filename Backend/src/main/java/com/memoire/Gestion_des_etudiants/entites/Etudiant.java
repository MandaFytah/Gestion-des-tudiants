package com.memoire.Gestion_des_etudiants.entites;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


/*@Table(name = "etudiants",uniqueConstraints = {
        @UniqueConstraint(columnNames = "idEtudiant"),
        @UniqueConstraint(columnNames = "matricule"+""),
        @UniqueConstraint(columnNames = "email")

})*/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "etudiants")
public class Etudiant implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEtudiant")
    private float idEtudiant;
    @Column(name = "matricule")
    private String matricule;
    @Column(name = "nom")
    @NotEmpty
    private String nom;
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "name")
    private String adresse;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT")
    @Column(name = "dateNaissance")
    private LocalDate dateNaissance;
    @Column(name = "lieu")
    private String lieu;
    @Column(name = "tel")
    private String tel;
    @Column(name = "email")
    @NotEmpty
    private String email;
    //private String ville;
    // private String codePostal;
    //private String pere, professionPere, cinPere,telPere;
    //private String mere, professionMere, cinMere, telMere;
    //private String tuteur, professionTuteur, cinTuteur, telTuteur;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "classe",nullable = false)
    private Niveau niveau;
    @Column(name = "classe")
    private String classe;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "nomDepartement",nullable = false)
    private  Departement departement;
    @Column(name="nomDepartement")
    private String nomDepartement;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yy-MM-dd",timezone = "GMT")
    @Column(name = "annee")
    private String annee;


    //private String bp;
    @Column(name = "photo")
    private byte [] photo;

    public Etudiant(String email, String password, ArrayList<Object> objects) {
        this.email=email;
        this.matricule=password;
    }

    //Methode







}

