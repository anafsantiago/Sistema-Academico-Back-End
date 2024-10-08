package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.entity.AlocacaoDocenteTurma;
import org.campusconnect.estudafacil.entity.Docente;
import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDocente;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.campusconnect.estudafacil.repository.AlocacaoDiscenteTurmaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlocacaoDocenteTurmaService {

    private final AlocacaoDiscenteTurmaRepository alocacaoDiscenteTurmaRepository;
    private final DocenteService docenteService;
    private final TurmaUnidadeCurricularService turmaUnidadeCurricularService;
    private final SituacaAlocacaoDocenteService situacaoAlocacaoDocenteService;

    private String cadastrarAlocacaoDocenteTurma(long idDocente, long idTurma) {
        Docente docente = docenteService.getdDocentePorId(idDocente);
        TurmaUnidadeCurricular turmaUnidadeCurricular = turmaUnidadeCurricularService.getTurmaUnidadeCurricularPorId(idTurma);
        SituacaoAlocacaoDocente situacaoAlocacaoDocente = situacaoAlocacaoDocenteService.getSituacaoAlocacaoDocentePorDescricao(SituacaoAlocacaoDocente.SITUACAO_ATIVA);
        List<LocalDate> diasAulas = turmaUnidadeCurricularService.obterDiasDeAula(turmaUnidadeCurricular);
        AlocacaoDocenteTurma alocacaoDocenteTurma = new AlocacaoDocenteTurma();
        alocacaoDocenteTurma.setDocente(docente);
        alocacaoDocenteTurma.setTurmaUnidadeCurricular(turmaUnidadeCurricular);
        alocacaoDocenteTurma.setDataInicioAlocacao(diasAulas.getFirst());
        alocacaoDocenteTurma.setDataFimAlocacao(diasAulas.getLast());
        alocacaoDocenteTurma.setCargaHoraiaSemanal(turmaUnidadeCurricularService.calcularAulasPorSemana(turmaUnidadeCurricular) * AlocacaoDocenteTurma.HORAS_AULA_DIA);
        alocacaoDocenteTurma.setSituacaoAlocacaoDocente(situacaoAlocacaoDocente);
        return "Alocação realizada com sucesso.";
    }

}