package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDiscente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SituacaoAlocacaoDiscenteRepository extends JpaRepository<SituacaoAlocacaoDiscente, Long> {

    Optional<SituacaoAlocacaoDiscente> findByDescricao(String descricaoSituacao);

}