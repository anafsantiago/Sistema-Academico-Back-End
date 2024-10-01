package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.CalendarioAcademico;
import org.campusconnect.estudafacil.repository.CalendarioAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CalendarioAcademicoService {

    private final CalendarioAcademicoRepository calendarioAcademicoRepository;

    public CalendarioAcademico getCalendarioAcademicoVigente(){
        LocalDate hoje = LocalDate.now();
        int anoVigente = hoje.getYear();
        return calendarioAcademicoRepository.findByAnoLetivo(anoVigente)
                .orElseThrow (() -> new IllegalArgumentException("Calendário Acadêmico não encontrado."));
    }

    @Transactional
    public String cadastrarCalendarioAcademico(CalendarioAcademico calendario){
        calendarioAcademicoRepository.save(calendario);
        return "Calendário Acadêmico cadastrado com sucesso.";
    }

}