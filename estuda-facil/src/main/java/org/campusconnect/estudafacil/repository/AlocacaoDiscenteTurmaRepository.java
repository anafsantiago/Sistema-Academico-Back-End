package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.AlocacaoDiscenteTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlocacaoDiscenteTurmaRepository extends JpaRepository<AlocacaoDiscenteTurma, Long> {

    @Query(" SELECT adt " +
            " FROM AlocacaoDiscenteTurma adt " +
            " WHERE adt.turmaUnidadeCurricular.id = :idTurma ")
    List<AlocacaoDiscenteTurma> findAlocacoesByIdTurma(@Param("idTurma") long idTurma);

    @Query("""
        SELECT adt FROM AlocacaoDiscenteTurma adt
        JOIN adt.discente d
        JOIN adt.turmaUnidadeCurricular tuc
        JOIN tuc.unidadeCurricular uc
        JOIN adt.situacaoAlocacaoDiscente sad
        JOIN adt.fichaIndividualAlocacaoDiscente fid
        JOIN d.pessoa p WHERE p.id = :idPessoa
       \s""")
    List<AlocacaoDiscenteTurma> carregarDadosAlocacoesDiscenteByIdPessoa(@Param("idPessoa") long idPessoa);

}