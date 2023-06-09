package fr.simplon.pixelshielrestapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private LocalDateTime creation_date = LocalDateTime.now();

    /**
     * Date de clôture du sondage.
     */
    @NotNull(message = "La date de clôture ne peut pas être nulle.")
    @FutureOrPresent(message = "La date de clôture doit être ultérieure à la date de création.")
    private LocalDateTime close_date;

    /**
     * Nom du créateur du sondage.
     */
    @NotNull
    private String username;

    @NotNull
    private Boolean published = false;

    @JsonManagedReference
    @OneToMany(mappedBy = "survey")
    private Set<Vote> votes = new HashSet<>();


    public void addVote(Vote vote) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        vote.setUsername(currentUserName);
        votes.add(vote);
        vote.setSurvey(this);
    }
    public boolean isOpen() {
        return LocalDateTime.now().isBefore(this.close_date);
    }
    public boolean isClosed() {
        return LocalDateTime.now().isAfter(this.close_date);
    }

}