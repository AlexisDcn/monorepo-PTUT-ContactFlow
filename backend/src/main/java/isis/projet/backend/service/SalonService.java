package isis.projet.backend.service;

import isis.projet.backend.dao.SalonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SalonService {
    private final SalonRepository salonDao;

    public SalonService(SalonRepository salonDao) {
        this.salonDao = salonDao;
    }

}
