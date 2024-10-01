package org.campusconnect.estudafacil.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autenticacao")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService autenticacaoService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Login login) {
        var token = autenticacaoService.generateToken(login);
        return ResponseEntity.ok(token);
    }

}