package isis.projet.backend.dao;

import isis.projet.backend.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Integer> {
}
