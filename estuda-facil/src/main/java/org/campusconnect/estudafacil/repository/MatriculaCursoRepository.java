package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.MatriculaCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaCursoRepository extends JpaRepository<MatriculaCurso, Long> {
}