package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.AlocacaoDocenteDTO;
import org.campusconnect.estudafacil.dto.DocenteDTO;
import org.campusconnect.estudafacil.dto.TurmaUnidadeCurricularDTO;
import org.campusconnect.estudafacil.dto.UnidadeCurricularDTO;
import org.campusconnect.estudafacil.entity.AlocacaoDocenteTurma;
import org.campusconnect.estudafacil.entity.CalendarioAcademico;
import org.campusconnect.estudafacil.entity.Docente;
import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDocente;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.campusconnect.estudafacil.repository.AlocacaoDocenteTurmaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlocacaoDocenteTurmaService {

    private final AlocacaoDocenteTurmaRepository alocacaoDocenteTurmaRepository;
    private final DocenteService docenteService;
    private final TurmaUnidadeCurricularService turmaUnidadeCurricularService;
    private final SituacaAlocacaoDocenteService situacaoAlocacaoDocenteService;
    private final CalendarioAcademicoService calendarioAcademicoService;

    public String cadastrarAlocacaoDocenteTurma(long idDocente, long idTurma) {
        CalendarioAcademico calendarioAcademicoVigente = calendarioAcademicoService.getCalendarioAcademicoVigente();
        Docente docente = docenteService.getdDocentePorId(idDocente);
        TurmaUnidadeCurricular turmaUnidadeCurricular = turmaUnidadeCurricularService.getTurmaUnidadeCurricularPorId(idTurma);
        SituacaoAlocacaoDocente situacaoAlocacaoDocente = situacaoAlocacaoDocenteService.getSituacaoAlocacaoDocentePorDescricao(SituacaoAlocacaoDocente.SITUACAO_ATIVA);
        //List<LocalDate> diasAulas = turmaUnidadeCurricularService.obterDiasDeAula(turmaUnidadeCurricular, calendarioAcademicoVigente);
        AlocacaoDocenteTurma alocacaoDocenteTurma = new AlocacaoDocenteTurma();
        alocacaoDocenteTurma.setDocente(docente);
        alocacaoDocenteTurma.setTurmaUnidadeCurricular(turmaUnidadeCurricular);
        //alocacaoDocenteTurma.setDataInicioAlocacao(diasAulas.getFirst());
        //alocacaoDocenteTurma.setDataFimAlocacao(diasAulas.getLast());
        int anoLetivo = LocalDate.now().getYear();
        alocacaoDocenteTurma.setDataInicioAlocacao(LocalDate.of(anoLetivo, 2, 20));
        alocacaoDocenteTurma.setDataFimAlocacao(LocalDate.of(anoLetivo, 6, 30));
        //alocacaoDocenteTurma.setCargaHoraiaSemanal(turmaUnidadeCurricularService.calcularAulasPorSemana(turmaUnidadeCurricular) * AlocacaoDocenteTurma.HORAS_AULA_DIA);
        alocacaoDocenteTurma.setCargaHoraiaSemanal(20);
        alocacaoDocenteTurma.setSituacaoAlocacaoDocente(situacaoAlocacaoDocente);
        alocacaoDocenteTurmaRepository.save(alocacaoDocenteTurma);
        return "Alocação realizada com sucesso.";
    }

    public List<AlocacaoDocenteDTO> carregarDadosAlocacoesDocente(long idPessoa) {
        List<AlocacaoDocenteTurma> alocacoesDocente = alocacaoDocenteTurmaRepository.carregarDadosAlocacoesDocenteByIdPessoa(idPessoa);

        return alocacoesDocente.stream()
                .map(alocacao -> {
                    DocenteDTO docenteDTO = new DocenteDTO(
                            alocacao.getDocente().getId(),
                            alocacao.getDocente().isGestor(),
                            alocacao.getDocente().getCodDocente()
                    );

                    UnidadeCurricularDTO unidadeCurricularDTO = new UnidadeCurricularDTO(
                            alocacao.getTurmaUnidadeCurricular().getUnidadeCurricular().getId(),
                            alocacao.getTurmaUnidadeCurricular().getUnidadeCurricular().getNomeUnidadeCurricular(),
                            alocacao.getTurmaUnidadeCurricular().getUnidadeCurricular().getEmenta(),
                            null
                    );

                    TurmaUnidadeCurricularDTO turmaUnidadeCurricularDTO = new TurmaUnidadeCurricularDTO(
                            alocacao.getTurmaUnidadeCurricular().getId(),
                            alocacao.getTurmaUnidadeCurricular().getCodigoTurma(),
                            unidadeCurricularDTO,
                            null
                    );

                    return new AlocacaoDocenteDTO(
                            alocacao.getId(),
                            docenteDTO,
                            turmaUnidadeCurricularDTO);
                })
                .collect(Collectors.toList());
    }

}