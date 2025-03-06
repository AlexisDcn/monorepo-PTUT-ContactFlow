package isis.projet.backend.service;

import isis.projet.backend.dao.ProspectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProspectService {
    private final ProspectRepository prospectDao;

    public ProspectService(ProspectRepository prospectDao) {
        this.prospectDao = prospectDao;
    }
}
