package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.entity.SituacaoTurma;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ConsolidacaoTurmaService {

    private final AlocacaoDiscenteTurmaService alocacaoDiscenteTurmaService;
    private final TurmaUnidadeCurricularService turmaUnidadeCurricularService;
    private final SituacaoTurmaService situacaoTurmaService;

    @Transactional
    public String consolidarTurma(long idTurma) {
        TurmaUnidadeCurricular turma = turmaUnidadeCurricularService.getTurmaUnidadeCurricularPorId(idTurma);
        LocalDate dataFim = turma.getDataFim();
        if (!turmaUnidadeCurricularService.isPeriodoDeConsolidacaoValido(dataFim)) {
            throw new IllegalArgumentException("A turma só pode ser consolidada no dia da data de fim ou até 5 dias depois.");
        }
        SituacaoTurma situacaoTurmaConsolidada = situacaoTurmaService.getSitucaoTurmaPorDescricao(SituacaoTurma.SITUACAO_CONSOLIDADA);
        alocacaoDiscenteTurmaService.consolidarAlocacoesDiscentesPorTurma(idTurma);
        turma.setSituacaoTurma(situacaoTurmaConsolidada);
        return "Turma consolidada com sucesso.";
    }

}
