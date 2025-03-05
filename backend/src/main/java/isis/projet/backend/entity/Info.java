package isis.projet.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Info {

    @EmbeddedId
    private InfoId id;

    @ManyToOne
    @MapsId("prospect")
    @JoinColumn(name = "id_prospect")
    private Prospect prospect;

    @ManyToOne
    @MapsId("champ")
    @JoinColumn(name = "id_champ")
    private Champ champ;

    private String valeurTxt;
    private Integer valeurNum;
}
