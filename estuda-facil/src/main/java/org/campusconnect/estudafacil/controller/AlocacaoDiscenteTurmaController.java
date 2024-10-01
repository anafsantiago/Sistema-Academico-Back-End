package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.service.AlocacaoDiscenteTurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}