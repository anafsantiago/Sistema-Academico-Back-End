package org.campusconnect.estudafacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlocacaoDocenteDTO {
    private long id;
    private DocenteDTO docente;
    private TurmaUnidadeCurricularDTO turmaUnidadeCurricular;
}