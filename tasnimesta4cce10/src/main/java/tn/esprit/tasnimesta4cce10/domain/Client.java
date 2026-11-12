package tn.esprit.tasnimesta4cce10.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String numPermis;
    private LocalDate dateInscription;

    // Côté inverse : ignoré en JSON. Pas de @JsonBackReference sur Reservation.client.
    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    @JsonIgnore
    @ToString.Exclude
    private List<Reservation> reservations;
}
