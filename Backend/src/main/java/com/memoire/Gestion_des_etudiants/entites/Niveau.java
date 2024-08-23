package com.memoire.Gestion_des_etudiants.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.annotation.processing.Generated;
import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "niveau")
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nom")
    private String nom;
}
