package org.campusconnect.estudafacil.entity;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "unidade_curricular")
public class UnidadeCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome_unidade_curricular", nullable = false, unique = true, length = 25)
    private String nomeUnidadeCurricular;

    @Column(name = "sigla_unidade_curricular", nullable = false, unique = true, length = 9)
    private String siglaUnidadeCurricular;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String ementa;

    public void setNomeUnidadeCurricular(String nomeUnidadeCurricular) {
        this.nomeUnidadeCurricular = nomeUnidadeCurricular;
        this.siglaUnidadeCurricular = StringUtil.gerarSigla(nomeUnidadeCurricular);
    }

}