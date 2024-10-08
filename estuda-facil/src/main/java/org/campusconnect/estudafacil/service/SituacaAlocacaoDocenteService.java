package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDocente;
import org.campusconnect.estudafacil.repository.SituacaoAlocacaoDocenteRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SituacaAlocacaoDocenteService {

    private final SituacaoAlocacaoDocenteRepository situacaoAlocacaoDocenteRepository;

    public SituacaoAlocacaoDocente getSituacaoAlocacaoDocentePorDescricao(String situacaoAlocacaoDocenteDescricao) {
        return situacaoAlocacaoDocenteRepository.findByDescricao(situacaoAlocacaoDocenteDescricao)
                .orElseThrow(()-> new IllegalArgumentException("Situação Alocação Docente não encontrada."));
    }

}