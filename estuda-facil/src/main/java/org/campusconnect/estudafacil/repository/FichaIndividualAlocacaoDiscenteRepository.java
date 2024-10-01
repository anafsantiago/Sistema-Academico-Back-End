package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.FichaIndividualAlocacaoDiscente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichaIndividualAlocacaoDiscenteRepository extends JpaRepository<FichaIndividualAlocacaoDiscente, Long> {

    @Query("SELECT f.id, f.notas FROM FichaIndividualAlocacaoDiscente f WHERE f.id IN :ids")
    List<FichaIndividualAlocacaoDiscente> findNotasByIdsFichas(@Param("ids") List<Long> idsFichas);

}