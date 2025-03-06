package isis.projet.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Suivie {

    @EmbeddedId
    private SuivieId id;

    @ManyToOne
    @MapsId("prospect")
    @JoinColumn(name = "id_prospect")
    private Prospect prospect;

    @ManyToOne
    @MapsId("formation")
    @JoinColumn(name = "id")
    private Formation formation;
}
