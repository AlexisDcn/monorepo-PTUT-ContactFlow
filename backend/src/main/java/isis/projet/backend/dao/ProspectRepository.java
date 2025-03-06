package isis.projet.backend.dao;
import isis.projet.backend.entity.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProspectRepository extends JpaRepository<Prospect, Integer> {
}
