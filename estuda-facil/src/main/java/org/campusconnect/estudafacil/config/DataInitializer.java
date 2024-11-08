package org.campusconnect.estudafacil.config;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.entity.CalendarioAcademico;
import org.campusconnect.estudafacil.entity.Curso;
import org.campusconnect.estudafacil.entity.GradeCurricular;
import org.campusconnect.estudafacil.entity.Papel;
import org.campusconnect.estudafacil.entity.Pessoa;
import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDiscente;
import org.campusconnect.estudafacil.entity.SituacaoAlocacaoDocente;
import org.campusconnect.estudafacil.entity.SituacaoMatricula;
import org.campusconnect.estudafacil.entity.SituacaoTurma;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.campusconnect.estudafacil.entity.Turno;
import org.campusconnect.estudafacil.entity.UnidadeCurricular;
import org.campusconnect.estudafacil.repository.CalendarioAcademicoRepository;
import org.campusconnect.estudafacil.repository.CursoRepository;
import org.campusconnect.estudafacil.repository.GradeCurricularRepository;
import org.campusconnect.estudafacil.repository.PapelRepository;
import org.campusconnect.estudafacil.repository.PessoaRepository;
import org.campusconnect.estudafacil.repository.SituacaoAlocacaoDiscenteRepository;
import org.campusconnect.estudafacil.repository.SituacaoAlocacaoDocenteRepository;
import org.campusconnect.estudafacil.repository.SituacaoMatriculaRepository;
import org.campusconnect.estudafacil.repository.SituacaoTurmaRepository;
import org.campusconnect.estudafacil.repository.TurmaUnidadeCurricularRepository;
import org.campusconnect.estudafacil.repository.TurnoRepository;
import org.campusconnect.estudafacil.repository.UnidadeCurricularRepository;
import org.campusconnect.estudafacil.service.AlocacaoDiscenteTurmaService;
import org.campusconnect.estudafacil.service.AlocacaoDocenteTurmaService;
import org.campusconnect.estudafacil.service.DiscenteService;
import org.campusconnect.estudafacil.service.DocenteService;
import org.campusconnect.estudafacil.service.TurmaUnidadeCurricularService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PessoaRepository pessoaRepository;
    private final UnidadeCurricularRepository unidadeCurricularRepository;
    private final GradeCurricularRepository gradeCurricularRepository;
    private final CursoRepository cursoRepository;
    private final SituacaoMatriculaRepository situacaoMatriculaRepository;
    private final SituacaoTurmaRepository situacaoTurmaRepository;
    private final SituacaoAlocacaoDiscenteRepository situacaoAlocacaoDiscenteRepository;
    private final SituacaoAlocacaoDocenteRepository situacaoAlocacaoDocenteRepository;
    private final PapelRepository papelRepository;
    private final CalendarioAcademicoRepository calendarioAcademicoRepository;
    private final TurnoRepository turnoRepository;
    private final DiscenteService discenteService;
    private final DocenteService docenteService;
    private final TurmaUnidadeCurricularService turmaUnidadeCurricularService;
    private final AlocacaoDiscenteTurmaService alocacaoDiscenteTurmaService;
    private final AlocacaoDocenteTurmaService alocacaoDocenteTurmaService;

    @Override
    public void run(String... args) {

        criarSituacoes();
        criarPapeis();
        criarCalendarioAcademico();
        criarTurnos();

        Pessoa pessoaDiscente = Pessoa.builder()
                .nome("Aluno Exemplo")
                .dataNascimento(LocalDate.of(2000, 1, 1))
                .cpf("12345678901")
                .contato("123456789")
                .email("aluno@examplo.com")
                .dataCadastro(LocalDate.now())
                .build();

        Pessoa pessoaDiscente2 = Pessoa.builder()
                .nome("Aluno Exemplo2")
                .dataNascimento(LocalDate.of(2002, 2, 2))
                .cpf("22345678903")
                .contato("223456789")
                .email("aluno@examplo2.com")
                .dataCadastro(LocalDate.now())
                .build();

        Pessoa pessoaDiscente3 = Pessoa.builder()
                .nome("Aluno Exemplo3")
                .dataNascimento(LocalDate.of(2003, 3, 3))
                .cpf("32345678903")
                .contato("323456789")
                .email("aluno@examplo3.com")
                .dataCadastro(LocalDate.now())
                .build();

        Pessoa pessoaDocente = Pessoa.builder()
                .nome("Professor Exemplo")
                .dataNascimento(LocalDate.of(1980, 1, 1))
                .cpf("12345678902")
                .contato("987654321")
                .email("professor@examplo.com")
                .dataCadastro(LocalDate.now())
                .build();

        Pessoa pessoaDocenteGestor = Pessoa.builder()
                .nome("Gestor Exemplo")
                .dataNascimento(LocalDate.of(1970, 1, 1))
                .cpf("12345678903")
                .contato("555555555")
                .email("gestor@examplo.com")
                .dataCadastro(LocalDate.now())
                .build();

        pessoaRepository.save(pessoaDiscente);
        pessoaRepository.save(pessoaDiscente2);
        pessoaRepository.save(pessoaDiscente3);
        pessoaRepository.save(pessoaDocente);
        pessoaRepository.save(pessoaDocenteGestor);

        discenteService.cadastrarDiscente(pessoaDiscente.getId(), criarCurso());
        discenteService.cadastrarDiscente(pessoaDiscente2.getId(), 1);
        discenteService.cadastrarDiscente(pessoaDiscente3.getId(), 1);
        criarTurmas();
        docenteService.cadastrarDocente(pessoaDocente.getId(), false);
        docenteService.cadastrarDocente(pessoaDocenteGestor.getId(), true);

        alocacaoDiscenteTurmaService.cadastrarAlocacaoDiscenteTurma(1, 1);
        alocacaoDiscenteTurmaService.cadastrarAlocacaoDiscenteTurma(2, 2);
        alocacaoDiscenteTurmaService.cadastrarAlocacaoDiscenteTurma(3, 2);

        alocacaoDocenteTurmaService.cadastrarAlocacaoDocenteTurma(1, 1);
        alocacaoDocenteTurmaService.cadastrarAlocacaoDocenteTurma(1, 2);
    }

    private long criarCurso() {

        List<UnidadeCurricular> unidadesCurriculares = new ArrayList<>();

        UnidadeCurricular unidade1 = new UnidadeCurricular();
        unidade1.setNomeUnidadeCurricular("Ambientes computacionais e conectividade");
        unidade1.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade1);

        UnidadeCurricular unidade2 = new UnidadeCurricular();
        unidade2.setNomeUnidadeCurricular("Análise de dados e big data");
        unidade2.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade2);

        UnidadeCurricular unidade3 = new UnidadeCurricular();
        unidade3.setNomeUnidadeCurricular("Computação gráfica e realidade virtual");
        unidade3.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade3);

        UnidadeCurricular unidade4 = new UnidadeCurricular();
        unidade4.setNomeUnidadeCurricular("Core curriculum");
        unidade4.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade4);

        UnidadeCurricular unidade5 = new UnidadeCurricular();
        unidade5.setNomeUnidadeCurricular("Estruturas de dados e análise de algoritmos");
        unidade5.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade5);

        UnidadeCurricular unidade6 = new UnidadeCurricular();
        unidade6.setNomeUnidadeCurricular("Estruturas matemáticas");
        unidade6.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade6);

        UnidadeCurricular unidade7 = new UnidadeCurricular();
        unidade7.setNomeUnidadeCurricular("Gestão e qualidade de software");
        unidade7.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade7);

        UnidadeCurricular unidade8 = new UnidadeCurricular();
        unidade8.setNomeUnidadeCurricular("Inovação, sustentabilidade e competitividade empresarial");
        unidade8.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade8);

        UnidadeCurricular unidade9 = new UnidadeCurricular();
        unidade9.setNomeUnidadeCurricular("Inteligência artificial");
        unidade9.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade9);

        UnidadeCurricular unidade10 = new UnidadeCurricular();
        unidade10.setNomeUnidadeCurricular("Modelagem de software");
        unidade10.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade10);

        UnidadeCurricular unidade11 = new UnidadeCurricular();
        unidade11.setNomeUnidadeCurricular("Modelos, métodos e técnicas da engenharia de software");
        unidade11.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade11);

        UnidadeCurricular unidade12 = new UnidadeCurricular();
        unidade12.setNomeUnidadeCurricular("Programação de soluções computacionais");
        unidade12.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade12);

        UnidadeCurricular unidade13 = new UnidadeCurricular();
        unidade13.setNomeUnidadeCurricular("Sistemas computacionais e segurança");
        unidade13.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade13);

        UnidadeCurricular unidade14 = new UnidadeCurricular();
        unidade14.setNomeUnidadeCurricular("Sistemas distribuídos e mobile");
        unidade14.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade14);

        UnidadeCurricular unidade15 = new UnidadeCurricular();
        unidade15.setNomeUnidadeCurricular("Teoria da computação e compiladores");
        unidade15.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade15);

        UnidadeCurricular unidade16 = new UnidadeCurricular();
        unidade16.setNomeUnidadeCurricular("Usabilidade, desenvolvimento web, mobile e jogos");
        unidade16.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade16);

        UnidadeCurricular unidade17 = new UnidadeCurricular();
        unidade17.setNomeUnidadeCurricular("Vida & Carreira");
        unidade17.setEmenta("Ementa da unidade curricular.");
        unidadesCurriculares.add(unidade17);
        unidadeCurricularRepository.saveAll(unidadesCurriculares);

        GradeCurricular gradeCurricular = new GradeCurricular();
        gradeCurricular.setUnidadesCurriculares(unidadesCurriculares);
        gradeCurricularRepository.save(gradeCurricular);

        Curso curso = new Curso();
        curso.setNomeCurso("Ciência da Computação");
        curso.setCargaHorariaTotal(3000);
        curso.setGradeCurricular(gradeCurricular);
        cursoRepository.save(curso);
        return curso.getId();
    }

    private void criarSituacoes() {

        situacaoAlocacaoDiscenteRepository.saveAll(Arrays.asList(
                SituacaoAlocacaoDiscente.builder().descricao(SituacaoAlocacaoDiscente.SITUACAO_ATIVA).build(),
                SituacaoAlocacaoDiscente.builder().descricao(SituacaoAlocacaoDiscente.SITUACAO_REPROVADA).build(),
                SituacaoAlocacaoDiscente.builder().descricao(SituacaoAlocacaoDiscente.SITUACAO_APROVADA).build()
        ));

        situacaoAlocacaoDocenteRepository.saveAll(Arrays.asList(
                SituacaoAlocacaoDocente.builder().descricao(SituacaoAlocacaoDocente.SITUACAO_ATIVA).build(),
                SituacaoAlocacaoDocente.builder().descricao(SituacaoAlocacaoDocente.SITUACAO_FINALIZADA).build()
        ));

        situacaoMatriculaRepository.saveAll(Arrays.asList(
                SituacaoMatricula.builder().descricao(SituacaoMatricula.SITUACAO_ATIVA).build(),
                SituacaoMatricula.builder().descricao(SituacaoMatricula.SITUACAO_CONCLUIDA).build(),
                SituacaoMatricula.builder().descricao(SituacaoMatricula.SITUACAO_CANCELADA).build()
        ));

        situacaoTurmaRepository.saveAll(Arrays.asList(
                SituacaoTurma.builder().descricao(SituacaoTurma.SITUACAO_ABERTA).build(),
                SituacaoTurma.builder().descricao(SituacaoTurma.SITUACAO_CONSOLIDADA).build()
        ));
    }

    private void criarPapeis() {
        papelRepository.saveAll(Arrays.asList(
                Papel.builder().descricao(Papel.PAPEL_ADMIN).build(),
                Papel.builder().descricao(Papel.PAPEL_DISCENTE).build(),
                Papel.builder().descricao(Papel.PAPEL_DOCENTE).build(),
                Papel.builder().descricao(Papel.PAPEL_GESTOR).build()
        ));
    }

    private void criarCalendarioAcademico() {
        int anoLetivo = LocalDate.now().getYear();
        CalendarioAcademico calendario = CalendarioAcademico.builder()
                .anoLetivo(anoLetivo)
                .inicioPrimeiroSemestre(LocalDate.of(anoLetivo, 2, 20))
                .fimPrimeiroSemestre(LocalDate.of(anoLetivo, 6, 30))
                .inicioSegundoSemestre(LocalDate.of(anoLetivo, 8, 15))
                .fimSegundoSemestre(LocalDate.of(anoLetivo, 12, 24))
                .build();
        calendarioAcademicoRepository.save(calendario);
    }

    private void criarTurnos() {

        Turno turnoMatutino = new Turno();
        turnoMatutino.setDescricao("MATUTINO");
        turnoRepository.save(turnoMatutino);

        Turno turnoVespertino = new Turno();
        turnoVespertino.setDescricao("VESPERTINO");
        turnoRepository.save(turnoVespertino);

        Turno turnoNoturno = new Turno();
        turnoNoturno.setDescricao("NOTURNO");
        turnoRepository.save(turnoNoturno);

    }

    private void criarTurmas() {
        TurmaUnidadeCurricular turmaUnidadeCurricular = new TurmaUnidadeCurricular();
        UnidadeCurricular unidadeCurricular = new UnidadeCurricular();
        unidadeCurricular.setId(1L);
        unidadeCurricular.setNomeUnidadeCurricular("Ambientes computacionais e conectividade");
        unidadeCurricular.setEmenta("Ementa da unidade curricular ACC.");

        Turno turno = new Turno();
        turno.setId(1L);
        turno.setDescricao("MATUTINO");
        turno.setAbreviacaoTurno("MAT");
        String codTurma = turmaUnidadeCurricularService.gerarCodigoTurma(unidadeCurricular.getSiglaUnidadeCurricular(),
                turno.getAbreviacaoTurno());
        turmaUnidadeCurricular.setCodigoTurma(codTurma);
        turmaUnidadeCurricular.setUnidadeCurricular(unidadeCurricular);
        turmaUnidadeCurricular.setTurno(turno);
        turmaUnidadeCurricular.setQuantidadeVagas(50);
        turmaUnidadeCurricularService.cadastrarTurmaUnidadeCurricular(turmaUnidadeCurricular);

        TurmaUnidadeCurricular turmaUnidadeCurricular2 = new TurmaUnidadeCurricular();
        UnidadeCurricular unidadeCurricular2 = new UnidadeCurricular();
        unidadeCurricular2.setId(2L);
        unidadeCurricular2.setNomeUnidadeCurricular("Análise de dados e big data");
        unidadeCurricular2.setEmenta("Ementa da unidade curricular ADBD.");

        Turno turno2 = new Turno();
        turno2.setId(2L);
        turno2.setDescricao("VESPERTINO");
        turno2.setAbreviacaoTurno("VES");
        String codTurma2 = turmaUnidadeCurricularService.gerarCodigoTurma(unidadeCurricular2.getSiglaUnidadeCurricular(),
                turno2.getAbreviacaoTurno());
        turmaUnidadeCurricular2.setCodigoTurma(codTurma2);
        turmaUnidadeCurricular2.setUnidadeCurricular(unidadeCurricular2);
        turmaUnidadeCurricular2.setTurno(turno2);
        turmaUnidadeCurricular2.setQuantidadeVagas(50);
        turmaUnidadeCurricularService.cadastrarTurmaUnidadeCurricular(turmaUnidadeCurricular2);
    }

}