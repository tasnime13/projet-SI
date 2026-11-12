package tn.esprit.tasnimesta4cce10.Service;

import tn.esprit.tasnimesta4cce10.domain.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation create(Reservation reservation);
    Reservation findById(long id);
    List<Reservation> findAll();
    void deleteById(long id);
    Reservation update(long id, Reservation reservation);
}
