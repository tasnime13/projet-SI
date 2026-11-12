package tn.esprit.tasnimesta4cce10.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private String statut;

    // Attributs propriétaires : aucune annotation Jackson (créés via { "client": {"idClient": 1} })
    @ManyToOne
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private Client client;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    @ToString.Exclude
    private Vehicule vehicule;

    @OneToOne
    @JoinColumn(name = "contrat_id")
    @ToString.Exclude
    private Contrat contrat;
}
