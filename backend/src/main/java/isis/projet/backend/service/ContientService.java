package isis.projet.backend.service;

import isis.projet.backend.dao.ContientRepository;
import isis.projet.backend.entity.Champ;
import isis.projet.backend.entity.Formulaire;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContientService {
    private final ContientRepository contientDao;

    public ContientService(ContientRepository contientDao) {
        this.contientDao = contientDao;
    }

    @Transactional
    public List<Champ> champsFormu(Integer idForm) {
        return contientDao.champsFormu(idForm);
    }

}

