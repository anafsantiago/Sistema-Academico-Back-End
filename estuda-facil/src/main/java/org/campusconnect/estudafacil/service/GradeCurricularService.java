package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.GradeCurricular;
import org.campusconnect.estudafacil.repository.GradeCurricularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeCurricularService {

    private final GradeCurricularRepository gradeCurricularRepository;

    public GradeCurricular getGradeCurricularPorIdCurso(long idCurso) {
        return gradeCurricularRepository.findByCursoId(idCurso).orElseThrow(()-> new IllegalArgumentException("Grade Curricular não encontrada."));
    }

}