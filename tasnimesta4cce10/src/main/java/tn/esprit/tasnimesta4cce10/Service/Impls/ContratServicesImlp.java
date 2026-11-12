package tn.esprit.tasnimesta4cce10.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.domain.Contrat;
import tn.esprit.tasnimesta4cce10.domain.Paiement;
import tn.esprit.tasnimesta4cce10.repository.IContratRepository;
import tn.esprit.tasnimesta4cce10.Service.IContratService;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServicesImlp implements IContratService {

    private final IContratRepository contratRepository;

    @Override
    public Contrat create(Contrat contrat) {
        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException("Un nouvel élément ne doit pas avoir d'identifiant");
        }
        verifier(contrat);
        contrat.setReservation(null);
        if (contrat.getPaiements() == null) {
            contrat.setPaiements(new ArrayList<>());
        }
        for (Paiement paiement : contrat.getPaiements()) {
            if (paiement.getIdPaiement() != null) {
                throw new IllegalArgumentException("Un nouveau paiement ne doit pas avoir d'identifiant");
            }
            if (paiement.getMontant() != null && paiement.getMontant() < 0) {
                throw new IllegalArgumentException("Le montant d'un paiement ne peut pas être négatif");
            }
            // lien propriétaire garanti (cascade PERSIST vers les paiements)
            paiement.setContrat(contrat);
        }
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat findById(long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat introuvable avec l'identifiant " + id);
        }
        contratRepository.deleteById(id);
    }

    // Ne recopie que les attributs modifiables : les paiements existants sont conservés.
    @Override
    public Contrat update(long id, Contrat contrat) {
        verifier(contrat);
        Contrat existant = findById(id);
        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.isValide());
        return contratRepository.save(existant);
    }

    private void verifier(Contrat contrat) {
        if (contrat.getMontantTotal() != null && contrat.getMontantTotal() < 0) {
            throw new IllegalArgumentException("Le montant total ne peut pas être négatif");
        }
    }
}
