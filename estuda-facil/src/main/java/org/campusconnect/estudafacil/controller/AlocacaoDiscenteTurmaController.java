package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.dto.AlocacaoDiscenteDTO;
import org.campusconnect.estudafacil.service.AlocacaoDiscenteTurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alocacao-discente")
@RequiredArgsConstructor
public class AlocacaoDiscenteTurmaController {

    private final AlocacaoDiscenteTurmaService alocacaoDiscenteTurmaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarAlocacaoDiscenteTurma(@RequestParam long idDiscente, @RequestParam long idTurma) {
        String mensagem = alocacaoDiscenteTurmaService.cadastrarAlocacaoDiscenteTurma(idDiscente, idTurma);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @GetMapping("/carregar-alocacao")
    public ResponseEntity<List<AlocacaoDiscenteDTO>> carregarAlocacoesDiscenteTurmaPorIdPessoa(@RequestParam long idPessoa) {
        List<AlocacaoDiscenteDTO> alocacoesDiscente = alocacaoDiscenteTurmaService.carregarDadosAlocacoesDiscente(idPessoa);
        return ResponseEntity.ok().body(alocacoesDiscente);
    }

    @GetMapping("/carregar-alocacoes")
    public ResponseEntity<List<AlocacaoDiscenteDTO>> carregarAlocacoesDiscenteTurmaPorIdTurma(@RequestParam long idTurma) {
        List<AlocacaoDiscenteDTO> alocacoesDiscente = alocacaoDiscenteTurmaService.getaAllPorIdTurma(idTurma);
        return ResponseEntity.ok().body(alocacoesDiscente);
    }

}