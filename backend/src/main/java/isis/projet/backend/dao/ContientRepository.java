package isis.projet.backend.dao;

import isis.projet.backend.entity.Champ;
import isis.projet.backend.entity.Contient;
import isis.projet.backend.entity.Formulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContientRepository extends JpaRepository<Contient, Integer> {

    @Query("SELECT c FROM Champ c INNER JOIN Contient co ON c.idChamp = co.champ.idChamp " +
            " WHERE co.formulaire.idForm = :idForm")
    List<Champ> champsFormu(Integer idForm);

}
