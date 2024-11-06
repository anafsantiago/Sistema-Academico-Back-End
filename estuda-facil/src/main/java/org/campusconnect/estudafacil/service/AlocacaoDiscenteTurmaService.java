package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.AlocacaoDiscenteDTO;
import org.campusconnect.estudafacil.dto.DiscenteDTO;
import org.campusconnect.estudafacil.dto.FichaIndividualDiscenteDTO;
import org.campusconnect.estudafacil.dto.NotaDTO;
import org.campusconnect.estudafacil.dto.SituacaoAlocacaoDiscenteDTO;
import org.campusconnect.estudafacil.dto.TurmaUnidadeCurricularDTO;
import org.campusconnect.estudafacil.dto.UnidadeCurricularDTO;
import org.campusconnect.estudafacil.entity.AlocacaoDiscenteTurma;
import org.campusconnect.estudafacil.entity.Discente;
import org.campusconnect.estudafacil.entity.FichaIndividualAlocacaoDiscente;
import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDiscente;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.campusconnect.estudafacil.repository.AlocacaoDiscenteTurmaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlocacaoDiscenteTurmaService {

    private final AlocacaoDiscenteTurmaRepository alocacaoDiscenteTurmaRepository;
    private final DiscenteService discenteService;
    private final TurmaUnidadeCurricularService turmaUnidadeCurricularService;
    private final FichaIndividualAlocacaoDiscenteService fichaIndividualAlocacaoDiscenteService;
    private final SituacaoAlocacaoDiscenteService situacaoAlocacaoDiscenteService;

    @Transactional
    public String cadastrarAlocacaoDiscenteTurma(long idDiscente, long idTurmaUnidadeCurricular) {
        Discente discente = discenteService.getDiscentePorId(idDiscente);
        TurmaUnidadeCurricular turmaUnidadeCurricular = turmaUnidadeCurricularService.getTurmaUnidadeCurricularPorId(idTurmaUnidadeCurricular);
        SituacaoAlocacaoDiscente situacaoAlocacaoDiscente = situacaoAlocacaoDiscenteService.getSituacaoAlocacaoDiscentePorDescricao(SituacaoAlocacaoDiscente.SITUACAO_ATIVA);
        AlocacaoDiscenteTurma alocacaoDiscenteTurma = new AlocacaoDiscenteTurma();
        FichaIndividualAlocacaoDiscente fichaIndividualAlocacaoDiscente = fichaIndividualAlocacaoDiscenteService.cadastrarFichaIndividualAlocacaoDiscente();
        alocacaoDiscenteTurma.setDiscente(discente);
        alocacaoDiscenteTurma.setTurmaUnidadeCurricular(turmaUnidadeCurricular);
        alocacaoDiscenteTurma.setSemestre(turmaUnidadeCurricular.getSemestre());
        alocacaoDiscenteTurma.setAnoLetivo(turmaUnidadeCurricular.getAnoLetivo());
        alocacaoDiscenteTurma.setSituacaoAlocacaoDiscente(situacaoAlocacaoDiscente);
        alocacaoDiscenteTurma.setFichaIndividualAlocacaoDiscente(fichaIndividualAlocacaoDiscente);
        alocacaoDiscenteTurmaRepository.save(alocacaoDiscenteTurma);
        return "Alocação realizada com sucesso.";
    }

    @Transactional
    public void consolidarAlocacoesDiscentesPorTurma(long idTurma) {
        List<AlocacaoDiscenteTurma> alocacoesDiscentes = alocacaoDiscenteTurmaRepository.findAlocacoesByIdTurma(idTurma);
        SituacaoAlocacaoDiscente situacaoAprovada = situacaoAlocacaoDiscenteService.getSituacaoAlocacaoDiscentePorDescricao(SituacaoAlocacaoDiscente.SITUACAO_APROVADA);
        SituacaoAlocacaoDiscente situacaoReprovada = situacaoAlocacaoDiscenteService.getSituacaoAlocacaoDiscentePorDescricao(SituacaoAlocacaoDiscente.SITUACAO_REPROVADA);
        alocacoesDiscentes.forEach(alocacao -> {
            FichaIndividualAlocacaoDiscente ficha = alocacao.getFichaIndividualAlocacaoDiscente();
            if (ficha != null) {
                if (ficha.getResultadoFinal() >= 7.0f && ficha.getPorcentagemFrequencia() >= 75) {
                    alocacao.setSituacaoAlocacaoDiscente(situacaoAprovada);
                } else {
                    alocacao.setSituacaoAlocacaoDiscente(situacaoReprovada);
                }
            }
        });
        alocacaoDiscenteTurmaRepository.saveAll(alocacoesDiscentes);
    }

    public List<AlocacaoDiscenteDTO> carregarDadosAlocacoesDiscente(long idPessoa) {
        List<AlocacaoDiscenteTurma> alocacoesDiscente = alocacaoDiscenteTurmaRepository.carregarDadosAlocacoesDiscenteByIdPessoa(idPessoa);

        return alocacoesDiscente.stream()
                .map(alocacao -> {
                    DiscenteDTO discenteDTO = new DiscenteDTO(
                            alocacao.getDiscente().getId(),
                            alocacao.getDiscente().getCodDiscente()
                    );

                    UnidadeCurricularDTO unidadeCurricularDTO = new UnidadeCurricularDTO(
                            alocacao.getTurmaUnidadeCurricular().getUnidadeCurricular().getId(),
                            alocacao.getTurmaUnidadeCurricular().getUnidadeCurricular().getNomeUnidadeCurricular(),
                            alocacao.getTurmaUnidadeCurricular().getUnidadeCurricular().getEmenta()
                    );

                    TurmaUnidadeCurricularDTO turmaUnidadeCurricularDTO = new TurmaUnidadeCurricularDTO(
                            alocacao.getTurmaUnidadeCurricular().getId(),
                            alocacao.getTurmaUnidadeCurricular().getCodigoTurma(),
                            unidadeCurricularDTO
                    );

                    SituacaoAlocacaoDiscenteDTO situacaoDTO = new SituacaoAlocacaoDiscenteDTO(
                            alocacao.getSituacaoAlocacaoDiscente().getId(),
                            alocacao.getSituacaoAlocacaoDiscente().getDescricao()
                    );

                    List<NotaDTO> notasDTO = alocacao.getFichaIndividualAlocacaoDiscente().getNotas().stream()
                            .map(nota -> new NotaDTO(nota.getId(), nota.getValor(), nota.isReposicao()))
                            .collect(Collectors.toList());

                    FichaIndividualDiscenteDTO fichaDTO = new FichaIndividualDiscenteDTO(
                            alocacao.getFichaIndividualAlocacaoDiscente().getId(),
                            notasDTO,
                            alocacao.getFichaIndividualAlocacaoDiscente().getFaltas(),
                            alocacao.getFichaIndividualAlocacaoDiscente().getResultadoFinal()
                    );

                    return new AlocacaoDiscenteDTO(
                            alocacao.getId(),
                            discenteDTO,
                            turmaUnidadeCurricularDTO,
                            situacaoDTO,
                            fichaDTO
                    );
                })
                .collect(Collectors.toList());
    }

}