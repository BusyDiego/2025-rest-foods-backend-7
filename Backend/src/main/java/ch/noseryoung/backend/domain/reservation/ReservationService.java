package ch.noseryoung.backend.domain.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    
    private final ReservationRepository reservationRepository;
    
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
    
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }
    
    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    
    public Reservation update(Long id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }
    
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}