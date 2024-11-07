package org.campusconnect.estudafacil.controller;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.AlocacaoDocenteDTO;
import org.campusconnect.estudafacil.service.AlocacaoDocenteTurmaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alocacao-docente")
@RequiredArgsConstructor
public class AlocacaoDocenteTurmaController {

    private final AlocacaoDocenteTurmaService alocacaoDocenteTurmaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarAlocacaoDiscenteTurma(@RequestParam long idDocente, @RequestParam long idTurma) {
        String mensagem = alocacaoDocenteTurmaService.cadastrarAlocacaoDocenteTurma(idDocente, idTurma);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @GetMapping("/carregar-alocacoes")
    public ResponseEntity<List<AlocacaoDocenteDTO>> carregarAlocacoesDiscenteTurma(@RequestParam long idPessoa) {
        List<AlocacaoDocenteDTO> alocacoesDocente = alocacaoDocenteTurmaService.carregarDadosAlocacoesDocente(idPessoa);
        return ResponseEntity.ok().body(alocacoesDocente);
    }

}