package isis.projet.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Salon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

@NonNull
@NotBlank
@NotEmpty
    private String name ;

@OneToMany
    private List<Formulaire> formulaire = new ArrayList<Formulaire>() ;


}
