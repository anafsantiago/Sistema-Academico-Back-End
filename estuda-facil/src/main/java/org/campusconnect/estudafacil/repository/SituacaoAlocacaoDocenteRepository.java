package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDocente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SituacaoAlocacaoDocenteRepository extends JpaRepository<SituacaoAlocacaoDocente, Long> {

    Optional<SituacaoAlocacaoDocente> findByDescricao(String descricao);

}