package tn.esprit.tasnimesta4cce10.Service.Imple;

import tn.esprit.tasnimesta4cce10.domaine.Equipement;

import java.util.List;

public interface IEquipementService {
    Equipement create(Equipement equipement);
    Equipement findById(long id);
    List<Equipement> findAll();
    void deleteById(long id);
    Equipement update(Equipement equipement);
}
