package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.AlocacaoDiscenteTurma;
import org.campusconnect.estudafacil.entity.Discente;
import org.campusconnect.estudafacil.entity.FichaIndividualAlocacaoDiscente;
import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDiscente;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.campusconnect.estudafacil.repository.AlocacaoDiscenteTurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}