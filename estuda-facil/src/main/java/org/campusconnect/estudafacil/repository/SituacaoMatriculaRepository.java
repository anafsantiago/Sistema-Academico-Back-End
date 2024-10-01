package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.SituacaoMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SituacaoMatriculaRepository extends JpaRepository<SituacaoMatricula, Long> {

    Optional<SituacaoMatricula> findByDescricao(String descricao);

}