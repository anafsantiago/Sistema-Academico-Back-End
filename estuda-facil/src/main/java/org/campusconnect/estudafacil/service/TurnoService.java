package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.TurnoDTO;
import org.campusconnect.estudafacil.dto.UnidadeCurricularDTO;
import org.campusconnect.estudafacil.entity.Turno;
import org.campusconnect.estudafacil.entity.UnidadeCurricular;
import org.campusconnect.estudafacil.repository.TurnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;

    public Turno getTurnoById(Long idTurno) {
        return turnoRepository.findById(idTurno).orElseThrow(() -> new RuntimeException("Turno não encontrado"));
    }

    public List<TurnoDTO> getAllTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        return turnos.stream()
                .map(turno -> new TurnoDTO(
                        turno.getId(),
                        turno.getDescricao(),
                        turno.getAbreviacaoTurno()))
                .collect(Collectors.toList());
    }

    @Transactional
    public String cadastrarTurno(Turno turno) {
        turnoRepository.save(turno);
        return "Turno cadastrado com sucesso.";
    }

}