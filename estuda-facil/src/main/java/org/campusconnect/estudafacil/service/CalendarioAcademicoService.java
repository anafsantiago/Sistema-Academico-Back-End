package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.entity.CalendarioAcademico;
import org.campusconnect.estudafacil.repository.CalendarioAcademicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarioAcademicoService {

    private final CalendarioAcademicoRepository calendarioAcademicoRepository;

    private void gerarDiasLetivos(CalendarioAcademico calendarioAcademico) {
        List<LocalDate> diasLetivosPrimeiroSemestre = new ArrayList<>();
        List<LocalDate> diasLetivosSegundoSemestre = new ArrayList<>();
        for (LocalDate date = calendarioAcademico.getInicioPrimeiroSemestre(); !date.isAfter(calendarioAcademico.getInicioPrimeiroSemestre()); date = date.plusDays(1)) {
            if (isDiaLetivo(date)) {
                diasLetivosPrimeiroSemestre.add(date);
            }
        }
        for (LocalDate date = calendarioAcademico.getInicioSegundoSemestre(); !date.isAfter(calendarioAcademico.getInicioSegundoSemestre()); date = date.plusDays(1)) {
            if (isDiaLetivo(date)) {
                diasLetivosSegundoSemestre.add(date);
            }
        }
        calendarioAcademico.setDiasLetivosPrimeiroSemestre(diasLetivosPrimeiroSemestre);
        calendarioAcademico.setDiasLetivosSegundoSemestre(diasLetivosSegundoSemestre);
    }

    private boolean isDiaLetivo(LocalDate date) {
        DayOfWeek diaDaSemana = date.getDayOfWeek();
        return diaDaSemana != DayOfWeek.SUNDAY;
    }

    public CalendarioAcademico getCalendarioAcademicoVigente() {
        LocalDate hoje = LocalDate.now();
        int anoVigente = hoje.getYear();
        CalendarioAcademico calendarioAcademicoVigente = calendarioAcademicoRepository.findByAnoLetivo(anoVigente)
                .orElseThrow(() -> new IllegalArgumentException("Calendário Acadêmico não encontrado."));
        gerarDiasLetivos(calendarioAcademicoVigente);
        return calendarioAcademicoVigente;
    }

    @Transactional
    public String cadastrarCalendarioAcademico(CalendarioAcademico calendario) {
        calendarioAcademicoRepository.save(calendario);
        return "Calendário Acadêmico cadastrado com sucesso.";
    }

}