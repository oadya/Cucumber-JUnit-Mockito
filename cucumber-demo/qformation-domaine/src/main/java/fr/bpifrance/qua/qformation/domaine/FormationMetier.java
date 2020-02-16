package fr.bpifrance.qua.qformation.domaine;

import fr.bpifrance.qua.qformation.domaine.exception.FormationFermeeException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FormationMetier implements IFormationMetier {

	private FormationRepositoryPort formationRepositoryPort;
	public FormationMetier(FormationRepositoryPort formationRepositoryPort ){
		this.formationRepositoryPort = formationRepositoryPort;
	}

	public Formation planifierFormation(Formation pFormation) {
		//RG: Le sujet de la formation doit exister dans la base
		if(this.obtenirSujet(pFormation.getCode()) != null) {
			if(formationRepositoryPort.isSalleDisponible(pFormation.getSalle(),pFormation.getDate())) { 		//RG: disponibilité de la salle
				pFormation.setStatut("Nouvelle"); //RG: Le statut est Nouvelle
				formationRepositoryPort.planifierFormation(pFormation);
				return pFormation;
			}else{
				System.out.println("La salle n'est pas disponible");
				return null;
			}
		}else{
			System.out.println("Le code du sujet n'existe pas");
			return null;
		}

	}

	public List<Formation> obtenirFormations() {
		return formationRepositoryPort.obtenirFormations();
	}

	public Formation consulterFormation(long pIdFormation) {
		Formation lFormation = formationRepositoryPort.obtenirFormation(pIdFormation);
		lFormation.setStagiaires(formationRepositoryPort.obtenirStagiairesFormation(pIdFormation));
		return lFormation;
	}

	public void annulerFormation(long pIdFormation) {
		formationRepositoryPort.supprimerFormation(pIdFormation);
	}

	public void annulerFormation(String pCode) {
		formationRepositoryPort.supprimerFormation(pCode);
	}

	public Formation inscrireFormation(long pIdFormation, Collaborateur pCollaborateur) throws FormationFermeeException {
		Formation pFormation = formationRepositoryPort.obtenirFormation(pIdFormation);
		if(pFormation.getNombreInscrits() < pFormation.getMaximumInscrits()) {
			Stagiaire lStagiaire = new Stagiaire();
			lStagiaire.setMatricule(pCollaborateur.getMatricule());
			lStagiaire.setNom(pCollaborateur.getNom());
			lStagiaire.setIdFormation(pFormation.getId());
			formationRepositoryPort.inscrireStagiaire(lStagiaire);
			pFormation.setNombreInscrits(pFormation.getNombreInscrits() + 1);
			if(pFormation.getNombreInscrits() == pFormation.getMaximumInscrits()) {
				pFormation.setOuverte(false);
			}
			formationRepositoryPort.modifierFormation(pFormation);
			return pFormation;
		}else {
			throw new FormationFermeeException("inscription fermée",300);}
	}

	public List<Stagiaire> obtenirStagiairesFormation(long pIdFormation) {
		return formationRepositoryPort.obtenirStagiairesFormation(pIdFormation);
	}

	public void desinscrireFormation(long pIdFormation, String matricule) {
		formationRepositoryPort.retirerStagiaire(pIdFormation, matricule);
		Formation lFormation = formationRepositoryPort.obtenirFormation(pIdFormation);
		lFormation.setNombreInscrits(lFormation.getNombreInscrits() - 1);
		if(lFormation.getNombreInscrits() < lFormation.getMaximumInscrits()) {
			lFormation.setOuverte(true);
		}
		formationRepositoryPort.modifierFormation(lFormation);
	}

	public void modifierFormation(Formation pFormation) {
		formationRepositoryPort.modifierFormation(pFormation);
	}

	public Sujet ajouterSujet(Sujet pSujet) throws Exception {
		if(formationRepositoryPort.obtenirSujet(pSujet.getCode()) == null) {
			return formationRepositoryPort.ajouterSujet(pSujet);
		}else{
			throw new Exception("Le sujet existe déjà");
		}
	}

	public List<Sujet> obtenirSujets() {
		return formationRepositoryPort.obtenirSujets();
	}

	public Salle ajouterSalle(Salle pSalle) {
		return formationRepositoryPort.ajouterSalle(pSalle);
	}


	public List<Salle> obtenirSalles() {
		return formationRepositoryPort.obtenirSalles();
	}

	public Salle obtenirSalle(String nomSalle) {
		return formationRepositoryPort.obtenirSalle(nomSalle);
	}

	@Override
	public Sujet obtenirSujet(String code) {
		return formationRepositoryPort.obtenirSujet(code);
	}

}
