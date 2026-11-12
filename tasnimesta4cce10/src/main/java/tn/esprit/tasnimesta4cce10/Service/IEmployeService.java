package tn.esprit.tasnimesta4cce10.Service;

import tn.esprit.tasnimesta4cce10.domain.Employe;

import java.util.List;

public interface IEmployeService {
    Employe create(Employe employe);
    Employe findById(long id);
    List<Employe> findAll();
    void deleteById(long id);
    Employe update(long id, Employe employe);
}
