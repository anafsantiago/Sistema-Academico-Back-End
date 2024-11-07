package org.campusconnect.estudafacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaIndividualDiscenteDTO {
    private long id;
    private List<NotaDTO> notas;
    private int faltas;
    private float resultadoFinal;
    private float porcentagemFrequencia;

    public FichaIndividualDiscenteDTO(long id, List<NotaDTO> notasDTO, int faltas, float resultadoFinal) {
    }
}