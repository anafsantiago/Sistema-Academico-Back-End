package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.entity.Papel;
import org.campusconnect.estudafacil.service.PapelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/papel")
@RequiredArgsConstructor
public class PapelController {

    private final PapelService papelService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarPapel(@RequestBody Papel papel) {
        String mensagem = papelService.cadastrarPapel(papel);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

}