package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.entity.Nota;
import org.campusconnect.estudafacil.service.FichaIndividualAlocacaoDiscenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/ficha-individual")
@RequiredArgsConstructor
public class FichaIndividualAlocacaoDiscenteController {

    private final FichaIndividualAlocacaoDiscenteService fichaIndividualAlocacaoDiscenteService;

    @PutMapping("/calcular-resultado-final")
    public ResponseEntity<Float> calcularResultadoFinal(@RequestBody List<Nota> boletim) {
        float resultadoFinal = fichaIndividualAlocacaoDiscenteService.calcularResultadoFinal(boletim);
        return ResponseEntity.ok(resultadoFinal);
    }

    @PutMapping("/registrar-notas")
    public ResponseEntity<String> registrarFrequencias(@RequestBody HashMap<Long, List<Float>> notas){
        String mensagem = fichaIndividualAlocacaoDiscenteService.registrarNotas(notas);
        return ResponseEntity.ok(mensagem);
    }

}