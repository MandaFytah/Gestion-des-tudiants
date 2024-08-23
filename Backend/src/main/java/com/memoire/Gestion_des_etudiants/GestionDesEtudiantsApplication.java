package com.memoire.Gestion_des_etudiants;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.memoire.Gestion_des_etudiants.DAO")
@EntityScan(basePackages = "com.memoire.Gestion_des_etudiants.entites")
@ComponentScan(basePackages = "com.memoire.Gestion_des_etudiants")
public class GestionDesEtudiantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDesEtudiantsApplication.class, args);

	}
}
