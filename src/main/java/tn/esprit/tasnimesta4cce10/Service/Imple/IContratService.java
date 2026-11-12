package tn.esprit.tasnimesta4cce10.Service.Imple;

import tn.esprit.tasnimesta4cce10.domaine.Contrat;

import java.util.List;

public interface IContratService {
    Contrat create(Contrat contrat);
    Contrat findById(long id);
    List<Contrat> findAll();
    void deleteById(long id);
    Contrat update(Contrat contrat);
}
