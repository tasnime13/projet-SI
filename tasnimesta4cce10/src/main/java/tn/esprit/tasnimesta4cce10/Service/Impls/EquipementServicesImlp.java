package tn.esprit.tasnimesta4cce10.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.domain.Equipement;
import tn.esprit.tasnimesta4cce10.repository.IEquipementRepository;
import tn.esprit.tasnimesta4cce10.Service.IEquipementService;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServicesImlp implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    @Override
    public Equipement create(Equipement equipement) {
        if (equipement.getIdEquipement() != null) {
            throw new IllegalArgumentException("Un nouvel élément ne doit pas avoir d'identifiant");
        }
        verifier(equipement);
        equipement.setVehicules(null);
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement findById(long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Équipement introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        if (!equipementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Équipement introuvable avec l'identifiant " + id);
        }
        equipementRepository.deleteById(id);
    }

    @Override
    public Equipement update(long id, Equipement equipement) {
        verifier(equipement);
        Equipement existant = findById(id);
        existant.setLibelle(equipement.getLibelle());
        return equipementRepository.save(existant);
    }

    private void verifier(Equipement equipement) {
        if (equipement.getLibelle() == null || equipement.getLibelle().isBlank()) {
            throw new IllegalArgumentException("Le libellé de l'équipement est obligatoire");
        }
    }
}
