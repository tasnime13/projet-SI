package tn.esprit.tasnimesta4cce10.Service.ample;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.Service.IvehiculeServices;
import tn.esprit.tasnimesta4cce10.domaine.vehicule;
import tn.esprit.tasnimesta4cce10.repository.IvehiculeRepository;

import java.util.List;
@RequiredArgsConstructor
@AllArgsConstructor
@Service

public class vehiculeServiceImpl implements IvehiculeServices {
    private final IvehiculeRepository ivehiculeRepository;

    private IvehiculeServices vehiculeRepository;
    @Autowired
    public vehiculeServiceImpl(IvehiculeServices vehiculeRepository, IvehiculeRepository ivehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
        this.ivehiculeRepository = ivehiculeRepository;
    }
    @Override
    public vehicule create(vehicule vehicule) {
        return vehiculeRepository.save(vehicule);}

    @Override
    public vehicule findById(Long id) {
        return vehiculeRepository.findById(id).orElseThrow;
    }

    @Override
    public List<vehicule> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
        vehiculeRepository.deleteById(id);

    }

    @Override
    public vehicule update(vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public vehicule save(vehicule vehicule) {
        return null;
    }
}
