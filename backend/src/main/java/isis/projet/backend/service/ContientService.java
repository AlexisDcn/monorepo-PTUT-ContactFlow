package isis.projet.backend.service;

import isis.projet.backend.dao.ContientRepository;
import org.springframework.stereotype.Service;

@Service
public class ContientService {
    private final ContientRepository contientDao;

    public ContientService(ContientRepository contientDao) {
        this.contientDao = contientDao;
    }
}

