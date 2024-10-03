package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.AlocacaoDiscenteTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlocacaoDiscenteTurmaRepository extends JpaRepository<AlocacaoDiscenteTurma, Long> {

    @Query(" SELECT adt.id, adt.situacaoAlocacaoDiscente, adt.fichaIndividualAlocacaoDiscente.resultadoFinal, adt.fichaIndividualAlocacaoDiscente.porcentagemFrequencia " +
            " FROM AlocacaoDiscenteTurma adt " +
            " WHERE adt.turmaUnidadeCurricular.id = :idTurma ")
    List<AlocacaoDiscenteTurma> findAlocacoesByIdTurma(@Param("idTurma") long idTurma);

}