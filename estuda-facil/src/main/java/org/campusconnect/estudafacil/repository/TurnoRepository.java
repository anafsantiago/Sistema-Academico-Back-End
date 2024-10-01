package org.campusconnect.estudafacil.repository;


import org.campusconnect.estudafacil.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

/*    @Query("SELECT t FROM Curso c " +
            "JOIN c.turno t " +
            "JOIN c.gradeCurricular gc " +
            "JOIN gc.unidadesCurriculares uc " +
            "WHERE uc.id = :idUnidadeCurricular")
    Optional<Turno> findByIdUnidadeCurricular(@Param("idUnidadeCurricular") long idUnidadeCurricular);*/

}