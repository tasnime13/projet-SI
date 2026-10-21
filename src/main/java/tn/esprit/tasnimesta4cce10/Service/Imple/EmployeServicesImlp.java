package tn.esprit.tasnimesta4cce10.Service.Imple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeServicesImlp implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe create(Employe employe) {
        if (employe.getIdEmploye() != null) {
            throw new IllegalArgumentException("Une nouvelle ressource ne doit pas avoir d'identifiant");
        }
        return employeRepository.save(employe);
    }

    @Override
    public Employe findById(long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        findById(id); // 404 si absent
        employeRepository.deleteById(id);
    }

    @Override
    public Employe update(Employe employe) {
        findById(employe.getIdEmploye()); // 404 si absent
        return employeRepository.save(employe);
    }
}

