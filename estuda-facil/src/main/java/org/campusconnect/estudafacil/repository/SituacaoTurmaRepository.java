package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.SituacaoTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SituacaoTurmaRepository extends JpaRepository<SituacaoTurma, Long> {

    Optional<SituacaoTurma> findByDescricao(String descricaoSituacao);

}