package isis.projet.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
public class Suivie {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_prospect")
    private Prospect prospect;

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private Formation formation;
}