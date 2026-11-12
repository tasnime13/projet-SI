package tn.esprit.tasnimesta4cce10.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    private String nom;
    private String adresse;
    private String telephone;
    private String ville;

    // Côté inverse : ignoré en JSON (l'attribut propriétaire est Vehicule.agence)
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Vehicule> vehicules;

    // Côté inverse : ignoré en JSON (l'attribut propriétaire est Employe.agence)
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Employe> employes;
}
