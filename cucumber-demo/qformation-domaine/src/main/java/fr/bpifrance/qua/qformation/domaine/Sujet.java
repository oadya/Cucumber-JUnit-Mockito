package fr.bpifrance.qua.qformation.domaine;

import lombok.Data;

@Data
public class Sujet {
	private String code;
	private String libelle;
	private String formateur;
	private String prerequis;

}
