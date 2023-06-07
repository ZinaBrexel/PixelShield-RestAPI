package fr.simplon.pixelshielrestapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entité représentant un sondage.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Survey {

    /**
     * Identifiant unique du sondage.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Description du sondage.
     */
    @NotBlank(message = "La description ne peut pas être vide.")
    @Size(min = 3, max = 120, message = "La description doit faire entre 3 et 120 caractères.")
    private String description;

    /**
     * Question posée dans le sondage.
     */
    @NotBlank(message = "La question ne peut pas être vide.")
    @Size(max = 120, message = "La question doit faire maximum 120 caractères.")
    private String question;

    /**
     * Date de création du sondage.
     */
    @NotNull(message = "La date de création ne peut pas être nulle.")
    @Column(updatable = false)
    private LocalDate creation_date = LocalDate.now();


    /**
     * Date de clôture du sondage.
     */
    @NotNull(message = "La date de clôture ne peut pas être nulle.")
    @FutureOrPresent(message = "La date de clôture doit être ultérieure à la date de création.")
    private LocalDate close_date;

    /**
     * Nom du créateur du sondage.
     */
    private String employee;
}