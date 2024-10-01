package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Docente {

    public static final int CARGA_HORARIA_MAX_SEMANAL = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pessoa", nullable = false, unique = true)
    Pessoa pessoa;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "carga_horaria_disponivel", nullable = false)
    private int cargaHorariaDisponivel;

    @Column(name = "carga_horaria_alocada", nullable = false)
    private int cargaHorariaAlocada;

    private boolean gestor;

    @Column(name = "cod_docente", nullable = false, unique = true, length = 30)
    private String codDocente;

}