package isis.projet.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ProspectChamp {
    // Id de l'entrée dans la table ChampForm (clé primaire, auto-générée)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    private String valeur_txt;

    @Column(unique=true)
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    private Integer valeur_num;



}
