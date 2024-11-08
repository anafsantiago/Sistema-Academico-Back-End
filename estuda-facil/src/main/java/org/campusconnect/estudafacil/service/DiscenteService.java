package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.dto.DiscenteDTO;
import org.campusconnect.estudafacil.dto.PessoaDTO;
import org.campusconnect.estudafacil.entity.CalendarioAcademico;
import org.campusconnect.estudafacil.entity.Discente;
import org.campusconnect.estudafacil.entity.Papel;
import org.campusconnect.estudafacil.entity.Pessoa;
import org.campusconnect.estudafacil.repository.DiscenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DiscenteService {

    private final DiscenteRepository discenteRepository;
    private final CalendarioAcademicoService calendarioAcademicoService;
    private final MatriculaCursoService matriculaCursoService;
    private final PessoaService pessoaService;
    private final UsuarioService usuarioService;

    public Discente getDiscentePorId(long id) {
        return discenteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Discente não encontrado."));
    }

    public DiscenteDTO getDiscentePorCpf(String cpf) {
        Discente discente = discenteRepository.findByPessoaCpf(cpf).orElseThrow(() -> new IllegalArgumentException("Discente não encontrado."));
        PessoaDTO pessoaDTO = new PessoaDTO(
                discente.getPessoa().getId(),
                discente.getPessoa().getNome()
        );
        return new DiscenteDTO(discente.getId(), pessoaDTO, null);
    }

    @Transactional
    public String cadastrarDiscente(long idPessoa, long idCurso) {
        CalendarioAcademico calendarioAcademicoVigente = calendarioAcademicoService.getCalendarioAcademicoVigente();
        Pessoa pessoaDiscente = pessoaService.getPessoaPorId(idPessoa);
        Discente discente = new Discente();
        discente.setPessoa(pessoaDiscente);
        discente.setDataCadastro(LocalDate.now());
        discente.setCodDiscente(pessoaService.gerarCodigoUnico(pessoaDiscente, false));
        discenteRepository.save(discente);
        String mensagemUsuario = usuarioService.cadastrarUsuario(pessoaDiscente, Papel.PAPEL_DISCENTE);
        String mensagemMatricula = matriculaCursoService.cadastrarMatriculaCurso(discente, idCurso, calendarioAcademicoVigente);
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Discente cadastrado com sucesso.")
                .append("\n")
                .append(mensagemUsuario)
                .append("\n")
                .append(mensagemMatricula);
        return mensagem.toString();
    }

}