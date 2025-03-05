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
public class ContientId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_champ")
    private Champ champ;

    @ManyToOne
    @JoinColumn(name = "id_form")
    private Formulaire formulaire;

}
