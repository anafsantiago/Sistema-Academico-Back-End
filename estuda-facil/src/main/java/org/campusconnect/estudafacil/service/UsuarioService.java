package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.Papel;
import org.campusconnect.estudafacil.entity.Pessoa;
import org.campusconnect.estudafacil.entity.Usuario;
import org.campusconnect.estudafacil.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PapelService papelService;
    private final PessoaService pessoaService;

    @Transactional
    public String cadastrarUsuario(Pessoa pessoa, String descricaoPapel) {
        return cadastrarUsuario(pessoa, descricaoPapel, false);
    }

    @Transactional
    public String cadastrarUsuario(Pessoa pessoa, String descricaoPapel, boolean gestor) {
        Usuario usuario = new Usuario();
        List<String> descricoesPapeis = new ArrayList<>();
        descricoesPapeis.add(descricaoPapel);
        if (gestor)
            descricoesPapeis.add(Papel.PAPEL_GESTOR);
        Set<Papel> papeis = new HashSet<>(papelService.buscarPapeisPorDescricoes(descricoesPapeis));
        usuario.setPessoa(pessoa);
        usuario.setPapeis(papeis);
        if (descricaoPapel.equals(Papel.PAPEL_DISCENTE)) {
            usuario.setUsuario(pessoaService.gerarCodigoUnico(pessoa, false));
            usuario.setSenha(passwordEncoder.encode(pessoa.getDataNascimentoFormatada()));
        } else {
            usuario.setUsuario(pessoaService.gerarCodigoUnico(pessoa, true));
            usuario.setSenha(passwordEncoder.encode(pessoa.getCpf()));
        }
        usuarioRepository.save(usuario);
        return "Usuário cadastrado com sucesso.";
    }

}