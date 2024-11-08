package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ficha_individual_alocacao_discente")
public class FichaIndividualAlocacaoDiscente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nota_primeira_unidade")
    private float notaUnidade1;

    @Column(name = "nota_segunda_unidade")
    private float notaUnidade2;

    @Column(name = "nota_terceira_unidade")
    private float notaUnidade3;

    @Column(name = "nota_reposicao")
    private float notaReposicao;

/*    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_presencario_ficha", nullable = false)
    private List<Presenca> presencas;*/

    @Column(nullable = false)
    private int faltas;

    @Column(name = "resultado_final", nullable = false)
    private float resultadoFinal;

    @Column(name = "porcentagem_frequencia", nullable = false)
    private float porcentagemFrequencia;

}