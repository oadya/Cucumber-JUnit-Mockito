package fr.bpifrance.qua.qformation.dao;

import fr.bpifrance.qua.qformation.dao.models.FormationEntity;
import fr.bpifrance.qua.qformation.dao.models.SalleEntity;
import fr.bpifrance.qua.qformation.dao.models.StagiaireEntity;
import fr.bpifrance.qua.qformation.dao.models.SujetEntity;
import fr.bpifrance.qua.qformation.dao.repositories.FormationRepository;
import fr.bpifrance.qua.qformation.dao.repositories.SalleRepository;
import fr.bpifrance.qua.qformation.dao.repositories.StagiaireRepository;
import fr.bpifrance.qua.qformation.dao.repositories.SujetRepository;
import fr.bpifrance.qua.qformation.domaine.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
@Qualifier("FormationRepositoryAdapter")
public class FormationRepositoryAdapter implements FormationRepositoryPort {
	@Autowired
	public FormationRepository formationRepository;
	@Autowired
	public StagiaireRepository stagiaireRepository;
	@Autowired
	public SujetRepository sujetRepository;
	@Autowired
	public SalleRepository salleRepository;
	ModelMapper modelMapper = new ModelMapper();

	public FormationRepositoryAdapter(){}


	public List<Formation> obtenirFormations() {
		List<Formation> listeFormations = new ArrayList<Formation>();
		Iterator<FormationEntity> iterator= formationRepository.findAll().iterator();
		while(iterator.hasNext()){
			Formation f = iterator.next();
			f.setStagiaires(this.obtenirStagiairesFormation(f.getId()));
			listeFormations.add(f);
		}
		return listeFormations;
	}

	public Formation planifierFormation(Formation pFormation) {
		FormationEntity formationEntity = modelMapper.map(pFormation, FormationEntity.class);
		return formationRepository.save(formationEntity);
	}


	public Formation obtenirFormation(long pIdFormation) {
		return  modelMapper.map(formationRepository.getOne(pIdFormation), Formation.class);
	}


	public void supprimerFormation(long pIdFormation) {
		formationRepository.deleteById(pIdFormation);
	}

	public Formation obtenirFormation(String code) {
		if(formationRepository.trouverFormation(code) == null){
			return null;
		}else{
			return  modelMapper.map(formationRepository.trouverFormation(code), Formation.class);
		}
	}

	public void supprimerFormation(String pCode) {
	}


	public void inscrireStagiaire(Stagiaire pStagiaire) {
		StagiaireEntity stagiaireEntity = modelMapper.map(pStagiaire, StagiaireEntity.class);
		stagiaireRepository.save(stagiaireEntity);
	}


	public void modifierFormation(Formation pFormation) {
		FormationEntity formationEntity = formationRepository.getOne(pFormation.getId());
		formationRepository.save(formationEntity);
	}


	public List<Stagiaire> obtenirStagiairesFormation(long pIdFormation) {
		List<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		Iterator<StagiaireEntity> iterator= stagiaireRepository.trouverStagiairesFormation(pIdFormation).iterator();
		while(iterator.hasNext()){
			listeStagiaires.add(iterator.next());
		}
		return listeStagiaires;

	}


	public Stagiaire obtenirStagiaire(long pIdFormation, String matricule) {
		return  stagiaireRepository.trouverStagiaire(pIdFormation, matricule);
	}


	public void retirerStagiaire(long pIdFormation, String matricule) {
		stagiaireRepository.supprimerStagiaire( pIdFormation,  matricule);

	}


	public Sujet ajouterSujet(Sujet pSujet) {
		SujetEntity sujetEntity = modelMapper.map(pSujet, SujetEntity.class);
		return sujetRepository.save(sujetEntity);
	}


	public List<Sujet> obtenirSujets() {
		List<Sujet> listeSujets = new ArrayList<Sujet>();
		Iterator<SujetEntity> iterator= sujetRepository.findAll().iterator();
		while(iterator.hasNext()){
			listeSujets.add(iterator.next());
		}
		return listeSujets;
	}


	public Sujet obtenirSujet(String code) {
		if(sujetRepository.trouverSujet(code) == null){
			return null;
		}else{
			return  modelMapper.map(sujetRepository.trouverSujet(code), Sujet.class);
		}

	}


	public Salle ajouterSalle(Salle pSalle) {
		SalleEntity salleEntity = modelMapper.map(pSalle, SalleEntity.class);
		return salleRepository.save(salleEntity);
	}


	public List<Salle> obtenirSalles() {
		List<Salle> listeSalles = new ArrayList<Salle>();
		Iterator<SalleEntity> iterator=  salleRepository.findAll().iterator();
		while(iterator.hasNext()){
			listeSalles.add(iterator.next());
		}
		return listeSalles;
	}

	public Salle obtenirSalle(String nomSalle) {
		return new Salle();//Pas implémenté
	}


	public boolean isSalleDisponible(String salle, Date date) {
		//Mock en dur
		if(salle.equals("RDC1")){return true;}
		return false;//Pas implémenté
	}


}
