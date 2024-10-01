package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.SituacaoTurma;
import org.campusconnect.estudafacil.repository.SituacaoTurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SituacaoTurmaService {

    private final SituacaoTurmaRepository situacaoTurmaRepository;

    public SituacaoTurma getSitucaoTurmaPorDescricao(String descricaoSituacao) {
        return situacaoTurmaRepository.findByDescricao(descricaoSituacao).orElseThrow(() -> new IllegalArgumentException("Situação Turma não encontrada."));
    }

}