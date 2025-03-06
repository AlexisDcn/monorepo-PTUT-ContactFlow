package isis.projet.backend.dao;

import isis.projet.backend.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalonRepository extends JpaRepository<Salon, Integer> {
}