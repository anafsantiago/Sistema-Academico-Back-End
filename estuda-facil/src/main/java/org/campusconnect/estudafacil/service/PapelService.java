package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.Papel;
import org.campusconnect.estudafacil.repository.PapelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PapelService {

    private final PapelRepository papelRepository;

    public Set<Papel> buscarPapeisPorDescricoes(List<String> descricoes){
        return papelRepository.findByDescricaoIn(descricoes)
                .orElseThrow(() -> new IllegalArgumentException("Pape(l/is) não encontrado(s)."));
    }

    @Transactional
    public String cadastrarPapel (Papel papel){
        papelRepository.save(papel);
        return "Papel cadastrado com sucesso.";
    }

}