package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.dto.CursoDTO;
import org.campusconnect.estudafacil.entity.Curso;
import org.campusconnect.estudafacil.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public Curso getCursoPorId(long id) {
        return cursoRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Curso não encontrado."));
    }

    public CursoDTO carregarCursoPorIdTurma(long idTurma) {
        Curso curso = cursoRepository.findCursoByIdTurma(idTurma)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        return new CursoDTO(
                curso.getId(),
                curso.getNomeCurso(),
                curso.getSiglaCurso()
        );
    }

    @Transactional
    public String cadastrarCurso(Curso curso) {
        cursoRepository.save(curso);
        return "Curso cadastrado com sucesso.";
    }

}