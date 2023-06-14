package fr.simplon.pixelshielrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String type;

    private String description;

    @Column(name="creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name="close_date")
    private LocalDateTime closeDate;


    private Boolean accepted = false;

    private Boolean pending = true;

    @Lob
    private byte[] file;

    public boolean isPending() {
        return pending;
    }

}
