package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.CalendarioAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalendarioAcademicoRepository extends JpaRepository<CalendarioAcademico, Long> {

    Optional<CalendarioAcademico> findByAnoLetivo(int anoLetivo);

}