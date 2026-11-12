package tn.esprit.tasnimesta4cce10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tasnimesta4cce10.domaine.Vehicule;

public interface IVehiculeRepository extends JpaRepository<Vehicule,Long> {
}
