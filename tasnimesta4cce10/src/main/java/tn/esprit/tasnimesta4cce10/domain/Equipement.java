package tn.esprit.tasnimesta4cce10.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    private String libelle;

    // Côté inverse : ignoré en JSON (l'attribut propriétaire est Vehicule.equipements)
    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Vehicule> vehicules;
}
