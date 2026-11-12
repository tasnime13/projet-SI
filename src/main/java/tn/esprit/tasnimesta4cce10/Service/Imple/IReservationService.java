package tn.esprit.tasnimesta4cce10.Service.Imple;

import tn.esprit.tasnimesta4cce10.domaine.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation create(Reservation reservation);
    Reservation findById(long id);
    List<Reservation> findAll();
    void deleteById(long id);
    Reservation update(Reservation reservation);
}

