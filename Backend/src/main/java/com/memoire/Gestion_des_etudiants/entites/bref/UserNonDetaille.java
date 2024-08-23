package com.memoire.Gestion_des_etudiants.entites.bref;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserNonDetaille {
    private long id;
    private String name;
    private String email;
    private String tel;
    private String status;
}
