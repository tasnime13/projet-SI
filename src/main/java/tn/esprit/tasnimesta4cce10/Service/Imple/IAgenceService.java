package tn.esprit.tasnimesta4cce10.Service.Imple;

import tn.esprit.tasnimesta4cce10.domaine.Agence;

import java.util.List;

public interface IAgenceService {
    Agence create(Agence agence);
    Agence findById(long id);
    List<Agence> findAll();
    void deleteById(long id);
    Agence update(Agence agence);
}
