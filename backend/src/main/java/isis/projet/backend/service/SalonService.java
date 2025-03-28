package isis.projet.backend.service;

import isis.projet.backend.dao.SalonRepository;
import isis.projet.backend.entity.Salon;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalonService {
    private final SalonRepository salonDao;

    public SalonService(SalonRepository salonDao) {
        this.salonDao = salonDao;
    }
    public List<Salon> getSalon() {
        return salonDao.getSalon();
    }

    public void deleteSalon(Integer salon) {
//        salonDao.deleteSalon();
    }

}
