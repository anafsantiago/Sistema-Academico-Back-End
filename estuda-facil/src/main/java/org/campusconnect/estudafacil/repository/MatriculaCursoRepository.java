package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.MatriculaCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatriculaCursoRepository extends JpaRepository<MatriculaCurso, Long> {

    @Query("SELECT m FROM MatriculaCurso m " +
            "JOIN m.discente d " +
            "JOIN d.pessoa p " +
            "JOIN m.situacaoMatricula s " +
            "WHERE p.id = :idPessoa")
    Optional<MatriculaCurso> findMatriculaCursoAtivaByPessoaId(@Param("idPessoa") long idPessoa);

}