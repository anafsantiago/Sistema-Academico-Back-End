package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDocente;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SituacaoAlocacaoDocenteRepository {

    Optional<SituacaoAlocacaoDocente> findByDescricao(String descricao);

}