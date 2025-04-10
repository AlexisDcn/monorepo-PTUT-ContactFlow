package isis.projet.backend.dao;
import isis.projet.backend.entity.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ProspectRepository extends JpaRepository<Prospect, Integer> {
    @Query("SELECT COUNT(*) FROM Prospect p WHERE p.salon.idSalon = :idSalon")
    Integer prospectSalon(Integer idSalon);

    @Query("SELECT COUNT(*) FROM Prospect p WHERE FUNCTION('YEAR', p.salon.date) = :annee")
    Integer countProspectBySpeYear(String annee);

    @Query("SELECT p FROM Prospect p WHERE p.salon.idSalon = :idSalon")
    List<Prospect> prospectSalonGlobalNom(Integer idSalon);

    @Query("SELECT COUNT(*) FROM Prospect p WHERE p.ville = :ville")
    Integer prospectVilleSpe(String ville);

    @Query("SELECT YEAR(s.date) as annee, COUNT(p) FROM Prospect p JOIN p.salon s GROUP BY YEAR(s.date)")
    List<Object[]> countProspectsByYear();

    @Query("SELECT p.ville as ville, COUNT(p) FROM Prospect p GROUP BY p.ville")
    List<Object[]> prospectVille();

    @Query("SELECT p FROM Prospect p JOIN p.salon s WHERE YEAR(s.date) = :year")
    List<Prospect> findProspectsByYear(Integer year);

    @Query("SELECT DISTINCT YEAR(s.date) FROM Salon s ORDER BY YEAR(s.date)")
    List<Integer> findDistinctYears();

    @Query("SELECT DISTINCT p.ville FROM Prospect p")
    List<String> getVilles();

    @Modifying
    @Query("DELETE FROM Prospect p WHERE p.salon.idSalon = :idSalon")
    void deleteProspect(Integer idSalon);

}
