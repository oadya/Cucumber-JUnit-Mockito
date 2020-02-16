package fr.bpifrance.qua.qformation.dao.repositories;

import fr.bpifrance.qua.qformation.dao.models.SujetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SujetRepository extends JpaRepository<SujetEntity, Long> {
    @Query("select s from SujetEntity s where s.code = ?1")
    SujetEntity trouverSujet(String code);
}
