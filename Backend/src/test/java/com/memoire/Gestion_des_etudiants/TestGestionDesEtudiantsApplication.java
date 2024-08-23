package com.memoire.Gestion_des_etudiants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;


@TestConfiguration(proxyBeanMethods = false)
public class TestGestionDesEtudiantsApplication {

	public static void main(String[] args) {
		SpringApplication.from(GestionDesEtudiantsApplication::main).with(TestGestionDesEtudiantsApplication.class).run(args);
	}

}
