package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.SituacaoMatricula;
import org.campusconnect.estudafacil.repository.SituacaoMatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SituacaoMatriculaService {

    private final SituacaoMatriculaRepository situacaoMatriculaRepository;

    public SituacaoMatricula getSituacaoMatriculaPorDescricao(String descricaoSituacao) {
        return situacaoMatriculaRepository.findByDescricao(descricaoSituacao).orElseThrow(() -> new IllegalArgumentException("Situação Matrícula não encontrada."));
    }

    @Transactional
    public String cadastrarSitucaoMatricula(SituacaoMatricula situacaoMatricula) {
        situacaoMatriculaRepository.save(situacaoMatricula);
        return "Situação Matrícula cadastrada com sucesso.";
    }

}