package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.Horario;
import org.campusconnect.estudafacil.repository.HorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioService {

    private final HorarioRepository horarioRepository;

    public Horario getHorarioPorId(long id) {
        return horarioRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Horário não encontrado."));
    }

    public List<Horario> getHorariosPorIds(List<Long> idsHorarios){
        return horarioRepository.findAllById(idsHorarios);
    }

}