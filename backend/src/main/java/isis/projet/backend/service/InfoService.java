package isis.projet.backend.service;

import isis.projet.backend.dao.InfoRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
    private final InfoRepository infoDao;

    public InfoService(InfoRepository infoDao) {
        this.infoDao = infoDao;
    }

    @Transactional
    public void deleteInfo(Integer salon) {
        // Faire en premier pour respecter la cascade
        infoDao.deleteInfo(salon);
    }
}
