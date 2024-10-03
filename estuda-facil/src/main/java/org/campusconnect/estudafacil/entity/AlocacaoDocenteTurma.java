package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alocacao_docente_turma")
public class AlocacaoDocenteTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_docente", nullable = false)
    private Docente docente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_turma_unidade_curricular", nullable = false, unique = true)
    private TurmaUnidadeCurricular turmaUnidadeCurricular;

    @Column(name = "data_inicio_alocacao", nullable = false)
    private LocalDate dataInicioAlocacao;

    @Column(name = "data_fim_alocacao", nullable = false)
    private LocalDate dataFimAlocacao;

    @Column(name = "carga_horaia_semanal", nullable = false)
    private Integer cargaHoraiaSemanal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_situacao_alocacao_docente", nullable = false)
    private SituacaoAlocacaoDocente situacaoAlocacaoDocente;

}