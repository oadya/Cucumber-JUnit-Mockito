package fr.bpifrance.qua.qformation.domaine.test;

import fr.bpifrance.qua.qformation.domaine.Formation;
import fr.bpifrance.qua.qformation.domaine.FormationMetier;
import fr.bpifrance.qua.qformation.domaine.FormationRepositoryPort;
import fr.bpifrance.qua.qformation.domaine.Sujet;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Etantdonné;
import cucumber.api.java.fr.Quand;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;

public class FormationDefinitions {
	//On mocke la base de données
	private static FormationRepositoryPort formationRepositoryPort= Mockito.mock(FormationRepositoryPort.class);

	private FormationMetier formationMetier;
	private Formation formation;
	private Sujet sujet;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public FormationDefinitions(){
		formationMetier = new FormationMetier(formationRepositoryPort);
	}

	@Etantdonné("^je suis un administrateur$")
	public void je_suis_un_administrateur() throws Throwable {
		System.out.println("je suis un admin");

	}

	@Etantdonné("^il existe un sujet de formation avec le code \"([^\"]*)\"$")
	public void il_existe_un_sujet_de_formation_avec_le_code(String code) throws Throwable {
		Sujet sujet = new Sujet();
		sujet.setCode(code);
		Mockito.when(formationRepositoryPort.obtenirSujet(code)).thenReturn(sujet);
	}

	@Quand("^je planifie la formation suivante$")
	public void je_planifie_la_formation_suivante(List<Formation>  formations) throws Throwable {
		formation = formationMetier.planifierFormation(formations.get(0));
	}

	@Alors("^il existe une formation avec les informations suivantes$")
	public void il_existe_une_formation_avec_les_informations_suivantes(List<Formation> formationsExpected) throws Throwable {
		Mockito.verify(formationRepositoryPort).planifierFormation(formation);
		Formation formationExpected = formationsExpected.get(0);
		assertThat(formation).isEqualToIgnoringNullFields(formationExpected);

	}

	@Etantdonné("^il n'existe pas un sujet de formation avec le code \"([^\"]*)\"$")
	public void il_n_existe_pas_un_sujet_de_formation_avec_le_code(String code) throws Throwable {
		Mockito.when(formationRepositoryPort.obtenirSujet(code)).thenReturn(null);
	}
	@Alors("^le système accepte la création de cette formation$")
	public void le_système_accepte_la_création_de_la_formation_de_cette_formation() throws Throwable {
		Mockito.verify(formationRepositoryPort).planifierFormation(formation);
	}

	@Alors("^le système refuse la création de cette formation$")
	public void le_système_refuse_la_création_de_la_formation_de_cette_formation() throws Throwable {
		Mockito.verify(formationRepositoryPort, never()).planifierFormation(formation);
	}

	@Etantdonné("^la salle \"([^\"]*)\" n'est pas disponible pour la date \"([^\"]*)\"$")
	public void la_salle_n_est_pas_disponible_pour_la_date(String salle, String date) throws Exception {
		Mockito.when(formationRepositoryPort.isSalleDisponible(salle, sdf.parse(date))).thenReturn(false);
	}

	@Etantdonné("^la salle \"([^\"]*)\" est disponible pour la date \"([^\"]*)\"$")
	public void la_salle_est_disponible_pour_la_date(String salle, String date) throws Exception {
		Mockito.when(formationRepositoryPort.isSalleDisponible(salle, sdf.parse(date))).thenReturn(true);
	}

	@Quand("^je crée le sujet de formation suivant$")
	public void jeCréeLeSujetDeFormationSuivant(List<Sujet> sujets)  {
		sujet=sujets.get(0);
		try {
			formationMetier.ajouterSujet(sujet);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	@Alors("^le système accepte la création du sujet$")
	public void leSystèmeAccepteLaCréationDuSujet() {
		Mockito.verify(formationRepositoryPort).ajouterSujet(sujet);
	}

	@Alors("^le système refuse la création du sujet$")
	public void leSystèmeRefuseLaCréationDuSujet() {
		Mockito.verify(formationRepositoryPort, never()).ajouterSujet(sujet);
		//Mockito.
	}
}
