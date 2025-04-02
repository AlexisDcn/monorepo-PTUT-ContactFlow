package isis.projet.backend.service;

import isis.projet.backend.dao.ContientRepository;
import isis.projet.backend.entity.Champ;
import isis.projet.backend.entity.Contient;
import isis.projet.backend.entity.ContientId;
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
    public Contient createContient(Champ champ, Formulaire formulaire) {
        ContientId contientId = new ContientId();
        contientId.setChamp(champ);
        contientId.setFormulaire(formulaire);

        Contient contient = new Contient();
        contient.setId(contientId);
        contient.setChamp(champ);
        contient.setFormulaire(formulaire);

        return contientDao.save(contient);
    }

    // Récupère la liste des champs suite à la route pour récup tous les champs d'un formulaire idForm
    @Transactional
    public List<Champ> champsFormu(Integer idForm) {
        return contientDao.champsFormu(idForm);
    }

    @Transactional
    public void deleteContient(Integer salon) {
        // Faire en premier pour respecter la cascade
        contientDao.deleteContient(salon);
    }


}

