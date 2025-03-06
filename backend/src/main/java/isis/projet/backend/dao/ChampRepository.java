package isis.projet.backend.dao;

import isis.projet.backend.entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampRepository extends JpaRepository<Champ, Integer> {
}
