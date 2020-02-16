package fr.bpifrance.qua.qformation.dao.models;

import fr.bpifrance.qua.qformation.domaine.Sujet;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Sujet", uniqueConstraints={@UniqueConstraint(columnNames={"code"})})
@Data
public class SujetEntity extends Sujet{
	@Id
	@Column(name = "code")
	private String code;
	private String libelle;
	private String formateur;
	private String prerequis;
	public SujetEntity() {}

}
