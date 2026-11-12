package tn.esprit.tasnimesta4cce10.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;

    private Double montantTotal;

    private boolean valide;

    // Côté inverse : ignoré en JSON (l'attribut propriétaire est Reservation.contrat)
    @OneToOne(mappedBy = "contrat")
    @JsonIgnore
    @ToString.Exclude
    private Reservation reservation;

    // Composition : le contrat porte ses paiements (paire Managed/Back reference)
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("contrat-paiements")
    @ToString.Exclude
    private List<Paiement> paiements = new ArrayList<>();
}
