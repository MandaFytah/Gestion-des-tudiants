package com.memoire.Gestion_des_etudiants.entites;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "departements")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddepartement")
    private Long idDepartement;
    @Column(name="code")
    private String code;
    @Column(name="nom")
    private String nom;
    private String tel;
    @ManyToOne(fetch = FetchType.LAZY)
    private String nomEtablissement;
    @Column(name="libelle")
    private String libelle;

}
