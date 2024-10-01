package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.entity.Turno;
import org.campusconnect.estudafacil.service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/turno")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarTurno(@RequestBody Turno turno) {
        String mensagem = turnoService.cadastrarTurno(turno);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

}