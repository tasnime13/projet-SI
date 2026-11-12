package tn.esprit.tasnimesta4cce10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tasnimesta4cce10.domaine.Paiement;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
}
