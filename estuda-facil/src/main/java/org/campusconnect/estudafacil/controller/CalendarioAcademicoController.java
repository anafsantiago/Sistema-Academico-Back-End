package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.entity.CalendarioAcademico;
import org.campusconnect.estudafacil.service.CalendarioAcademicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendario-academico")
@RequiredArgsConstructor
public class CalendarioAcademicoController {

    private final CalendarioAcademicoService calendarioAcademicoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCalendarioAcademico(@RequestBody CalendarioAcademico calendarioAcademico) {
        String mensagem = calendarioAcademicoService.cadastrarCalendarioAcademico(calendarioAcademico);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

}