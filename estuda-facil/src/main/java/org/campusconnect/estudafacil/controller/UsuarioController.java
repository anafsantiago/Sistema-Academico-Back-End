package org.campusconnect.estudafacil.controller;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.UsuarioDTO;
import org.campusconnect.estudafacil.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/carregar-usuario-logado")
    public ResponseEntity<UsuarioDTO> buscarUsuarioLogado(@RequestParam String usuario) {
        UsuarioDTO usuarioLogado = usuarioService.getUsuarioLogado(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioLogado);
    }

}