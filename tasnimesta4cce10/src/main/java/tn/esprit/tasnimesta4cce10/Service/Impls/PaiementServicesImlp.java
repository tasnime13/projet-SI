package tn.esprit.tasnimesta4cce10.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.domain.Paiement;
import tn.esprit.tasnimesta4cce10.repository.IPaiementRepository;
import tn.esprit.tasnimesta4cce10.Service.IPaiementService;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServicesImlp implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement findById(long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }
}
