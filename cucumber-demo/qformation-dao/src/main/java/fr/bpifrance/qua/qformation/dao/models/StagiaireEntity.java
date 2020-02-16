package fr.bpifrance.qua.qformation.dao.models;

import fr.bpifrance.qua.qformation.domaine.Stagiaire;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Stagiaire")
@Data
public class StagiaireEntity extends Stagiaire {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
	private long id;
	private String matricule;
	private String nom;
	private long idFormation;
	public StagiaireEntity(){
	}

}
