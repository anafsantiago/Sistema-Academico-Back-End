package org.campusconnect.estudafacil.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "turma_unidade_curricular")
public class TurmaUnidadeCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "codigo_turma", nullable = false, unique = true, length = 30)
    private String codigoTurma;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidade_curricular", nullable = false)
    private UnidadeCurricular unidadeCurricular;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_situacao_turma", nullable = false)
    private SituacaoTurma situacaoTurma;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "turmaUnidade")
    private List<HorarioTurma> horarios;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_turno", nullable = false)
    private Turno turno;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(nullable = false)
    private int semestre;

    @Column(name = "ano_letivo", nullable = false)
    private int anoLetivo;

    @Column(name = "quantidade_vagas", nullable = false)
    private int quantidadeVagas;

}