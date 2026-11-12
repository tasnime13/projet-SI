package tn.esprit.tasnimesta4cce10.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.domain.Agence;
import tn.esprit.tasnimesta4cce10.repository.IAgenceRepository;
import tn.esprit.tasnimesta4cce10.Service.IAgenceService;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServicesImlp implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence create(Agence agence) {
        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException("Un nouvel élément ne doit pas avoir d'identifiant");
        }
        verifier(agence);
        return agenceRepository.save(agence);
    }

    @Override
    public Agence findById(long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence introuvable avec l'identifiant " + id);
        }
        agenceRepository.deleteById(id);
    }

    @Override
    public Agence update(long id, Agence agence) {
        verifier(agence);
        Agence existante = findById(id);
        existante.setNom(agence.getNom());
        existante.setAdresse(agence.getAdresse());
        existante.setTelephone(agence.getTelephone());
        existante.setVille(agence.getVille());
        return agenceRepository.save(existante);
    }

    private void verifier(Agence agence) {
        if (agence.getNom() == null || agence.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom de l'agence est obligatoire");
        }
    }
}
