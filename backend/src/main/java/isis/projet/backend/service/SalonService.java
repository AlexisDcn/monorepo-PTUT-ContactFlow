package isis.projet.backend.service;

import isis.projet.backend.dao.SalonRepository;
import isis.projet.backend.entity.Salon;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<String> getDate() {
        List<LocalDate> dates = salonDao.getDate();

        List<String> annees = dates.stream()
                .map(date -> String.valueOf(date.getYear())).distinct().collect(Collectors.toList());

        return annees;
    }

}
