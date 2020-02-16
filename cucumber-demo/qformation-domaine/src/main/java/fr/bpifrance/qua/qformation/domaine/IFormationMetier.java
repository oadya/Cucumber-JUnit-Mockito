package fr.bpifrance.qua.qformation.domaine;

import fr.bpifrance.qua.qformation.domaine.exception.FormationFermeeException;

import java.util.List;

public interface IFormationMetier {
	public Formation planifierFormation(Formation pFormation);
	public void modifierFormation(Formation pFormation);
	public List<Formation> obtenirFormations();
	public Formation consulterFormation(long pIdFormation);
	public void annulerFormation(long pIdFormation);
	public void annulerFormation(String pCode);
	public Formation inscrireFormation(long pIdFormation, Collaborateur pStagiaire) throws FormationFermeeException;
	public void desinscrireFormation(long pIdFormation, String matricule);
	public List<Stagiaire> obtenirStagiairesFormation(long pIdFormation);
	public Sujet ajouterSujet(Sujet pSujet) throws Exception;
	public List<Sujet> obtenirSujets();
	public Salle ajouterSalle(Salle pSalle);
	public List<Salle> obtenirSalles();
	public Salle obtenirSalle(String nomSalle);
	public Sujet obtenirSujet(String code);
}
