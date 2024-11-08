package org.campusconnect.estudafacil.controller;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.UnidadeCurricularDTO;
import org.campusconnect.estudafacil.service.UnidadeCurricularService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/unidade")
public class UnidadeCurricularController {

    private final UnidadeCurricularService unidadeCurricularService;

    @GetMapping("/carregar-unidades")
    public ResponseEntity<List<UnidadeCurricularDTO>> carregarUnidades() {
        List<UnidadeCurricularDTO> unidadesCurriculares = unidadeCurricularService.getAll();
        return ResponseEntity.ok().body(unidadesCurriculares);
    }
}
