package isis.projet.backend.dao;

import isis.projet.backend.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Integer> {
    @Query("SELECT s FROM Salon s")
    List<Salon> getSalon();

}