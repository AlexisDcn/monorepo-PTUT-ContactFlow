package isis.projet.backend.dao;

import isis.projet.backend.entity.Contient;
import isis.projet.backend.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FormationRepository extends JpaRepository<Formation, Integer> {

    @Modifying
    @Query("DELETE FROM Formation f WHERE f.id = :idFormation")
    void deleteFormation(Integer idFormation);
}
