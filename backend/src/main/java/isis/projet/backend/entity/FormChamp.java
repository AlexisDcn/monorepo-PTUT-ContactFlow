package isis.projet.backend.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class ChampForm {

    // Id de l'entrée dans la table ChampForm (clé primaire, auto-générée)
    @ManyToMany
    private List<Champ> id_champs = new ArrayList<>();

    @ManyToMany
    private List<Formulaire> id_form = new ArrayList<>();


}
