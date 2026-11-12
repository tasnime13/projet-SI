package tn.esprit.tasnimesta4cce10.Service;

import tn.esprit.tasnimesta4cce10.domain.Equipement;

import java.util.List;

public interface IEquipementService {
    Equipement create(Equipement equipement);
    Equipement findById(long id);
    List<Equipement> findAll();
    void deleteById(long id);
    Equipement update(long id, Equipement equipement);
}
