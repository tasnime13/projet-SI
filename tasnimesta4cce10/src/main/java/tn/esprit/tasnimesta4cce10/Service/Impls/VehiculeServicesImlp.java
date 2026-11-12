package tn.esprit.tasnimesta4cce10.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.Service.IVehiculeServices;
import tn.esprit.tasnimesta4cce10.domain.Agence;
import tn.esprit.tasnimesta4cce10.domain.Equipement;
import tn.esprit.tasnimesta4cce10.domain.Vehicule;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;
import tn.esprit.tasnimesta4cce10.repository.IAgenceRepository;
import tn.esprit.tasnimesta4cce10.repository.IEquipementRepository;
import tn.esprit.tasnimesta4cce10.repository.IvehiculeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServicesImlp implements IVehiculeServices {

    private final IvehiculeRepository ivehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final IEquipementRepository equipementRepository;

    @Override
    public Vehicule create(Vehicule vehicule) {
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException("Un nouvel élément ne doit pas avoir d'identifiant");
        }
        verifier(vehicule);
        vehicule.setAgence(resoudreAgence(vehicule.getAgence()));
        vehicule.setEquipements(resoudreEquipements(vehicule.getEquipements()));
        return ivehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(long id) {
        return ivehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Vehicule> findAll() {
        return ivehiculeRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        if (!ivehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Véhicule introuvable avec l'identifiant " + id);
        }
        ivehiculeRepository.deleteById(id);
    }

    @Override
    public Vehicule update(long id, Vehicule vehicule) {
        verifier(vehicule);
        Vehicule existant = findById(id);

        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        // associations modifiées seulement si elles sont fournies dans le corps
        if (vehicule.getAgence() != null) {
            existant.setAgence(resoudreAgence(vehicule.getAgence()));
        }
        if (vehicule.getEquipements() != null) {
            existant.setEquipements(resoudreEquipements(vehicule.getEquipements()));
        }

        return ivehiculeRepository.save(existant);
    }

    @Override
    public Vehicule save(Vehicule vehicule) {
        return ivehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule assignement(Long idVehicule, Long idAgence) {
        Vehicule vehicule = findById(idVehicule);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Agence introuvable avec l'identifiant " + idAgence));

        vehicule.setAgence(agence);
        return ivehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule assignementEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findById(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Équipement introuvable avec l'identifiant " + idEquipement));

        vehicule.getEquipements().add(equipement);
        return ivehiculeRepository.save(vehicule);
    }

    private void verifier(Vehicule v) {
        if (v.getTarifJournalier() != null && v.getTarifJournalier().signum() < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
        }
    }

    private Agence resoudreAgence(Agence agence) {
        if (agence == null) {
            return null;
        }
        if (agence.getIdAgence() == null) {
            throw new IllegalArgumentException("agence.idAgence est obligatoire");
        }
        return agenceRepository.findById(agence.getIdAgence())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Agence introuvable avec l'identifiant " + agence.getIdAgence()));
    }

    private List<Equipement> resoudreEquipements(List<Equipement> equipements) {
        if (equipements == null) {
            return null;
        }
        List<Equipement> resultat = new ArrayList<>();
        for (Equipement e : equipements) {
            if (e == null || e.getIdEquipement() == null) {
                throw new IllegalArgumentException("equipements[].idEquipement est obligatoire");
            }
            resultat.add(equipementRepository.findById(e.getIdEquipement())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Équipement introuvable avec l'identifiant " + e.getIdEquipement())));
        }
        return resultat;
    }

    @Override

    public void unassignementEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findById(idVehicule);

        boolean retire = vehicule.getEquipements() != null
                && vehicule.getEquipements().removeIf(
                e -> idEquipement.equals(e.getIdEquipement()));

        if (!retire) {
            throw new ResourceNotFoundException("L'équipement " + idEquipement
                    + " n'est pas associé au véhicule " + idVehicule);
        }
        ivehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule addAndAssignAgence(Vehicule vehicule, Long idAgence) {
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Agence introuvable avec l'identifiant " + idAgence));

        vehicule.setAgence(agence);
        return ivehiculeRepository.save(vehicule);
    }
}
