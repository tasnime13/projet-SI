package tn.esprit.tasnimesta4cce10.Service;

import tn.esprit.tasnimesta4cce10.domaine.Vehicule;

import java.util.List;

public interface IVehiculeService {
    Vehicule create(Vehicule vehicule);
    Vehicule findById(long id);
    List<Vehicule> findAll();
    void deleteById(long id);
    Vehicule update(Vehicule vehicule);
}