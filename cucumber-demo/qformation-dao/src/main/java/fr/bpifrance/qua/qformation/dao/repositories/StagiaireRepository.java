package fr.bpifrance.qua.qformation.dao.repositories;

import fr.bpifrance.qua.qformation.dao.models.StagiaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface StagiaireRepository extends JpaRepository<StagiaireEntity, Long>{

    @Query("select s from StagiaireEntity s where s.idFormation = ?1")
	List<StagiaireEntity> trouverStagiairesFormation(long pIdFormation);
    
    @Query("select s from StagiaireEntity s where s.idFormation = ?1 and s.matricule = ?2")
    StagiaireEntity trouverStagiaire(long pIdFormation, String matricule);
    @Modifying
    @Transactional
    @Query("delete from StagiaireEntity s where s.idFormation = ?1 and s.matricule = ?2")
	void supprimerStagiaire(long pIdFormation, String matricule);

}

