package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.GradeCurricular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeCurricularRepository extends JpaRepository<GradeCurricular, Long> {

    @Query("SELECT gc FROM GradeCurricular gc JOIN Curso c ON gc.id = c.gradeCurricular.id WHERE c.id = :idCurso")
    Optional<GradeCurricular> findByCursoId(@Param("idCurso") long idCurso);

}