package isis.projet.backend.service;

import isis.projet.backend.dao.FormulaireRepository;
import isis.projet.backend.entity.Formulaire;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormulaireService {
    private final FormulaireRepository formulaireDao;

    public FormulaireService(FormulaireRepository formulaireDao) {
        this.formulaireDao = formulaireDao;
    }

    @Transactional
    public List<Formulaire> formulairesActifs() {
        return formulaireDao.formulairesActifs();
    }

}

