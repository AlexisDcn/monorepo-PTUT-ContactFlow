package isis.projet.backend.entity;

import jakarta.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @ToString
@Entity
public class Champ {
    // Id de l'entrée dans la table Champ (clé primaire, auto-générée)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChamp;
    private String type;
    private String nom;
    private String placeholders;
    private Boolean defaut;
}
