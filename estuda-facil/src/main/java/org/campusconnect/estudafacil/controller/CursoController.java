package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.entity.Curso;
import org.campusconnect.estudafacil.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/curso")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCurso(@RequestBody Curso curso) {
        String mensagem = cursoService.cadastrarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

}