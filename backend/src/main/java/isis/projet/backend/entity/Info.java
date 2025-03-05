package isis.projet.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
public class Info {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_prospect")
    private Prospect prospect;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_champ")
    private Champ champ;

    private String valeurTxt;
    private Integer valeurNum;
}