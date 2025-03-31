package isis.projet.backend.dao;

import isis.projet.backend.entity.Country;
import isis.projet.backend.entity.Suivie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuivieRepository extends JpaRepository<Suivie, Integer> {
    @Modifying
    @Query("DELETE FROM Suivie s WHERE s.prospect.salon.idSalon = :idSalon")
    void deleteSuivi(Integer idSalon);

}