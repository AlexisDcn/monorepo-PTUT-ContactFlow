package isis.projet.backend.dao;

import isis.projet.backend.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Integer> {
    @Query("SELECT s FROM Salon s")
    List<Salon> getSalon();

    @Modifying
    @Query("DELETE FROM Salon s WHERE s.idSalon = :idSalon")
    void deleteSalon(Integer idSalon);

    @Modifying
    @Query("UPDATE Salon s SET s.archive = CASE WHEN s.archive = true THEN false ELSE true END WHERE s.idSalon = :idSalon")
    void archiveSalon(Integer idSalon);

    @Modifying
    @Query("UPDATE Salon s SET s.nom = :nvNom WHERE s.idSalon = :idSalon")
    void modifNom(Integer idSalon, String nvNom);

    @Query("SELECT s.date FROM Salon s")
    List<LocalDate> getDate();

}