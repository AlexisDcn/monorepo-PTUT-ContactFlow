package isis.projet.backend.controller;

import isis.projet.backend.entity.*;
import isis.projet.backend.service.*;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
@Slf4j
public class SimpleController {
    private final CountryService countryService;
    private final FormulaireService formulaireService;
    private final ContientService contientService;
    private final ProspectService prospectService;
    private final SalonService salonService;
    private final InfoService infoService;
    private final SuivieService suivieService;
    private final ChampService champService;
    private final FormationService formationService;

    public SimpleController(CountryService countryService, FormulaireService formulaireService, ContientService contientService, ProspectService prospectService, SalonService salonService, InfoService infoService, SuivieService suivieService, ChampService champService, FormationService formationService) {
        this.countryService = countryService;
        this.formulaireService = formulaireService;
        this.contientService = contientService;
        this.prospectService = prospectService;
        this.salonService = salonService;
        this.infoService = infoService;
        this.suivieService = suivieService;
        this.champService = champService;
        this.formationService = formationService;
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

    // Route qui permet de récupérer tous les formulaires actifs

    @GetMapping("/formulairesActifs")
    public Map<String, List<Formulaire>> formulairesActifs() {
        log.info("Service formulairesActifs");
        return Map.of("formulaire", formulaireService.formulairesActifs());
    }

    // Route qui permet de récupérer tous les champs appartenant à un formulaire x passé en paramètre

    @GetMapping("/getChampFormu/{idForm}")
    public Map<String, List<Champ>> getChampFormu(@PathVariable("idForm") Integer idForm) {
        log.info("Service getChampFormu, idForm = " + idForm);
        return Map.of("champs", contientService.champsFormu(idForm));
    }

    // Route qui permet de récupérer le nombre de personnes par un salon spécifié

    @GetMapping("/getCountSalon/{idSalon}")
    public Map<String, Integer> getCountSalon(@PathVariable("idSalon") Integer idSalon) {
        log.info("Service getCountSalon" + idSalon);
        return Map.of("Salon", prospectService.prospectSalon(idSalon));
    }

    // Route qui permet de récupérer le nombre de personnes par salon (en prenant tous les salons)

    @GetMapping("/getCountSalon")
    // La on va redonner un JSON qui a pour nom Salon dans lequel est stocké un dictionnaire
    // Le dictionnaire ressemble à "Salon_id" : nb_Personnes
    public Map<String, Map<String, Integer>> getCountSalon() {
        log.info("Service getCountSalon");
        List<Salon> salons = salonService.getSalon();
        return Map.of("salon", prospectService.prospectSalonGlobal(salons));
    }

    @GetMapping("/getPersParAnnee")
    public Map<String, Map<String, Integer>> getPersParAnnee() {
        log.info("Service getPersParAnnee");
        return Map.of("salon", prospectService.prospectAnnee());
    }

    @DeleteMapping("/deleteSalon/{idSalon}")
    public void deleteSalon(@PathVariable("idSalon") Integer idSalon) {

        // Suppression de tout ce qui concerne les liaisons
        infoService.deleteInfo(idSalon);
        suivieService.deleteSuivi(idSalon);
        contientService.deleteContient(idSalon);

        // Supprime tous les trucs physiques
        formulaireService.deleteFormulaire(idSalon);
        prospectService.deleteProspect(idSalon);

        // Enfin, suppression du salon :)
        salonService.deleteSalon(idSalon);
    }

    @DeleteMapping("/deleteFormation/{idFormation}")
    public void deleteFormation(@PathVariable("idFormation") Integer idFormation) {
        suivieService.deleteSuiviFormation(idFormation);
        formationService.deleteFormation(idFormation);
    }

    @GetMapping("/getProspectsSalon/{idSalon}")
    public List<Prospect> getProspectsSalon(@PathVariable("idSalon") Integer idSalon) {
        log.info("Service getProspectsSalon" + idSalon);
        return prospectService.prospectSalonNom(idSalon);
    }

    @GetMapping("/getProspectsSalon")
    // La on va redonner un JSON qui a pour nom Salon dans lequel est stocké un dictionnaire
    // Le dictionnaire ressemble à "Salon_id" : nb_Personnes
    public Map<String, Map<String, List<Prospect>>> getProspectsSalon() {
        log.info("Service getProspectsSalon");
        List<Salon> salons = salonService.getSalon();
        return Map.of("salon", prospectService.prospectSalonGlobalNom(salons));
    }

    @PutMapping("/archiver/{idSalon}")
    public void archiver(@PathVariable("idSalon") Integer idSalon) {
        formulaireService.desactiver(idSalon);
        salonService.archiver(idSalon);
    }

    @PutMapping("/modifNomSalon/{idSalon}")
    public void modifNomSalon(@PathVariable("idSalon") Integer idSalon, @RequestBody Map<String, String> payload) {
        String nvNom = payload.get("nom");
        salonService.modifNom(idSalon, nvNom);
    }

    @GetMapping("/getProspectsByYear/{year}")
    public List<Prospect> getProspectsByYear(@PathVariable("year") Integer year) {
        log.info("Service getProspectsByYear for year: " + year);
        return prospectService.getProspectsByYear(year);
    }

    @GetMapping("/getAvailableYears")
    public List<Integer> getAvailableYears() {
        log.info("Service getAvailableYears");
        return prospectService.getAvailableYears();
    }

    @PostMapping("/createFormulaire")
    public Formulaire createFormulaire(@RequestBody Formulaire formulaire) {
        log.info("Creating formulaire");
        return formulaireService.createFormulaire(formulaire);
    }

    @PostMapping("/createChamp")
    public Champ createChamp(@RequestBody Champ champ) {
        log.info("Creating champ");
        return champService.createChamp(champ);
    }

    @PostMapping("/createContient")
    public Contient createContient(@RequestBody Map<String, Integer> request) {
        Integer champId = request.get("champId");
        Integer formulaireId = request.get("formulaireId");

        Champ champ = new Champ();
        champ.setIdChamp(champId);

        Formulaire formulaire = new Formulaire();
        formulaire.setIdForm(formulaireId);

        log.info("Creating contient");
        return contientService.createContient(champ, formulaire);
    }

    @GetMapping("/getSalonNom/{idSalon}")
    public String getSalonNom(@PathVariable("idSalon") Integer idSalon) {
        return formulaireService.getSalonNom(idSalon);
    }
}


