package fr.bpifrance.qua.qformation.testihm.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RessourcesPage {
    //Salles
    @FindBy(name = "salle" )
    public WebElement txtSalle;
    @FindBy(id = "capacite" )
    public WebElement txtCapacite;

    //Sujets
    @FindBy(id = "code" )
    public WebElement txtCodeSujet;
    @FindBy(id = "libelle" )
    public WebElement txtLibelleSujet;
    @FindBy(id = "formateur" )
    public WebElement txtFormateurSujet;
    @FindBy(id = "prerequis" )
    public WebElement txtPrerequisSujet;

    @FindBy(xpath = "//input[@value='Ajouter']" )
    public WebElement btnAjouter;



}
