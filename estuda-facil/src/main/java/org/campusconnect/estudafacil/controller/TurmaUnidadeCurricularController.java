package org.campusconnect.estudafacil.controller;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.TurmaUnidadeCurricularDTO;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.campusconnect.estudafacil.service.ConsolidacaoTurmaService;
import org.campusconnect.estudafacil.service.TurmaUnidadeCurricularService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/turma")
@RequiredArgsConstructor
public class TurmaUnidadeCurricularController {

    private final TurmaUnidadeCurricularService turmaUnidadeCurricularService;
    private final ConsolidacaoTurmaService consolidacaoTurmaService;

    @GetMapping("/carregar-turmas-alocaveis")
    public ResponseEntity<List<TurmaUnidadeCurricularDTO>> carregarTurmas(@RequestParam long idDiscente) {
        List<TurmaUnidadeCurricularDTO> turmasUnidades = turmaUnidadeCurricularService.getAllTurmasAbertasAlocaveis(idDiscente);
        return ResponseEntity.ok().body(turmasUnidades);
    }

    @GetMapping("/gerar-codigo")
    public ResponseEntity<String> gerarCodigoTurma(@RequestParam String siglaUnidadeCurricular,
                                                   @RequestParam String abvTurno) {
        String codigoTurma = turmaUnidadeCurricularService.gerarCodigoTurma(siglaUnidadeCurricular, abvTurno);
        return ResponseEntity.ok(codigoTurma);
    }

/*    @PutMapping("/cadastrar")
    public ResponseEntity<String> cadastrarTurma(@RequestBody TurmaUnidadeCurricular turmaUnidadeCurricular,
                                                 @RequestParam List<Long> idsHorarios) {
        String mensagem = turmaUnidadeCurricularService.cadastrarTurmaUnidadeCurricular(turmaUnidadeCurricular, idsHorarios);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }*/

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarTurma(@RequestBody TurmaUnidadeCurricular turmaUnidadeCurricular) {
        String mensagem = turmaUnidadeCurricularService.cadastrarTurmaUnidadeCurricular(turmaUnidadeCurricular);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @PutMapping("/consolidar-turma")
    public ResponseEntity<String> consolidarTurma(@RequestParam long idTurma) {
        String mensagem = consolidacaoTurmaService.consolidarTurma(idTurma);
        return ResponseEntity.ok(mensagem);
    }

}