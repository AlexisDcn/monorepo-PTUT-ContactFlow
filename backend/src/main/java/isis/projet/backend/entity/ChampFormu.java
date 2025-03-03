package isis.projet.backend.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ChampFormu {

    // Id de l'entrée dans la table ChampForm (clé primaire, auto-générée)
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    private List<Champ> id_champ = new ArrayList<>();

    @ManyToMany
    private List<Formulaire> id_formu = new ArrayList<>();


}
