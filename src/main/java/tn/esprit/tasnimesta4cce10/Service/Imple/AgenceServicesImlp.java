package tn.esprit.tasnimesta4cce10.Service.Imple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.domaine.Agence;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;
import tn.esprit.tasnimesta4cce10.repository.IAgenceRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AgenceServicesImlp implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence create(Agence agence) {
        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException("Une nouvelle ressource ne doit pas avoir d'identifiant");
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
        findById(id); // 404 si absent
        agenceRepository.deleteById(id);
    }

    @Override
    public Agence update(Agence agence) {
        findById(agence.getIdAgence()); // 404 si absent
        verifier(agence);
        return agenceRepository.save(agence);
    }

    private void verifier(Agence agence) {
        if (agence.getNom() == null || agence.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom de l'agence est obligatoire");
        }
    }
}
