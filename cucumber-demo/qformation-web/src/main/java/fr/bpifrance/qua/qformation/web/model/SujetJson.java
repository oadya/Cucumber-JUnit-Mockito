package fr.bpifrance.qua.qformation.web.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class SujetJson {
    private String code;
    private String libelle;
    private String formateur;
    private String prerequis;

}
