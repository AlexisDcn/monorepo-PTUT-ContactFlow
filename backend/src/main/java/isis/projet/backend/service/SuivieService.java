package isis.projet.backend.service;

import isis.projet.backend.dao.CountryRepository;
import isis.projet.backend.dao.SuivieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SuivieService {
    private final SuivieRepository suivieDao;

    public SuivieService(SuivieRepository suivieDao) {
        this.suivieDao = suivieDao;
    }

}
