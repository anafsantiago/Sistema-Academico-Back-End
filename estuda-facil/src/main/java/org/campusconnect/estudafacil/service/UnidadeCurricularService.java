package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.UnidadeCurricularDTO;
import org.campusconnect.estudafacil.entity.UnidadeCurricular;
import org.campusconnect.estudafacil.repository.UnidadeCurricularRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnidadeCurricularService {

    private final UnidadeCurricularRepository unidadeCurricularRepository;

    public List<UnidadeCurricularDTO> getAllUnidades() {
        List<UnidadeCurricular> unidadesCurriculares = unidadeCurricularRepository.findAll();
        return unidadesCurriculares.stream()
                .map(unidadeCurricular -> new UnidadeCurricularDTO(
                        unidadeCurricular.getId(),
                        unidadeCurricular.getNomeUnidadeCurricular(),
                        unidadeCurricular.getEmenta(),
                        unidadeCurricular.getSiglaUnidadeCurricular()))
                .collect(Collectors.toList());
    }
}
