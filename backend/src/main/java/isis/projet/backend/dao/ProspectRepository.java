package isis.projet.backend.dao;
import isis.projet.backend.entity.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface ProspectRepository extends JpaRepository<Prospect, Integer> {
    @Query("SELECT COUNT(*) FROM Prospect p WHERE p.salon.idSalon = :idSalon")
    Integer prospectSalon(Integer idSalon);
}
