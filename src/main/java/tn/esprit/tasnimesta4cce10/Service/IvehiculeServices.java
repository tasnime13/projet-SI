package tn.esprit.tasnimesta4cce10.Service;

import tn.esprit.tasnimesta4cce10.domaine.vehicule;

import java.util.List;

public interface IvehiculeServices {
    vehicule create(vehicule vehicule);
    vehicule findById(Long id);
    List<vehicule> findAll();
    void deleteById(Long id);
    vehicule update(vehicule vehicule);

    vehicule save(vehicule vehicule);
}
