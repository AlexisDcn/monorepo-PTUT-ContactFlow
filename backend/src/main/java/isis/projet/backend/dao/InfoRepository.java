package isis.projet.backend.dao;

import isis.projet.backend.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InfoRepository extends JpaRepository<Info, Integer> {
    @Modifying
    @Query("DELETE FROM Info i WHERE i.prospect.salon.idSalon = :idSalon")
    void deleteInfo(Integer idSalon);
}
