package isis.projet.backend.dao;

import isis.projet.backend.entity.Country;
import isis.projet.backend.entity.Suivie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuivieRepository extends JpaRepository<Suivie, Integer> {
}