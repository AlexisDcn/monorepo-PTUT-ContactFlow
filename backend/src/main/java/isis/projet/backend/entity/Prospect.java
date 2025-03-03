package isis.projet.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Prospect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    @NonNull // Lombok : génère un constructeur avec ce paramètre
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    @NotEmpty // Validation : ne doit pas être vide
    private String nom;

    @Column(unique=true)
    @NonNull // Lombok : génère un constructeur avec ce paramètre
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    @NotEmpty // Validation : ne doit pas être vide
    private String prenom;

    @Column(unique=true)
    @NonNull // Lombok : génère un constructeur avec ce paramètre
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    @NotEmpty // Validation : ne doit pas être vide
    private long numTel;

    @Column(unique=true)
    @NonNull // Lombok : génère un constructeur avec ce paramètre
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    @NotEmpty // Validation : ne doit pas être vide
    private String email;

    @Column(unique=true)
    @NonNull // Lombok : génère un constructeur avec ce paramètre
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    @NotEmpty // Validation : ne doit pas être vide
    private String ville;

    @Column(unique=true)
    @NonNull // Lombok : génère un constructeur avec ce paramètre
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    @NotEmpty // Validation : ne doit pas être vide
    private String departement;

    @Column(unique=true)
    @NonNull // Lombok : génère un constructeur avec ce paramètre
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    @NotEmpty // Validation : ne doit pas être vide
    private long codePostal;

    @Column(unique=true)
    @NonNull // Lombok : génère un constructeur avec ce paramètre
    @NotBlank // Validation : ne doit pas contenir uniquement des espaces
    @NotEmpty // Validation : ne doit pas être vide
    private String etablissement;
    

    @ManyToOne
    private Salon id_salon;

    @ManyToMany
    private List<Formation> id_forma = new ArrayList<>();


    @ManyToMany
    private List<Champ> id_champ = new ArrayList<>();
}
