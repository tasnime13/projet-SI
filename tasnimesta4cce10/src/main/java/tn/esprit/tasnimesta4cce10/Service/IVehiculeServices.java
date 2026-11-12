package tn.esprit.tasnimesta4cce10.Service;

import tn.esprit.tasnimesta4cce10.domain.Vehicule;

import java.util.List;

public interface IVehiculeServices {
    Vehicule create(Vehicule vehicule);
    Vehicule findById(long id);
    List<Vehicule> findAll();
    void deleteById(long id);
    Vehicule update(long id, Vehicule vehicule);

    Vehicule save(Vehicule vehicule);
    Vehicule assignement(Long idVehicule,Long idAgence);
    Vehicule assignementEquipement(Long idVehicule, Long idEquipement);
    void unassignementEquipement(Long idVehicule, Long idEquipement);
    Vehicule addAndAssignAgence(Vehicule vehicule, Long idAgence); }

