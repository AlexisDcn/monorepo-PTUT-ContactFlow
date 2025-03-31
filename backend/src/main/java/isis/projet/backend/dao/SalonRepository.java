package isis.projet.backend.dao;

import isis.projet.backend.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Integer> {
    @Query("SELECT s FROM Salon s")
    List<Salon> getSalon();

    @Modifying
    @Query("DELETE FROM Salon s WHERE s.idSalon = :idSalon")
    void deleteSalon(Integer idSalon);

    @Modifying
    @Query("UPDATE Salon s SET s.archive = true WHERE s.idSalon = :idSalon")
    void archiveSalon(Integer idSalon);

}