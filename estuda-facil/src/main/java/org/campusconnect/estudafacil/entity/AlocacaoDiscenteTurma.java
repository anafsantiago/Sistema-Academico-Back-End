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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alocacao_discente_turma")
public class AlocacaoDiscenteTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_discente", nullable = false)
    private Discente discente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_turma_unidade_curricular", nullable = false)
    private TurmaUnidadeCurricular turmaUnidadeCurricular;

    @Column(nullable = false)
    private int semestre;

    @Column(name = "ano_letivo", nullable = false)
    private int anoLetivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_situacao_alocacao_discente")
    private SituacaoAlocacaoDiscente situacaoAlocacaoDiscente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ficha_individual_alocacao_discente")
    private FichaIndividualAlocacaoDiscente fichaIndividualAlocacaoDiscente;

}