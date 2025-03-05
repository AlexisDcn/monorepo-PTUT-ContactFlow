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
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Champ {
    // Id de l'entrée dans la table Champ (clé primaire, auto-générée)
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String type;

    private String placeholder;

    @ManyToMany
    private List<Prospect> id_prospect = new ArrayList<>();

    @ManyToMany
    private List<Formulaire> formulaires = new ArrayList<>();
}
