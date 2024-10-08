package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.AlocacaoDocenteTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocacaoDocenteTurmaRepository extends JpaRepository<AlocacaoDocenteTurma, Long> {
}