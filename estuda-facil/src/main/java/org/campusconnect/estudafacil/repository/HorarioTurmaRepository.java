package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.HorarioTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioTurmaRepository extends JpaRepository<HorarioTurma, Long> {
}