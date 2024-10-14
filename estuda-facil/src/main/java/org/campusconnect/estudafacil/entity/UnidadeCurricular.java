package org.campusconnect.estudafacil.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import org.campusconnect.estudafacil.util.StringUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "unidade_curricular")
public class UnidadeCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome_unidade_curricular", nullable = false, unique = true, length = 100)
    private String nomeUnidadeCurricular;

    @Column(name = "sigla_unidade_curricular", nullable = false, unique = true, length = 9)
    private String siglaUnidadeCurricular;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String ementa;

    @ManyToMany(mappedBy = "unidadesCurriculares", fetch = FetchType.EAGER)
    private List<GradeCurricular> gradesCurriculares;

    public void setNomeUnidadeCurricular(String nomeUnidadeCurricular) {
        this.nomeUnidadeCurricular = nomeUnidadeCurricular;
        this.siglaUnidadeCurricular = StringUtil.gerarSigla(nomeUnidadeCurricular);
    }

}