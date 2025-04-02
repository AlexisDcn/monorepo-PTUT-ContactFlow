package isis.projet.backend.service;

import isis.projet.backend.dao.ChampRepository;
import isis.projet.backend.entity.Champ;
import org.springframework.stereotype.Service;

@Service
public class ChampService {
    private final ChampRepository champDao;

    public ChampService(ChampRepository champDao) {
        this.champDao = champDao;
    }

    public Champ createChamp(Champ champ) {
        return champDao.save(champ);
    }

}

