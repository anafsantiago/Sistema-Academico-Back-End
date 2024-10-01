package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.entity.SituacaoMatricula;
import org.campusconnect.estudafacil.service.SituacaoMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/situacao-matricula")
@RequiredArgsConstructor
public class SituacaoMatriculaController {

    private final SituacaoMatriculaService situacaoMatriculaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarSituacaoMatricula(@RequestBody SituacaoMatricula situacaoMatricula) {
        String mensagem = situacaoMatriculaService.cadastrarSitucaoMatricula(situacaoMatricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

}