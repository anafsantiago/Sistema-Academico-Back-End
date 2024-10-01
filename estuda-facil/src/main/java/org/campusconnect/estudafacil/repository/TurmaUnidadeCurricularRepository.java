package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.SituacaoTurma;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaUnidadeCurricularRepository extends JpaRepository<TurmaUnidadeCurricular, Long> {

    List<TurmaUnidadeCurricular> findAllByCodigoTurmaStartingWithAndSituacaoTurma(String codigoTurma, SituacaoTurma situacaoTurma);

}