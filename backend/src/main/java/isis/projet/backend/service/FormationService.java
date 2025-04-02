package isis.projet.backend.service;

import isis.projet.backend.dao.ContientRepository;
import isis.projet.backend.dao.FormationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FormationService {
    private final FormationRepository formationDao;

    public FormationService(FormationRepository formationDao) {
        this.formationDao = formationDao;
    }

    @Transactional
    public void deleteFormation(Integer formation) {
        // Faire en premier pour respecter la cascade
        formationDao.deleteFormation(formation);
    }
}

