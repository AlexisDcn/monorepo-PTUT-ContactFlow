package isis.projet.backend.service;

import isis.projet.backend.dao.ChampRepository;
import org.springframework.stereotype.Service;

@Service
public class ChampService {
    private final ChampRepository champDao;

    public ChampService(ChampRepository champDao) {
        this.champDao = champDao;
    }

}

