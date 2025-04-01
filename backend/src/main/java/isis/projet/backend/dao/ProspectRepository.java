package isis.projet.backend.dao;
import isis.projet.backend.entity.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProspectRepository extends JpaRepository<Prospect, Integer> {
    @Query("SELECT COUNT(*) FROM Prospect p WHERE p.salon.idSalon = :idSalon")
    Integer prospectSalon(Integer idSalon);

    @Query("SELECT p FROM Prospect p WHERE p.salon.idSalon = :idSalon")
    List<Prospect> prospectSalonGlobalNom(Integer idSalon);


    @Query("SELECT YEAR(s.date) as annee, COUNT(p) FROM Prospect p JOIN p.salon s GROUP BY YEAR(s.date)")
    List<Object[]> countProspectsByYear();

    @Query("SELECT p FROM Prospect p JOIN p.salon s WHERE YEAR(s.date) = :year")
    List<Prospect> findProspectsByYear(Integer year);

    @Query("SELECT DISTINCT YEAR(s.date) FROM Salon s ORDER BY YEAR(s.date)")
    List<Integer> findDistinctYears();

    @Modifying
    @Query("DELETE FROM Prospect p WHERE p.salon.idSalon = :idSalon")
    void deleteProspect(Integer idSalon);
}
