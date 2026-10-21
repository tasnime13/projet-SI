package tn.esprit.tasnimesta4cce10.Service.Imple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PaiementServicesImlp implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement create(Paiement paiement) {
        if (paiement.getIdPaiement() != null) {
            throw new IllegalArgumentException("Une nouvelle ressource ne doit pas avoir d'identifiant");
        }
        verifier(paiement);
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement findById(long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        findById(id); // 404 si absent
        paiementRepository.deleteById(id);
    }

    @Override
    public Paiement update(Paiement paiement) {
        findById(paiement.getIdPaiement()); // 404 si absent
        verifier(paiement);
        return paiementRepository.save(paiement);
    }

    private void verifier(Paiement paiement) {
        if (paiement.getMontant() != null && paiement.getMontant().signum() < 0) {
            throw new IllegalArgumentException("Le montant du paiement ne peut pas etre negatif");
        }
    }
}
