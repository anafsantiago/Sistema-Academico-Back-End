package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.Docente;
import org.campusconnect.estudafacil.entity.Papel;
import org.campusconnect.estudafacil.entity.Pessoa;
import org.campusconnect.estudafacil.repository.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final PessoaService pessoaService;
    private final UsuarioService usuarioService;

    public Docente getdDocentePorId(long idDocente) {
        return docenteRepository.findById(idDocente).orElseThrow(() -> new IllegalArgumentException("Docente não encontrado."));
    }

    public String cadastrarDocente(long idPessoa, boolean isGestor) {
        Pessoa pessoaDocente = pessoaService.getPessoaPorId(idPessoa);
        Docente docente = new Docente();
        docente.setPessoa(pessoaDocente);
        docente.setCargaHorariaDisponivel(Docente.CARGA_HORARIA_MAX_SEMANAL);
        docente.setCargaHorariaAlocada(0);
        docente.setDataCadastro(LocalDate.now());
        docente.setGestor(isGestor);
        docente.setCodDocente(pessoaService.gerarCodigoUnico(pessoaDocente, true));
        docenteRepository.save(docente);
        String mensagemUsuario = usuarioService.cadastrarUsuario(pessoaDocente, Papel.PAPEL_DOCENTE, isGestor);
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Docente cadastrado com sucesso.")
                .append("\n")
                .append(mensagemUsuario);
        return mensagem.toString();
    }

}