package fr.bpifrance.qua.qformation.dao.models;

import fr.bpifrance.qua.qformation.domaine.Formation;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Formation")
@Data
public class FormationEntity extends Formation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
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

	public FormationEntity(){}

}
