package tn.esprit.tasnimesta4cce10.Service;

import tn.esprit.tasnimesta4cce10.domain.Paiement;

import java.util.List;

/** Lecture seule : les paiements sont créés et supprimés via le Contrat (cascade). */
public interface IPaiementService {
    Paiement findById(long id);
    List<Paiement> findAll();
}
