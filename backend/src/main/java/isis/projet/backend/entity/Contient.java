package isis.projet.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Contient {

    @EmbeddedId
    private ContientId id;

    @ManyToOne
    @MapsId("champ")
    @JoinColumn(name = "id_champ")
    private Champ champ;

    @ManyToOne
    @MapsId("formulaire")
    @JoinColumn(name = "id_form")
    private Formulaire formulaire;
}
