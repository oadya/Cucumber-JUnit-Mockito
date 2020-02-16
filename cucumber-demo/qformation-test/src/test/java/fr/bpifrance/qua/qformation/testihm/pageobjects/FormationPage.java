package fr.bpifrance.qua.qformation.testihm.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormationPage {
    @FindBy(linkText = "Liste des formations" )
    public WebElement menuListeFormations;
    @FindBy(linkText = "Planifier une formation" )
    public WebElement menuPlanifierFormation;

    @FindBy(id = "code" )
    public WebElement listeCodeFormation;


    @FindBy(id = "libelle" )
    public WebElement txtlibelleFormation;
    @FindBy(id = "date" )
    public WebElement txtDateFormation;
    @FindBy(id = "salle" )
    public WebElement listeSalleFormation;
    @FindBy(id = "formateur" )
    public WebElement txtFormateurFormation;
    @FindBy(id = "maximumInscrits" )
    public WebElement txtMaximumStagiaires;
    @FindBy(xpath="//input[@value='Ajouter la formation']")
    public WebElement btnAjouterFormation;
    @FindBy(xpath="//table[//th[1]='ID']")
    public WebElement tableauFormations;

    //Stagiaires
    @FindBy(id = "matricule" )
    public WebElement txtMatriculeStagiaire;
    @FindBy(id = "nom" )
    public WebElement txtNomStagiaire;
    @FindBy(xpath="//input[@value='Inscrire']")
    public WebElement btnInscrireStagiaire;
}
