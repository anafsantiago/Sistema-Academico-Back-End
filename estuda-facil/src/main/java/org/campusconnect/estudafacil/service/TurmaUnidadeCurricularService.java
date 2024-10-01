package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.CalendarioAcademico;
import org.campusconnect.estudafacil.entity.SituacaoTurma;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.campusconnect.estudafacil.repository.TurmaUnidadeCurricularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurmaUnidadeCurricularService {

    private final TurmaUnidadeCurricularRepository turmaRepository;
    private final CalendarioAcademicoService calendarioAcademicoService;
    private final HorarioTurmaService horarioTurmaService;
    private final SituacaoTurmaService situacaoTurmaService;

    public TurmaUnidadeCurricular getTurmaUnidadeCurricularPorId(long idTurma) {
        return turmaRepository.findById(idTurma).orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));
    }

    public String gerarCodigoTurma(String siglaUnidadeCurricular, String abvTurno) {
        CalendarioAcademico calendarioAcademicoVigente = calendarioAcademicoService.getCalendarioAcademicoVigente();
        StringBuilder codigoBase = new StringBuilder();
        codigoBase.append(siglaUnidadeCurricular);
        codigoBase.append(calendarioAcademicoVigente.getAnoLetivo());
        codigoBase.append(calendarioAcademicoVigente.getSemestreVigente());
        codigoBase.append(abvTurno);
        return gerarCodigoFinalTurma(codigoBase.toString());
    }

    public String gerarCodigoFinalTurma(String codTurmaBase) {
        SituacaoTurma situacaoTurma = situacaoTurmaService.getSitucaoTurmaPorDescricao(SituacaoTurma.SITUACAO_ABERTA);
        List<TurmaUnidadeCurricular> turmasAbertas = turmaRepository.findAllByCodigoTurmaStartingWithAndSituacaoTurma(codTurmaBase, situacaoTurma);
        char sufixo = 'A';
        Set<Character> sufixosUsados = turmasAbertas.stream()
                .map(t -> t.getCodigoTurma().charAt(codTurmaBase.length() + 1))
                .collect(Collectors.toSet());
        while (sufixosUsados.contains(sufixo)) {
            sufixo++;
        }
        StringBuilder codigoFinal = new StringBuilder(codTurmaBase);
        codigoFinal.append("-").append(sufixo);
        return codigoFinal.toString();
    }

    @Transactional
    public String cadastrarTurmaUnidadeCurricular(TurmaUnidadeCurricular turmaUnidadeCurricular, List<Long> idsHorarios) {
        CalendarioAcademico calendarioAcademicoVigente = calendarioAcademicoService.getCalendarioAcademicoVigente();
        SituacaoTurma situacaoTurma = situacaoTurmaService.getSitucaoTurmaPorDescricao(SituacaoTurma.SITUACAO_ABERTA);
        TurmaUnidadeCurricular novaTurma = new TurmaUnidadeCurricular();
        novaTurma.setCodigoTurma(turmaUnidadeCurricular.getCodigoTurma());
        novaTurma.setUnidadeCurricular(turmaUnidadeCurricular.getUnidadeCurricular());
        novaTurma.setSituacaoTurma(situacaoTurma);
        novaTurma.setDataCadastro(LocalDate.now());
        novaTurma.setSemestre(calendarioAcademicoVigente.getSemestreVigente());
        novaTurma.setAnoLetivo(calendarioAcademicoVigente.getAnoLetivo());
        novaTurma.setQuantidadeVagas(turmaUnidadeCurricular.getQuantidadeVagas());
        turmaRepository.save(novaTurma);
        String mensagemHorarios = horarioTurmaService.cadastrarHorarioTurma(turmaUnidadeCurricular, idsHorarios);
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Turma cadastrada com sucesso.");
        mensagem.append("\n");
        mensagem.append(mensagemHorarios);
        return mensagem.toString();
    }

}