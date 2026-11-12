package tn.esprit.tasnimesta4cce10.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.domain.Agence;
import tn.esprit.tasnimesta4cce10.domain.Employe;
import tn.esprit.tasnimesta4cce10.repository.IAgenceRepository;
import tn.esprit.tasnimesta4cce10.repository.IEmployeRepository;
import tn.esprit.tasnimesta4cce10.Service.IEmployeService;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServicesImlp implements IEmployeService {

    private final IEmployeRepository employeRepository;
    private final IAgenceRepository agenceRepository;

    @Override
    public Employe create(Employe employe) {
        if (employe.getIdEmploye() != null) {
            throw new IllegalArgumentException("Un nouvel élément ne doit pas avoir d'identifiant");
        }
        employe.setAgence(resoudreAgence(employe.getAgence()));
        return employeRepository.save(employe);
    }

    @Override
    public Employe findById(long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employé introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employé introuvable avec l'identifiant " + id);
        }
        employeRepository.deleteById(id);
    }

    @Override
    public Employe update(long id, Employe employe) {
        Employe existant = findById(id);
        existant.setNom(employe.getNom());
        existant.setPrenom(employe.getPrenom());
        existant.setRole(employe.getRole());
        if (employe.getAgence() != null) {
            existant.setAgence(resoudreAgence(employe.getAgence()));
        }
        return employeRepository.save(existant);
    }

    // Corps imbriqué (agence avec idAgence) : on recharge l'agence existante.

    private Agence resoudreAgence(Agence agence) {
        if (agence == null) {
            return null;
        }
        if (agence.getIdAgence() == null) {
            throw new IllegalArgumentException("agence.idAgence est obligatoire");
        }
        return agenceRepository.findById(agence.getIdAgence())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Agence introuvable avec l'identifiant " + agence.getIdAgence()));
    }
}
