package tn.esprit.tasnimesta4cce10.domaine;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    private String libelle;
    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private Set<vehicule> vehicules = new HashSet<>();
}