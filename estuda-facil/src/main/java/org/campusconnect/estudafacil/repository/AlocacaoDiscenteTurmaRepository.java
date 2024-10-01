package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.AlocacaoDiscenteTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocacaoDiscenteTurmaRepository extends JpaRepository<AlocacaoDiscenteTurma, Long> {
}