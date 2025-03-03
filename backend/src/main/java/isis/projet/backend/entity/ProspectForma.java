package isis.projet.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ProspectForma {
    // Id de l'entrée dans la table ChampForm (clé primaire, auto-générée)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    private List<Prospect> id_prospect = new ArrayList<>();

    @ManyToMany
    private List<Formation> id_forma = new ArrayList<>();
}
