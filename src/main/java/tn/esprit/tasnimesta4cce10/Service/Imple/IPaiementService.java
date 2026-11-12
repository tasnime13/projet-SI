package tn.esprit.tasnimesta4cce10.Service.Imple;

import tn.esprit.tasnimesta4cce10.domaine.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement create(Paiement paiement);
    Paiement findById(long id);
    List<Paiement> findAll();
    void deleteById(long id);
    Paiement update(Paiement paiement);
}
