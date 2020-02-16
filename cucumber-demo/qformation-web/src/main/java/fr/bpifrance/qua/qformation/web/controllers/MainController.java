package fr.bpifrance.qua.qformation.web.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.validation.Valid;
import fr.bpifrance.qua.qformation.web.model.FormationJson;
import fr.bpifrance.qua.qformation.web.model.SalleJson;
import fr.bpifrance.qua.qformation.web.model.StagiaireJson;
import fr.bpifrance.qua.qformation.web.model.SujetJson;
import fr.bpifrance.qua.qformation.web.restclient.FormationRestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {
	private String urlString;
	Properties prop;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	FormationRestService restClient;
	public  MainController(FormationRestService restClient) throws IOException {
		prop= new Properties();
		prop.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
		this.urlString=prop.getProperty("formation.api.url");
		this.restClient=restClient;
	}
	@RequestMapping(value = { "/ressources"}, method = RequestMethod.GET)
	public String afficherRessources(ModelMap model)  {
		return "ressources";
	}
	
	@RequestMapping(value = { "/sujets"}, method = RequestMethod.GET)
	public String listeSujets(ModelMap model) {
		if (model.get("sujet") == null) {
			model.addAttribute("sujet", new SujetJson());
		}
		model.addAttribute("jsonSujets", restClient.getSujets());
		return "sujets";
	}
	
	@RequestMapping(value = { "/salles"}, method = RequestMethod.GET)
	public String listeSalles(ModelMap model)  {
		if (model.get("salle") == null) {
			model.addAttribute("salle", new SalleJson());
		}
		model.addAttribute("jsonSalles", restClient.getSalles());
		return "salles";
	}

	@RequestMapping(value = { "/salles/ajout"}, method = RequestMethod.POST)
	public String ajoutSalle(@Valid @ModelAttribute(value="salle") final SalleJson pSalle,
            final BindingResult pBindingResult, final ModelMap pModel)  {
	       if (pModel.get("salle") == null) {
	            pModel.addAttribute("salle", new SalleJson());
	        }

	        if (!pBindingResult.hasErrors()) {
	        	restClient.ajouterSalle(pSalle);
	        	 pModel.addAttribute("salle", new SalleJson());
	        	return listeSalles(pModel);
	        }
	        else{
	        	return "salles";
	        }     
	
	}

	@RequestMapping(value = { "/sujets/ajout"}, method = RequestMethod.POST)
	public String ajoutSujet(@Valid @ModelAttribute(value="sujet") final SujetJson pSujet,
            final BindingResult pBindingResult, final ModelMap pModel) {
	       if (pModel.get("sujet") == null) {
	            pModel.addAttribute("sujet", new SujetJson());
	        }

	        if (!pBindingResult.hasErrors()) {
	        	restClient.ajouterSujet(pSujet);
	        	 pModel.addAttribute("sujet", new SujetJson());
	        	return listeSujets(pModel);
	        }
	        else{
	        	return "sujets";
	        }     
	
	}


	@RequestMapping(value = { "/formations/liste"}, method = RequestMethod.GET)
	public String listeFormations(ModelMap model)  {
		model.addAttribute("jsonFormations", restClient.getFormations());
		return "formations-liste";
	}

	@RequestMapping(value = { "/formations/ajout"}, method = RequestMethod.GET)
	public String afficherFormulaireNouvelleFormation(ModelMap model)   {
		if(restClient.getSujets().size() > 0){
			if(restClient.getSalles().size() > 0){
				if (model.get("formation") == null) {
					model.addAttribute("formation", new FormationJson());
				}
				model.addAttribute("jsonSalles", restClient.getSalles());
			    model.addAttribute("jsonSujets", restClient.getSujets());
				return "formations-ajout";
			}else {
				model.addAttribute("messagederreur","Il n'y a aucune salle de formations. Merci de contacter un administrateur!");
				return "formations-erreur";	
			}
		}else {
			model.addAttribute("messagederreur","Il n'y a aucun sujet de formations. Merci de contacter un administrateur!");
			return "formations-erreur";
		}
	}

	@RequestMapping(value = { "/formations/modification"}, method = RequestMethod.GET)
	public String afficherFormulaireFormation(@RequestParam(value="id") final Integer pIdFormation, final ModelMap model)  {
		if (model.get("formation") == null) {
			model.addAttribute("formation", new FormationJson());
			}
		if (model.get("stagiaire") == null) {
			model.addAttribute("stagiaire", new StagiaireJson());
			}

   	 	model.addAttribute("formation", restClient.getFormation(pIdFormation));
		model.addAttribute("jsonStagiaires", restClient.getStagiaires(pIdFormation));
		model.addAttribute("jsonSalles", restClient.getSalles());
		return "formations-modification";
	}
	
	@RequestMapping(value = { "/formations/inscription"}, method = RequestMethod.POST)
	public String inscriptionFormation(@Valid @ModelAttribute(value="stagiaire") final StagiaireJson pStagiaire,
            final BindingResult pBindingResult, final ModelMap pModel)  {
	       if (pModel.get("stagire") == null) {
	            pModel.addAttribute("stagiaire", new StagiaireJson());
	        }

	        if (!pBindingResult.hasErrors()) {
	        	 restClient.ajouterStagiaire( pStagiaire.getIdFormation(),pStagiaire);
	        	return afficherFormulaireFormation((int) pStagiaire.getIdFormation(), pModel);
	        }
	        else{
	        	return "formations-liste";
	        }

	}

	
	@RequestMapping(value = { "/formations/ajout"}, method = RequestMethod.POST)
	public String planifierFormation(@Valid @ModelAttribute(value="formation") final FormationJson pFormation,
            final BindingResult pBindingResult, final ModelMap pModel) throws   NumberFormatException, ParseException {
	       if (pModel.get("formation") == null) {
	            pModel.addAttribute("formation", new FormationJson());
	        }

	        if (!pBindingResult.hasErrors()) {
				restClient.planifierFormation(pFormation);
	        	return listeFormations(pModel);
	        }
	        else{
	        	return "formations-ajout";
	        }      
	}

	@RequestMapping(value = { "/formations/modification"}, method = RequestMethod.POST)
	public String modifierFormation(@Valid @ModelAttribute(value="formation") final FormationJson pFormation,
            final BindingResult pBindingResult, final ModelMap pModel) throws  NumberFormatException, ParseException {
	       if (pModel.get("formation") == null) {
	            pModel.addAttribute("formation", new FormationJson());
	        }

	        if (!pBindingResult.hasErrors()) {
	        	restClient.modifierFormation(pFormation.getId(), pFormation);
	        	return listeFormations(pModel);
	        }
	        else{
	        	return listeFormations(pModel);
	        }      
	}
	@RequestMapping(value = { "/formations/annulation"}, method = RequestMethod.POST)
	public String annulerFormation(@RequestParam(value="id") final int pIdFormation, 
             final ModelMap pModel) {
			restClient.supprimerFormation(pIdFormation);
	        return listeFormations(pModel);
	}
	
	@RequestMapping(value = { "/formations/desinscrire"}, method = RequestMethod.GET)
	public String desinscrireFormation(@RequestParam(value="idFormation") final Integer pIdFormation, @RequestParam(value="matricule") String pMatricule, final ModelMap pModel)  {
		restClient.desinsrireFormation(pIdFormation, pMatricule);
    	return afficherFormulaireFormation(pIdFormation, pModel);		
	}
	
    @RequestMapping(value = "/formations", method = RequestMethod.GET)
    public String welcomeFormationPage() {
    	return "formations-index";
    }
    
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomePage() {
    	return "index";
    }

}
