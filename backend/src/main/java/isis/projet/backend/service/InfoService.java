package isis.projet.backend.service;

import isis.projet.backend.dao.InfoRepository;

import org.springframework.stereotype.Service;

@Service
public class InfoService {
    private final InfoRepository infoDao;

    public InfoService(InfoRepository infoDao) {
        this.infoDao = infoDao;
    }
}
