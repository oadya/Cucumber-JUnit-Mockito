package fr.bpifrance.qua.qformation.dao.repositories;

import fr.bpifrance.qua.qformation.dao.models.FormationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FormationRepository extends JpaRepository<FormationEntity, Long>{
    @Query("select f from FormationEntity f where f.code = ?1")
    FormationEntity trouverFormation(String code);

}

