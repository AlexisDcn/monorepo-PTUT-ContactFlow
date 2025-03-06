package isis.projet.backend.dao;

import isis.projet.backend.entity.Contient;
import isis.projet.backend.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Integer> {
}
