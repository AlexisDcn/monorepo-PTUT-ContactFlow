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

    @Transactional
    public void deleteSalon(Integer salon) {
        // Faire en dernier pour respecter la cascade
        salonDao.deleteSalon(salon);
    }

    @Transactional
    public void archiver(Integer salon) {
        salonDao.archiveSalon(salon);
    }

    @Transactional
    public void modifNom(Integer salon, String nvNom) {
        salonDao.modifNom(salon, nvNom);
    }
}
