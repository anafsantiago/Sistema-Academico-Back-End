package org.campusconnect.estudafacil.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ficha_individual_alocacao_discente")
public class FichaIndividualAlocacaoDiscente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_boletim_ficha", nullable = false)
    List<Nota> notas;

    @Column(name = "resultado_final", nullable = false)
    private float resultadoFinal;

    @Column(nullable = false)
    private int faltas;

    @Column(name = "porcentagem_frequencia", nullable = false)
    private float porcentagemFrequencia;

}