package fr.simplon.pixelshielrestapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"username", "id_survey"}))
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String username;

    @NotNull
    @Column(nullable = false)
    private Boolean value;

    @Column(nullable = false)
    private LocalDateTime votedAt = LocalDateTime.now();

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name= "id_survey", nullable = false)
    private Survey survey;

}
