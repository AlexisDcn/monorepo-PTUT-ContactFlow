package isis.projet.backend.service;

import isis.projet.backend.dao.ProspectRepository;
import isis.projet.backend.entity.Salon;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProspectService {
    private final ProspectRepository prospectDao;

    public ProspectService(ProspectRepository prospectDao) {
        this.prospectDao = prospectDao;
    }

    @Transactional
    public Integer prospectSalon(Integer idSalon) {
        return prospectDao.prospectSalon(idSalon);
    }

    @Transactional
    public Map<String, Integer> prospectSalonGlobal(List<Salon> salons) {
        Map<String, Integer> map = new HashMap<>();
        for (Salon salon : salons) {
            map.put(salon.getNom(), prospectDao.prospectSalon(salon.getIdSalon()));
        }
        return map;
    }

    @Transactional
    public Map<String, Integer> prospectAnnee() {
        List<Object[]> results = prospectDao.countProspectsByYear();
        Map<String, Integer> data = new HashMap<>();

        for (Object[] row : results) {
            String annee = String.valueOf(row[0]); // Récup l'année
            Integer count = ((Number) row[1]).intValue(); // Récup le nb de pers
            data.put(annee, count);
        }

        return data;
    }

}
