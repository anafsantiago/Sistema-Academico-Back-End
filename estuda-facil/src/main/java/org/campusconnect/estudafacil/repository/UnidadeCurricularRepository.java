package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.UnidadeCurricular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeCurricularRepository extends JpaRepository<UnidadeCurricular, Long> {
}