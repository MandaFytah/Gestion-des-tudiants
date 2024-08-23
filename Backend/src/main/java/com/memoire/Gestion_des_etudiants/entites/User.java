package com.memoire.Gestion_des_etudiants.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

//=:email map the email that we have been declared inside Userrepository
//We use 'User' instead of 'user' here because this one is already maped with @Table(....)
@NamedQuery(name = "User.findByEmailId", query = "select u from User where u.email=:email")


@Data
//@DynamicUpdate
//@DynamicInsert
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements UserDetails{
    //private final long SerialiVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "tel")
    private String tel;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;
    @Column(name = "role")
    private String role;

    public <E> User(String email, String password,String role, ArrayList<E> es) {
        this.email=email;
        this.password=password;
        this.role=role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }
}
