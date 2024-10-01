package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matricula_curso")
public class MatriculaCurso {

    public static final int PERIODO_NOVA_MATRICULA = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_discente", nullable = false)
    private Discente discente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(name = "cod_matricula", nullable = false, unique = true, length = 50)
    private String codMatricula;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_situacao_matricula", nullable = false)
    private SituacaoMatricula situacaoMatricula;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @Column(name = "ano_letivo", nullable = false)
    private int anoLetivo;

    @Column(nullable = false)
    private int periodo;

    public String getAnoMatricula() {
        if (dataMatricula != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            return dataMatricula.format(formatter);
        }
        return null;
    }

}