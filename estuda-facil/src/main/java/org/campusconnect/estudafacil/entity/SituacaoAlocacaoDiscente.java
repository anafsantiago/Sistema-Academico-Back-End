package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "situacao_alocacao_discente")
public class SituacaoAlocacaoDiscente {

    public static final String SITUACAO_ATIVA = "ATIVA";
    public static final String SITUACAO_REPROVADA = "REPROVADA";
    public static final String SITUACAO_APROVADA = "APROVADA";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 10)
    private String descricao;

}