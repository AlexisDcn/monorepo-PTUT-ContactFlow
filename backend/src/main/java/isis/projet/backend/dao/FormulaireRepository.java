package isis.projet.backend.dao;

import isis.projet.backend.entity.Formation;
import isis.projet.backend.entity.Formulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormulaireRepository extends JpaRepository<Formulaire, Integer> {
    @Query("SELECT f FROM Formulaire f WHERE f.actif = true ")
    List<Formulaire> formulairesActifs();

    @Modifying
    @Query ("DELETE FROM Formulaire f WHERE f.salon.idSalon =:idSalon")
    void deleteFormulaire(Integer idSalon);

    @Modifying
    @Query ("UPDATE Formulaire f SET f.actif = CASE WHEN f.actif = true THEN false ELSE true END WHERE f.salon.idSalon = :idSalon")
    void desactiverForm (Integer idSalon);

    @Query("SELECT s.nom FROM Salon s WHERE s.idSalon = :idSalon")
    String getSalonNom(Integer idSalon);
}
