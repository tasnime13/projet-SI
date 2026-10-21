package tn.esprit.tasnimesta4cce10.Service.Imple;

import tn.esprit.tasnimesta4cce10.domaine.Employe;

import java.util.List;

public interface IEmployeService {
    Employe create(Employe employe);
    Employe findById(long id);
    List<Employe> findAll();
    void deleteById(long id);
    Employe update(Employe employe);
}
