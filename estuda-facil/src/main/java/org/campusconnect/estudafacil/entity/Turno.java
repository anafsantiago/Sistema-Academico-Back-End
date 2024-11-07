package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 10)
    private String descricao;

    @Column(name = "abreviacao_turno", nullable = false, unique = true, length = 5)
    private String abreviacaoTurno;

    public void setDescricao(String descricao) {
        this.descricao = descricao.toUpperCase().trim();
        if (descricao != null && descricao.length() >= 3) {
            abreviacaoTurno = descricao.substring(0, 3).toUpperCase();
        } else {
            abreviacaoTurno = descricao;
        }
    }

}