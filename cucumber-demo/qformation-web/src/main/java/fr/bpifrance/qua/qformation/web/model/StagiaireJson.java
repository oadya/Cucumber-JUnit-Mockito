package fr.bpifrance.qua.qformation.web.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class StagiaireJson {
    private long idFormation;
    private String matricule;
    private String nom;

}
