package fr.simplon.pixelshielrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @Column (name="username")
    private String email;
    private String firstName;
    private String lastname;
    private String address;
    private String phone;
    private String password;
    private int enabled;

    @OneToMany(mappedBy = "user")
    private Collection<Authority> authorities = new ArrayList<>();

}
