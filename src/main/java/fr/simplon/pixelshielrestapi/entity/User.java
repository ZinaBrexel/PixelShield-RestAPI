package fr.simplon.pixelshielrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Users {

    private String firstName;
    private String lastname;

    private String address;
    @Id
    @Column (name="username")
    private String email;

    private String phone;

    private String password;


}
