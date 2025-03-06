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
    private int idProspect;
    private String nom;
    private String prenom;
    private String numTel;
    private String mail;
    private String ville;
    private String departement;
    private String codePostal;

    @ManyToOne
    @JoinColumn(name = "id_salon", nullable = false)
    private Salon salon;
}
