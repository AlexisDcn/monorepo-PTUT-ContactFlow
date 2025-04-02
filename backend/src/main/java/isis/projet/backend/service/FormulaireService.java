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
    public Formulaire createFormulaire(Formulaire formulaire) {
        return formulaireDao.save(formulaire);
    }

    @Transactional
    public List<Formulaire> formulairesActifs() {
        return formulaireDao.formulairesActifs();
    }

    @Transactional
    public void deleteFormulaire(Integer salon) {
        // Faire en premier pour respecter la cascade
        formulaireDao.deleteFormulaire(salon);
    }

    @Transactional
    public void desactiver(Integer salon) {
        formulaireDao.desactiverForm(salon);
    }

}

