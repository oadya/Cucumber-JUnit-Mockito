package fr.bpifrance.qua.qformation.api.resources;

import java.util.Collection;
import java.util.List;

import fr.bpifrance.qua.qformation.domaine.exception.FormationFermeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import fr.bpifrance.qua.qformation.domaine.*;


@RestController
@RequestMapping("/api/")
public class FormationResource {

	@Autowired
	private  FormationMetier formationMetier;
	
	@RequestMapping(value = "/salles", method = RequestMethod.GET)
	public List<Salle> obtenirSalles(){
		return formationMetier.obtenirSalles();
	}
	
	 @RequestMapping(value = "/salles", method = RequestMethod.POST)
	 public ResponseEntity<?> creerSalle(@RequestBody Salle pSalle,
			 UriComponentsBuilder ucBuilder) {
	        formationMetier.ajouterSalle(pSalle);
	        HttpHeaders headers = new HttpHeaders();
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	 
	@RequestMapping(value = "/sujets", method = RequestMethod.GET)
	public List<Sujet> obtenirSujets(){
		return formationMetier.obtenirSujets();
	}

	 @RequestMapping(value = "/sujets", method = RequestMethod.POST)
	 public ResponseEntity<?> creerSujet(@RequestBody Sujet pSujet,
			 UriComponentsBuilder ucBuilder) throws Exception {
	        formationMetier.ajouterSujet(pSujet);
	        HttpHeaders headers = new HttpHeaders();
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	
	@RequestMapping(value = "/formations", method = RequestMethod.GET)
	public List<Formation> obtenirFormations(){
		List<Formation> lFormations= formationMetier.obtenirFormations();
		return lFormations;
	}
	
	@RequestMapping(value = "/formations/{id}", method = RequestMethod.GET)
	public Formation obtenirFormation(@PathVariable("id") int pIdFormation){
		return formationMetier.consulterFormation(pIdFormation);
	}
	
	@RequestMapping(value = "/formations/{id}", method = RequestMethod.PUT)
	public void modifierFormation(@PathVariable("id") int pIdFormation, @RequestBody Formation pFormation,
			 UriComponentsBuilder ucBuilder){
		formationMetier.modifierFormation(pFormation);
	}
	
	
	@RequestMapping(value = "/formations/{id}/stagiaires", method = RequestMethod.GET)
	public List<Stagiaire> obtenirStagiairesFormation(@PathVariable("id") int pIdFormation){
		return formationMetier.consulterFormation(pIdFormation).getStagiaires();
	}
	
	
	@RequestMapping(value = "/formations/{id}", method = RequestMethod.DELETE)
	public void annulerFormation(@PathVariable("id") int pIdFormation){
		formationMetier.annulerFormation(pIdFormation);
	}
	
	
	 @RequestMapping(value = "/formations", method = RequestMethod.POST)
	 public ResponseEntity<?> creerFormation(@RequestBody Formation formation,
			 UriComponentsBuilder ucBuilder) {
	        Formation pformation=formationMetier.planifierFormation(formation);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/api/formations/{id}").buildAndExpand(pformation.getId()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	 
	 @RequestMapping(value = "/formations/{id}/stagiaire", method = RequestMethod.POST)
	 public ResponseEntity<?> inscriptionFormation(@RequestBody Collaborateur collaborateur,
			 UriComponentsBuilder ucBuilder,@PathVariable("id") int pIdFormation) {
	        try{
	        	formationMetier.inscrireFormation(pIdFormation, collaborateur);
	          	 HttpHeaders headers = new HttpHeaders();
			        return new ResponseEntity<String>(headers, HttpStatus.CREATED);	
	        }catch(FormationFermeeException e){
	        	return new ResponseEntity<Object>(new FormationFermeeException("fermée",300), HttpStatus.CONFLICT);
	        }

	 }
	 
	   @RequestMapping(value = "/formations/{id}/stagiaire/{matricule}", method = RequestMethod.DELETE)
	   public void desinscrireFormation(@PathVariable("id") int pIdFormation, @PathVariable("matricule") String matricule){
	    	formationMetier.desinscrireFormation(pIdFormation, matricule);
	    }
}
