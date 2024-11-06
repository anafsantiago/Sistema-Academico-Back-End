package org.campusconnect.estudafacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeCurricularDTO {
    private long id;
    private String nomeUnidadeCurricular;
    private String ementa;
}