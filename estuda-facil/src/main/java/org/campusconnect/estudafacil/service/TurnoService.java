package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.entity.Turno;
import org.campusconnect.estudafacil.repository.TurnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;

    public Turno getTurnoById(Long idTurno) {
        return turnoRepository.findById(idTurno).orElseThrow(() -> new RuntimeException("Turno não encontrado"));
    }

    @Transactional
    public String cadastrarTurno(Turno turno) {
        turnoRepository.save(turno);
        return "Turno cadastrado com sucesso.";
    }

}