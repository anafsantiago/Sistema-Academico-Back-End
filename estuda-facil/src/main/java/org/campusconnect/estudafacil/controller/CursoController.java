package org.campusconnect.estudafacil.controller;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.CursoDTO;
import org.campusconnect.estudafacil.entity.Curso;
import org.campusconnect.estudafacil.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/carregar-curso")
    public ResponseEntity<CursoDTO> carregarCursoPorIdTurma(@RequestParam long idTurma) {
        CursoDTO curso = cursoService.carregarCursoPorIdTurma(idTurma);
        return ResponseEntity.ok().body(curso);
    }

}