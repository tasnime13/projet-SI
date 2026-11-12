package tn.esprit.tasnimesta4cce10.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.domain.Maintenance;
import tn.esprit.tasnimesta4cce10.domain.Vehicule;
import tn.esprit.tasnimesta4cce10.repository.IMaintenanceRepository;
import tn.esprit.tasnimesta4cce10.repository.IvehiculeRepository;
import tn.esprit.tasnimesta4cce10.Service.IMaintenanceService;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServicesImlp implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;
    private final IvehiculeRepository vehiculeRepository;

    @Override
    public Maintenance create(Maintenance maintenance) {
        if (maintenance.getIdMaintenance() != null) {
            throw new IllegalArgumentException("Un nouvel élément ne doit pas avoir d'identifiant");
        }
        verifier(maintenance);
        maintenance.setVehicule(resoudreVehicule(maintenance.getVehicule()));
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance findById(long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance introuvable avec l'identifiant " + id);
        }
        maintenanceRepository.deleteById(id);
    }

    @Override
    public Maintenance update(long id, Maintenance maintenance) {
        verifier(maintenance);
        Maintenance existante = findById(id);
        existante.setDateDebut(maintenance.getDateDebut());
        existante.setDateFin(maintenance.getDateFin());
        existante.setDescription(maintenance.getDescription());
        if (maintenance.getVehicule() != null) {
            existante.setVehicule(resoudreVehicule(maintenance.getVehicule()));
        }
        return maintenanceRepository.save(existante);
    }

    private void verifier(Maintenance maintenance) {
        if (maintenance.getDateDebut() != null && maintenance.getDateFin() != null
                && maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début");
        }
    }

    // Véhicule imbriqué obligatoire (vehicule avec idVehicule).

    private Vehicule resoudreVehicule(Vehicule vehicule) {
        if (vehicule == null || vehicule.getIdVehicule() == null) {
            throw new IllegalArgumentException("vehicule.idVehicule est obligatoire");
        }
        return vehiculeRepository.findById(vehicule.getIdVehicule())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Véhicule introuvable avec l'identifiant " + vehicule.getIdVehicule()));
    }
}
