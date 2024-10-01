package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDiscente;
import org.campusconnect.estudafacil.repository.SituacaoAlocacaoDiscenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SituacaoAlocacaoDiscenteService {

    private final SituacaoAlocacaoDiscenteRepository situacaoAlocacaoDiscenteRepository;

    public SituacaoAlocacaoDiscente getSituacaoAlocacaoDiscentePorDescricao(String situacaoAlocacaoDiscenteDescricao) {
        return situacaoAlocacaoDiscenteRepository.findByDescricao(situacaoAlocacaoDiscenteDescricao)
                .orElseThrow(()-> new IllegalArgumentException("Situação Alocação Discente nao encontrada."));
    }

}