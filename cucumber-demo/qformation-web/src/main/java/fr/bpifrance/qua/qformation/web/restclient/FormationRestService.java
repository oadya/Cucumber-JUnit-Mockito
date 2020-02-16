package fr.bpifrance.qua.qformation.web.restclient;


import fr.bpifrance.qua.qformation.web.model.FormationJson;
import fr.bpifrance.qua.qformation.web.model.SalleJson;
import fr.bpifrance.qua.qformation.web.model.StagiaireJson;
import fr.bpifrance.qua.qformation.web.model.SujetJson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Component
public interface FormationRestService {
    @RequestMapping(method = RequestMethod.GET, value = "/formations", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FormationJson> getFormations();

    @RequestMapping(method = RequestMethod.GET, value = "/formations/{idFormation}", produces = MediaType.APPLICATION_JSON_VALUE)
    FormationJson getFormation(@PathVariable("idFormation") long idFormation);

    @RequestMapping(method = RequestMethod.DELETE, value = "/formations/{idFormation}", produces = MediaType.APPLICATION_JSON_VALUE)
    FormationJson supprimerFormation(@PathVariable("idFormation") long idFormation);

    @RequestMapping(method = RequestMethod.GET, value = "/formations/{idFormation}/stagiaires", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StagiaireJson> getStagiaires(@PathVariable("idFormation") long idFormation);


    @RequestMapping(method = RequestMethod.DELETE, value = "/formations/{idFormation}/stagiaire/{matricule}", produces = MediaType.APPLICATION_JSON_VALUE)
    FormationJson desinsrireFormation(@PathVariable("idFormation") long idFormation, @PathVariable("matricule") String matricule );


    @RequestMapping(method = RequestMethod.POST, value = "/formations/{idFormation}/stagiaire", consumes = MediaType.APPLICATION_JSON_VALUE)
    void ajouterStagiaire(@PathVariable("idFormation") long idFormation,StagiaireJson stagiaire);

    @RequestMapping(method = RequestMethod.GET, value = "/salles", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SalleJson> getSalles();

    @RequestMapping(method = RequestMethod.GET, value = "/sujets", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SujetJson> getSujets();

    @RequestMapping(method = RequestMethod.POST, value = "/salles", consumes = MediaType.APPLICATION_JSON_VALUE)
    void ajouterSalle(SalleJson salle);

    @RequestMapping(method = RequestMethod.POST, value = "/sujets", consumes = MediaType.APPLICATION_JSON_VALUE)
    void ajouterSujet(SujetJson sujet);

    @RequestMapping(method = RequestMethod.POST, value = "/formations", consumes = MediaType.APPLICATION_JSON_VALUE)
    void planifierFormation(FormationJson formation);

    @RequestMapping(method = RequestMethod.PUT, value = "/formations/{idFormation}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void modifierFormation(@PathVariable("idFormation") long idFormation,FormationJson formation);
}
