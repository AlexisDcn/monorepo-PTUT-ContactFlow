package isis.projet.backend.service;

import isis.projet.backend.dao.InfoRepository;

import isis.projet.backend.entity.*;
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

    @Transactional
    public Info createInfo(Champ champ, Prospect prospect, String valeur) {
        InfoId infoId = new InfoId();
        infoId.setChamp(champ);
        infoId.setProspect(prospect);

        Info info = new Info();
        info.setId(infoId);
        info.setChamp(champ);
        info.setProspect(prospect);
        info.setValeurTxt(valeur);

        return infoDao.save(info);
    }
}
