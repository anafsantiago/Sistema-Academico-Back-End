package org.campusconnect.estudafacil.controller;

import org.campusconnect.estudafacil.entity.Pessoa;
import org.campusconnect.estudafacil.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.getPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable long id) {
        Pessoa pessoa = pessoaService.getPessoaPorId(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarPessoa(@RequestBody Pessoa pessoa) {
        String mensagem = pessoaService.cadastrarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarPessoa(
            @PathVariable long id,
            @RequestBody Pessoa pessoaAtualizada) {
        String mensagem = pessoaService.atualizarPessoa(id, pessoaAtualizada);
        return ResponseEntity.ok(mensagem);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPessoa(@PathVariable long id) {
        String mensagem = pessoaService.deletarPessoa(id);
        return ResponseEntity.ok(mensagem);
    }

}