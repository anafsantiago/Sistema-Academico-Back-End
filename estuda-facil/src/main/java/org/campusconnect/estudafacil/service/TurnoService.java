package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.Turno;
import org.campusconnect.estudafacil.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;

/*    public Turno getTurnoPorUnidadeCurricular(long idUnidadeCurricular) {
        return turnoRepository.findByIdUnidadeCurricular(idUnidadeCurricular).orElseThrow(() ->new IllegalArgumentException("Turno não encontrado."));
    }*/

    @Transactional
    public String cadastrarTurno(Turno turno) {
        turnoRepository.save(turno);
        return "Turno cadastrado com sucesso.";
    }

}