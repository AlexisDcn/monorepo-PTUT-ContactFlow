package isis.projet.backend.dao;

import isis.projet.backend.entity.Champ;
import isis.projet.backend.entity.Contient;
import isis.projet.backend.entity.Formulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContientRepository extends JpaRepository<Contient, Integer> {

    // Requête SQL sélectionnant tous les champs liés au formulaire idForm
    @Query("SELECT c FROM Champ c INNER JOIN Contient co ON c.idChamp = co.champ.idChamp " +
            " WHERE co.formulaire.idForm = :idForm")
    List<Champ> champsFormu(Integer idForm);


    @Modifying
    @Query ("DELETE FROM Contient c WHERE c.formulaire.salon.idSalon = :idSalon")
    void deleteContient(Integer idSalon);

}
