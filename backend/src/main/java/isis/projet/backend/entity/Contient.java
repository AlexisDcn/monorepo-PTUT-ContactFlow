package isis.projet.backend.entity;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter @ToString
@Entity
public class Contient {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_champ")
    private Champ champ;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_form")
    private Formulaire formulaire;
}
