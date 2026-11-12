package tn.esprit.tasnimesta4cce10.Service.Imple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.Service.IVehiculeService;
import tn.esprit.tasnimesta4cce10.domaine.Vehicule;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;
import tn.esprit.tasnimesta4cce10.repository.IVehiculeRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VehiculeServicesImlp implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule create(Vehicule vehicule) {
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException("Une nouvelle ressource ne doit pas avoir d'identifiant");
        }
        verifier(vehicule);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        findById(id); // 404 si absent
        vehiculeRepository.deleteById(id);
    }

    @Override
    public Vehicule update(Vehicule vehicule) {
        findById(vehicule.getIdVehicule()); // 404 si absent
        verifier(vehicule);
        return vehiculeRepository.save(vehicule);
    }

    private void verifier(Vehicule vehicule) {
        if (vehicule.getTarifJournalier() != null && vehicule.getTarifJournalier().signum() < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas etre negatif");
        }
    }
}
