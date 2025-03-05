package isis.projet.backend.dao;

import isis.projet.backend.entity.Champ;
import isis.projet.backend.entity.Contient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContientRepository extends JpaRepository<Contient, Integer> {
}
