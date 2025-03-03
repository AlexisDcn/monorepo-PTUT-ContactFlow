package isis.projet.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Prospect {

    @Column(unique=true)
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    private String valeur_txt;

    @Column(unique=true)
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    private Integer valeur_num;

    @ManyToMany
    private Formulaire formulaire;
}
