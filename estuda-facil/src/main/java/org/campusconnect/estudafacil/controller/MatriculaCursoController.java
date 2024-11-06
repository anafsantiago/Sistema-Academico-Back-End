package org.campusconnect.estudafacil.controller;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.MatriculaCursoDTO;
import org.campusconnect.estudafacil.service.MatriculaCursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matricula-curso")
@RequiredArgsConstructor
public class MatriculaCursoController {

    private final MatriculaCursoService matriculaCursoService;

    @GetMapping("/carregar-matricula")
    public ResponseEntity<MatriculaCursoDTO> carregarMatriculaDiscente(@RequestParam long idPessoa) {
        MatriculaCursoDTO matriculaDiscente = matriculaCursoService.carregarDadosMatriculaDiscente(idPessoa);
        return ResponseEntity.ok().body(matriculaDiscente);
    }

}