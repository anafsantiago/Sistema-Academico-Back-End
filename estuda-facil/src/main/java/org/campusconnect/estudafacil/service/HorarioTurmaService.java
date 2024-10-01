package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.Horario;
import org.campusconnect.estudafacil.entity.HorarioTurma;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.campusconnect.estudafacil.repository.HorarioTurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioTurmaService {

    private final HorarioTurmaRepository horarioTurmaRepository;
    private final HorarioService horarioService;

    public String cadastrarHorarioTurma(TurmaUnidadeCurricular turmaUnidadeCurricular, List<Long> idsHorarios) {
        List<Horario> horarios = horarioService.getHorariosPorIds(idsHorarios);
        List<HorarioTurma> horariosTurma = new ArrayList<>();
        if(!horarios.isEmpty()){
            for (Horario horario : horarios) {
                HorarioTurma ht = new HorarioTurma();
                ht.setTurmaUnidade(turmaUnidadeCurricular);
                ht.setHorario(horario);
                horariosTurma.add(ht);
            }
            horarioTurmaRepository.saveAll(horariosTurma);
            return "Horários cadastrados com sucesso.";
        }
        return "Horários não encontrados.";
    }

}