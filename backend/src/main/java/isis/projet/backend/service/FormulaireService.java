package isis.projet.backend.service;

import isis.projet.backend.dao.FormulaireRepository;
import org.springframework.stereotype.Service;

@Service
public class FormulaireService {
    private final FormulaireRepository formulaireDao;

    public FormulaireService(FormulaireRepository formulaireDao) {
        this.formulaireDao = formulaireDao;
    }
}

