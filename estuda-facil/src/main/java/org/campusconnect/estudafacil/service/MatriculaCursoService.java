package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.CursoDTO;
import org.campusconnect.estudafacil.dto.MatriculaCursoDTO;
import org.campusconnect.estudafacil.entity.CalendarioAcademico;
import org.campusconnect.estudafacil.entity.Curso;
import org.campusconnect.estudafacil.entity.Discente;
import org.campusconnect.estudafacil.entity.MatriculaCurso;
import org.campusconnect.estudafacil.entity.SituacaoMatricula;
import org.campusconnect.estudafacil.repository.MatriculaCursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MatriculaCursoService {

    private final MatriculaCursoRepository matriculaCursoRepository;
    private final SituacaoMatriculaService situacaoMatriculaService;
    private final CursoService cursoService;

    private String gerarCodigoUnico(String siglaCurso, String anoMatriculaCurso, int semestreVigente, String codDiscente) {
        StringBuilder codigoCurso = new StringBuilder();
        codigoCurso.append(siglaCurso);
        codigoCurso.append(anoMatriculaCurso);
        codigoCurso.append(semestreVigente);
        codigoCurso.append(codDiscente);
        return codigoCurso.toString();
    }

    @Transactional
    public String cadastrarMatriculaCurso(Discente discente, long idCurso, CalendarioAcademico calendarioAcademico) {
        Curso curso = cursoService.getCursoPorId(idCurso);
        SituacaoMatricula situacaoMatricula = situacaoMatriculaService.getSituacaoMatriculaPorDescricao(SituacaoMatricula.SITUACAO_ATIVA);
        MatriculaCurso matriculaCurso = new MatriculaCurso();
        matriculaCurso.setDiscente(discente);
        matriculaCurso.setCurso(curso);
        matriculaCurso.setDataMatricula(LocalDate.now());
        matriculaCurso.setCodMatricula(gerarCodigoUnico(curso.getSiglaCurso(),
                matriculaCurso.getAnoMatricula(),
                calendarioAcademico.getSemestreVigente(),
                discente.getCincoUltimosCaracteresCodDiscente()));
        matriculaCurso.setSituacaoMatricula(situacaoMatricula);
        matriculaCurso.setAnoLetivo(calendarioAcademico.getAnoLetivo());
        matriculaCurso.setPeriodo(MatriculaCurso.PERIODO_NOVA_MATRICULA);
        matriculaCursoRepository.save(matriculaCurso);
        return "Matrícula cadastrada com sucesso.";
    }

    public MatriculaCursoDTO carregarDadosMatriculaDiscente(long idPessoa) {
        MatriculaCurso matriculaDiscente = matriculaCursoRepository.findMatriculaCursoAtivaByPessoaId(idPessoa)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrada matrícula ativa para esse discente."));

        CursoDTO cursoDTO = new CursoDTO(
                matriculaDiscente.getCurso().getId(),
                matriculaDiscente.getCurso().getNomeCurso(),
                matriculaDiscente.getCurso().getSiglaCurso()
        );

        return new MatriculaCursoDTO(
                matriculaDiscente.getId(),
                cursoDTO,
                matriculaDiscente.getPeriodo()
        );
    }

}