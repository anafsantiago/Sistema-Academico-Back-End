package org.campusconnect.estudafacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaIndividualDiscenteDTO {
    private long id;
    private int faltas;
    private float notaUnidade1;
    private float notaUnidade2;
    private float notaUnidade3;
    private float notaReposicao;
    private float resultadoFinal;
    private float porcentagemFrequencia;
}