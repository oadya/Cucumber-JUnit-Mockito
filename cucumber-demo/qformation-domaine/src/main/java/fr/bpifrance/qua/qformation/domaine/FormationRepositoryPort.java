package fr.bpifrance.qua.qformation.domaine;

import java.util.Date;
import java.util.List;

public interface FormationRepositoryPort {
	public Formation planifierFormation(Formation pFormation);
	public List<Formation> obtenirFormations();
	public Formation obtenirFormation(long pIdFormation);
	public void supprimerFormation(long pIdFormation);
	public Formation obtenirFormation(String pCode);
	public void supprimerFormation(String pCode);
	public void inscrireStagiaire(Stagiaire lStagiaire);
	public void modifierFormation(Formation pFormation);
	public List<Stagiaire> obtenirStagiairesFormation(long pIdFormation);
	public Stagiaire obtenirStagiaire(long pIdFormation, String pMatricule);
	public void retirerStagiaire(long pIdFormation, String pMatricule);
	public Sujet ajouterSujet(Sujet pSujet);
	public List<Sujet> obtenirSujets();
	public Sujet obtenirSujet(String code);
	public Salle ajouterSalle(Salle pSalle);
	public List<Salle> obtenirSalles();
	public Salle obtenirSalle(String nomSalle);
	public boolean isSalleDisponible(String salle, Date date);
}
