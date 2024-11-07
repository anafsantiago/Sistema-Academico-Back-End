package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.AlocacaoDiscenteTurma;
import org.campusconnect.estudafacil.entity.AlocacaoDocenteTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlocacaoDocenteTurmaRepository extends JpaRepository<AlocacaoDocenteTurma, Long> {

    @Query("""
        SELECT adt FROM AlocacaoDocenteTurma adt
        JOIN adt.docente d
        JOIN adt.turmaUnidadeCurricular tuc
        JOIN tuc.unidadeCurricular uc
        JOIN d.pessoa p WHERE p.id = :idPessoa\s
       \s""")
    List<AlocacaoDocenteTurma> carregarDadosAlocacoesDocenteByIdPessoa(@Param("idPessoa") long idPessoa);

}