package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "calendario_academico")
public class CalendarioAcademico {

    public static final int PRIMEIRO_SEMESTRE = 1;
    public static final int SEGUNDO_SEMESTRE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ano_letivo", nullable = false, unique = true)
    private int anoLetivo;

    @Column(name = "inicio_primeiro_semestre", nullable = false)
    private LocalDate inicioPrimeiroSemestre;

    @Column(name = "fim_primeiro_semestre", nullable = false)
    private LocalDate fimPrimeiroSemestre;

    @Column(name = "inicio_segundo_semestre", nullable = false)
    private LocalDate inicioSegundoSemestre;

    @Column(name = "fim_segundo_semestre", nullable = false)
    private LocalDate fimSegundoSemestre;

    @Transient
    private List<LocalDate> diasLetivosPrimeiroSemestre;

    @Transient
    private List<LocalDate> diasLetivosSegundoSemestre;

    public int getSemestreVigente() {
        LocalDate hoje = LocalDate.now();
        if (hoje.isBefore(inicioPrimeiroSemestre) || hoje.isAfter(fimSegundoSemestre)) {
            return PRIMEIRO_SEMESTRE;
        }
        if (!hoje.isAfter(fimPrimeiroSemestre)) {
            return PRIMEIRO_SEMESTRE;
        }
        return SEGUNDO_SEMESTRE;
    }

}