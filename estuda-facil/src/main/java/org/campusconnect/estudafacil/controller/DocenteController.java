package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.service.DocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/docente")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService docenteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarDocente(
            @RequestParam long idPessoa,
            @RequestParam boolean isGestor) {
        String mensagem = docenteService.cadastrarDocente(idPessoa, isGestor);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

}