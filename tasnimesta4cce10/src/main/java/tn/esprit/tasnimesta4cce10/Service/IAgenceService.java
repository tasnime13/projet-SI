package tn.esprit.tasnimesta4cce10.Service;

import tn.esprit.tasnimesta4cce10.domain.Agence;

import java.util.List;

public interface IAgenceService {
    Agence create(Agence agence);
    Agence findById(long id);
    List<Agence> findAll();
    void deleteById(long id);
    Agence update(long id, Agence agence);
}
