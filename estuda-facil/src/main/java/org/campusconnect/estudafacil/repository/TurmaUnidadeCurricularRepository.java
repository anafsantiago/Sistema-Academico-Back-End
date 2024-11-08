package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.SituacaoTurma;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaUnidadeCurricularRepository extends JpaRepository<TurmaUnidadeCurricular, Long> {

    List<TurmaUnidadeCurricular> findAllByCodigoTurmaStartingWithAndSituacaoTurma(String codigoTurma, SituacaoTurma situacaoTurma);

    @Query(" SELECT t.id, t.semestre, t.horarios FROM TurmaUnidadeCurricular t " +
            " JOIN AlocacaoDiscenteTurma adt ON adt.turmaUnidadeCurricular.id = t.id " +
            " WHERE adt.fichaIndividualAlocacaoDiscente.id = :idFichaIndividual ")
    Optional<TurmaUnidadeCurricular> findByIdFichaIndividual(@Param("idFichaIndividual") long idFichaIndividual);

    @Query("SELECT t FROM TurmaUnidadeCurricular t WHERE t.situacaoTurma.descricao = :descricao")
    List<TurmaUnidadeCurricular> findAllBySituacaoAberta(@Param("descricao") String descricao);

    @Query("SELECT t FROM TurmaUnidadeCurricular t " +
            "WHERE t.situacaoTurma.descricao = :descricao " +
            "AND NOT EXISTS (SELECT 1 FROM AlocacaoDiscenteTurma a WHERE a.discente.id = :discenteId AND a.turmaUnidadeCurricular.id = t.id)")
    List<TurmaUnidadeCurricular> findAllTurmasAbertasAlocaveis(@Param("descricao") String descricao, @Param("discenteId") long discenteId);


}