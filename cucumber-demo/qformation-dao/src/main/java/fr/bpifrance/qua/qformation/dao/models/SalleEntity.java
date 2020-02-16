package fr.bpifrance.qua.qformation.dao.models;

import fr.bpifrance.qua.qformation.domaine.Salle;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Salle")
@Data
public class SalleEntity extends Salle {
	@Id
	@Column(name = "salle")
	private String salle;
	private Integer capacite;
	public SalleEntity() {}

}
