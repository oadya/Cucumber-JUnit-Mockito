package fr.bpifrance.qua.qformation.web.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class SalleJson {
    private String salle;
    private Integer capacite;

}
