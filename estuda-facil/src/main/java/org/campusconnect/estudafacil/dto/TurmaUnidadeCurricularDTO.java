package org.campusconnect.estudafacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaUnidadeCurricularDTO {
    private long id;
    private String codigoTurma;
    private UnidadeCurricularDTO unidadeCurricular;
}