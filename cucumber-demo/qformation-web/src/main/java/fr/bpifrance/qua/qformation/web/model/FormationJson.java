package fr.bpifrance.qua.qformation.web.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@Data
public class FormationJson {
    private long id;
    private String code;
    private String libelle;
    private Date date;
    private String salle;
    private String formateur;
    private String statut;
    private int maximumInscrits;
    private int nombreInscrits;
    private boolean ouverte;
    private List<StagiaireJson> stagiaires = new ArrayList<StagiaireJson>();

}
