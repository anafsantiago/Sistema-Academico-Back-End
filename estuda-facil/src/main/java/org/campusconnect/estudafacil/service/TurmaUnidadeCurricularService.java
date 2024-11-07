package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.entity.CalendarioAcademico;
import org.campusconnect.estudafacil.entity.SituacaoTurma;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.campusconnect.estudafacil.repository.TurmaUnidadeCurricularRepository;
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

    public TurmaUnidadeCurricular getTurmaByIdFichaIndividual(long idFichaIndividual) {
        return turmaRepository.findByIdFichaIndividual(idFichaIndividual).orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));
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
    //public String cadastrarTurmaUnidadeCurricular(TurmaUnidadeCurricular turmaUnidadeCurricular, List<Long> idsHorarios) {
    public String cadastrarTurmaUnidadeCurricular(TurmaUnidadeCurricular turmaUnidadeCurricular) {
        CalendarioAcademico calendarioAcademicoVigente = calendarioAcademicoService.getCalendarioAcademicoVigente();
        SituacaoTurma situacaoTurma = situacaoTurmaService.getSitucaoTurmaPorDescricao(SituacaoTurma.SITUACAO_ABERTA);
        TurmaUnidadeCurricular novaTurma = new TurmaUnidadeCurricular();
        novaTurma.setCodigoTurma(turmaUnidadeCurricular.getCodigoTurma());
        novaTurma.setUnidadeCurricular(turmaUnidadeCurricular.getUnidadeCurricular());
        novaTurma.setSituacaoTurma(situacaoTurma);
        novaTurma.setTurno(turmaUnidadeCurricular.getTurno());
        novaTurma.setDataCadastro(LocalDate.now());
        novaTurma.setSemestre(calendarioAcademicoVigente.getSemestreVigente());
        novaTurma.setAnoLetivo(calendarioAcademicoVigente.getAnoLetivo());
        novaTurma.setQuantidadeVagas(turmaUnidadeCurricular.getQuantidadeVagas());
/*        List<LocalDate> diasDeAula = obterDiasDeAula(novaTurma, calendarioAcademicoVigente);
        if (!diasDeAula.isEmpty()) {
            novaTurma.setDataInicio(diasDeAula.getFirst());
            novaTurma.setDataFim(diasDeAula.getLast());
        }*/
        int anoLetivo = LocalDate.now().getYear();
        novaTurma.setDataInicio(LocalDate.of(anoLetivo, 2, 20));
        novaTurma.setDataFim(LocalDate.of(anoLetivo, 6, 30));
        turmaRepository.save(novaTurma);
        //String mensagemHorarios = horarioTurmaService.cadastrarHorarioTurma(novaTurma, idsHorarios);
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Turma cadastrada com sucesso.");
        mensagem.append("\n");
        //mensagem.append(mensagemHorarios);
        return mensagem.toString();
    }

    public List<LocalDate> obterDiasDeAula(TurmaUnidadeCurricular turmaUnidadeCurricular, CalendarioAcademico calendarioAcademicoVigente) {
        List<LocalDate> diasLetivos = (turmaUnidadeCurricular.getSemestre() == CalendarioAcademico.PRIMEIRO_SEMESTRE)
                ? calendarioAcademicoVigente.getDiasLetivosPrimeiroSemestre()
                : calendarioAcademicoVigente.getDiasLetivosSegundoSemestre();
        return diasLetivos.stream()
                .filter(diaLetivo -> turmaUnidadeCurricular.getHorarios().stream()
                        .anyMatch(ht -> diaLetivo.getDayOfWeek() == ht.getHorario().getDiaSemana()))
                .collect(Collectors.toList());
    }

    public int calcularDiasDeAulaNoSemestre(TurmaUnidadeCurricular turmaUnidade) {
        CalendarioAcademico calendarioAcademicoVigente = calendarioAcademicoService.getCalendarioAcademicoVigente();
        List<LocalDate> diasDeAula = obterDiasDeAula(turmaUnidade, calendarioAcademicoVigente);
        return diasDeAula.size();
    }

    public int calcularAulasPorSemana(TurmaUnidadeCurricular turmaUnidadeCurricular) {
        return (int) turmaUnidadeCurricular.getHorarios().stream()
                .map(ht -> ht.getHorario().getDiaSemana())
                .distinct()
                .count();
    }

    public boolean isPeriodoDeConsolidacaoValido(LocalDate dataFim) {
        if (dataFim == null) {
            throw new IllegalArgumentException("Data de fim da turma não está definida.");
        }
        LocalDate dataAtual = LocalDate.now();
        return !dataAtual.isBefore(dataFim) && !dataAtual.isAfter(dataFim.plusDays(5));
    }

}