package isis.projet.backend.controller;

import isis.projet.backend.entity.Formulaire;
import isis.projet.backend.service.CountryService;
import isis.projet.backend.service.FormulaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
@Slf4j
public class SimpleController {
    private final CountryService countryService;
    private final FormulaireService formulaireService;

    public SimpleController(CountryService countryService, FormulaireService formulaireService) {
        this.countryService = countryService;
        this.formulaireService = formulaireService;
    }

    @GetMapping("/hello")
    public Map<String, String> sayHello() {
        log.info("Service Hello World");
        return Map.of("message", "Hello, World !");
    }

    @PutMapping("/combienDePays")
    public Map<String, Long> combienDePays() {
        log.info("Service combienDePays");
        // On renverra un objet JSON de la forme {"combien": 123}
        return Map.of("combien", countryService.combienDePays());
    }

    @GetMapping("/formulairesActifs")
    public Map<String, List<Formulaire>> formulairesActifs() {
        log.info("Service formulairesActifs");
        return Map.of("formulaire", formulaireService.formulairesActifs());
    }

}


