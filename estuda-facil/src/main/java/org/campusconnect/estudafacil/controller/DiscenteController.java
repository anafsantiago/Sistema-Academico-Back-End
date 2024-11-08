package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.dto.DiscenteDTO;
import org.campusconnect.estudafacil.service.DiscenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discente")
public class DiscenteController {

    private final DiscenteService discenteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarDiscente(
            @RequestParam long idPessoa,
            @RequestParam long idCurso) {
        String mensagem = discenteService.cadastrarDiscente(idPessoa, idCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @GetMapping("/carregar-discente-cpf")
    public ResponseEntity<DiscenteDTO> carregarDiscente( @RequestParam String cpf ) {
        DiscenteDTO discente = discenteService.getDiscentePorCpf(cpf);
        return ResponseEntity.ok(discente);
    }

}