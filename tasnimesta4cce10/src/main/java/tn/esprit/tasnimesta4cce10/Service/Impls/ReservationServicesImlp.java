package tn.esprit.tasnimesta4cce10.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.domain.Client;
import tn.esprit.tasnimesta4cce10.domain.Contrat;
import tn.esprit.tasnimesta4cce10.domain.Reservation;
import tn.esprit.tasnimesta4cce10.domain.Vehicule;
import tn.esprit.tasnimesta4cce10.repository.IClientRepository;
import tn.esprit.tasnimesta4cce10.repository.IContratRepository;
import tn.esprit.tasnimesta4cce10.repository.IReservationRepository;
import tn.esprit.tasnimesta4cce10.repository.IvehiculeRepository;
import tn.esprit.tasnimesta4cce10.Service.IReservationService;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServicesImlp implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final IClientRepository clientRepository;
    private final IvehiculeRepository vehiculeRepository;
    private final IContratRepository contratRepository;

    @Override
    public Reservation create(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            throw new IllegalArgumentException("Un nouvel élément ne doit pas avoir d'identifiant");
        }
        verifier(reservation);
        reservation.setClient(resoudreClient(reservation.getClient()));
        reservation.setVehicule(resoudreVehicule(reservation.getVehicule()));
        reservation.setContrat(resoudreContrat(reservation.getContrat()));
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findById(long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Réservation introuvable avec l'identifiant " + id);
        }
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation update(long id, Reservation reservation) {
        verifier(reservation);
        Reservation existante = findById(id);
        existante.setDateDebut(reservation.getDateDebut());
        existante.setDateFin(reservation.getDateFin());
        existante.setStatut(reservation.getStatut());
        if (reservation.getClient() != null) {
            existante.setClient(resoudreClient(reservation.getClient()));
        }
        if (reservation.getVehicule() != null) {
            existante.setVehicule(resoudreVehicule(reservation.getVehicule()));
        }
        return reservationRepository.save(existante);
    }

    private void verifier(Reservation reservation) {
        if (reservation.getDateDebut() != null && reservation.getDateFin() != null
                && reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début");
        }
    }

    // Client et véhicule imbriqués obligatoires, contrat facultatif.

    private Client resoudreClient(Client client) {
        if (client == null || client.getIdClient() == null) {
            throw new IllegalArgumentException("client.idClient est obligatoire");
        }
        return clientRepository.findById(client.getIdClient())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Client introuvable avec l'identifiant " + client.getIdClient()));
    }

    private Vehicule resoudreVehicule(Vehicule vehicule) {
        if (vehicule == null || vehicule.getIdVehicule() == null) {
            throw new IllegalArgumentException("vehicule.idVehicule est obligatoire");
        }
        return vehiculeRepository.findById(vehicule.getIdVehicule())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Véhicule introuvable avec l'identifiant " + vehicule.getIdVehicule()));
    }

    private Contrat resoudreContrat(Contrat contrat) {
        if (contrat == null) {
            return null;
        }
        if (contrat.getIdContrat() == null) {
            throw new IllegalArgumentException("contrat.idContrat est obligatoire");
        }
        return contratRepository.findById(contrat.getIdContrat())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Contrat introuvable avec l'identifiant " + contrat.getIdContrat()));
    }
}
