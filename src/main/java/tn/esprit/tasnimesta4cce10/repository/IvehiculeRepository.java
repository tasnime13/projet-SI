package tn.esprit.tasnimesta4cce10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.tasnimesta4cce10.domaine.vehicule;

public interface IvehiculeRepository extends JpaRepository<vehicule,Long> {
}
