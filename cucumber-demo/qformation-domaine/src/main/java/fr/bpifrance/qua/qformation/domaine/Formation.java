package fr.bpifrance.qua.qformation.domaine;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Formation {
	private long id;
	private String code;
	private String libelle;
	private Date date;
	private String salle;
	private String formateur;
	private String statut;
	private int maximumInscrits=10;
	private int nombreInscrits=0;
	private boolean ouverte=true;
	private List<Stagiaire> stagiaires;

	public Formation(){
	}
	public Formation(String code, String libelle, Date date, String salle, String formateur, String statut, boolean ouverte, int maximumInscrits, int nombreInscrits ){

		this.code =code;
		this.libelle=libelle;
		this.date=date;
		this.salle=salle;
		this.formateur=formateur;
		this.statut=statut;
		this.ouverte=ouverte;
		this.maximumInscrits=maximumInscrits;
		this.nombreInscrits=nombreInscrits;
	}


}
