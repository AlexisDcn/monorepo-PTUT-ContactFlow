package isis.projet.backend.dao;

import isis.projet.backend.entity.Formation;
import isis.projet.backend.entity.Formulaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormulaireRepository extends JpaRepository<Formulaire, Integer> {
}
