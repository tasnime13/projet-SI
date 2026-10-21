package tn.esprit.tasnimesta4cce10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tasnimesta4cce10.domaine.Maintenance;

public interface IMaintenanceRepository  extends JpaRepository<Maintenance, Long> {
}
