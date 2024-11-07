package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c " +
            "JOIN AlocacaoDiscenteTurma adt ON adt.turmaUnidadeCurricular.id = :idTurma " +
            "JOIN Discente d ON d.id = adt.discente.id " +
            "JOIN MatriculaCurso m ON m.discente.id = d.id " +
            "WHERE adt.turmaUnidadeCurricular.id = :idTurma")
    Optional<Curso> findCursoByIdTurma(@Param("idTurma") Long idTurma);

}