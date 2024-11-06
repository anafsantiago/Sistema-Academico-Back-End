package org.campusconnect.estudafacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlocacaoDiscenteDTO {
    private long id;
    private DiscenteDTO discente;
    private TurmaUnidadeCurricularDTO turmaUnidadeCurricular;
    private SituacaoAlocacaoDiscenteDTO situacaoAlocacaoDiscente;
    private FichaIndividualDiscenteDTO fichaIndividualAlocacaoDiscente;
}