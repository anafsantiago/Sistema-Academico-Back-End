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
@Table(name = "situacao_matricula")
public class SituacaoMatricula {

    public static final String SITUACAO_ATIVA = "ATIVA";
    public static final String SITUACAO_CONCLUIDA = "CONCLUÍDA";
    public static final String SITUACAO_CANCELADA = "CANCELADA";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 9)
    private String descricao;

}