package isis.projet.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@ToString
public class InfoId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_prospect")
    private Prospect prospect;

    @ManyToOne
    @JoinColumn(name = "id_champ")
    private Champ champ;

}
