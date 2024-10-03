package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.Pessoa;
import org.campusconnect.estudafacil.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public String gerarCodigoUnico(Pessoa pessoa, boolean isDocente) {
        StringBuilder codigo = new StringBuilder();
        if (isDocente) {
            codigo.append(pessoa.getCincoUltimosDigitosCpf());
        } else {
            codigo.append(pessoa.getTresPrimeirosDigitosCpf());
        }
        codigo.append(pessoa.getDiaMesDataNascimento());
        codigo.append(pessoa.getAnoDataCadastro());
        return codigo.toString();
    }

    @Transactional
    public String cadastrarPessoa(Pessoa pessoa) {
        if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        if (pessoaRepository.existsByEmail(pessoa.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        if (pessoaRepository.existsByContato(pessoa.getContato())) {
            throw new IllegalArgumentException("Contato já cadastrado.");
        }
        pessoa.setDataCadastro(LocalDate.now());
        pessoaRepository.save(pessoa);
        return "Cadastro realizado com sucesso.";
    }

    public Pessoa getPessoaPorId(long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada."));
    }

    public List<Pessoa> getPessoas() {
        return pessoaRepository.findAll();
    }

    @Transactional
    public String atualizarPessoa(long id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = getPessoaPorId(id);
        if (!pessoaExistente.getCpf().equals(pessoaAtualizada.getCpf())) {
            if (pessoaRepository.existsByCpf(pessoaAtualizada.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado.");
            }
            pessoaExistente.setCpf(pessoaAtualizada.getCpf());
        }
        if (!pessoaExistente.getEmail().equals(pessoaAtualizada.getEmail())) {
            if (pessoaRepository.existsByEmail(pessoaAtualizada.getEmail())) {
                throw new IllegalArgumentException("Email já cadastrado.");
            }
            pessoaExistente.setEmail(pessoaAtualizada.getEmail());
        }
        if (!pessoaExistente.getContato().equals(pessoaAtualizada.getContato())) {
            if (pessoaRepository.existsByContato(pessoaAtualizada.getContato())) {
                throw new IllegalArgumentException("Contato já cadastrado.");
            }
            pessoaExistente.setContato(pessoaAtualizada.getContato());
        }
        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaRepository.save(pessoaExistente);
        return "Atualização bem sucedida.";
    }

    @Transactional
    public String deletarPessoa(long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada."));
        pessoaRepository.delete(pessoa);
        return "Pessoa removida com sucesso.";
    }

}