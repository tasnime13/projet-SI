package tn.esprit.tasnimesta4cce10.Service;

import tn.esprit.tasnimesta4cce10.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance create(Maintenance maintenance);
    Maintenance findById(long id);
    List<Maintenance> findAll();
    void deleteById(long id);
    Maintenance update(long id, Maintenance maintenance);
}
