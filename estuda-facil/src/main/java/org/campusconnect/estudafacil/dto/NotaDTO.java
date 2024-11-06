package org.campusconnect.estudafacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaDTO {
    private long id;
    private float valor;
    private boolean reposicao;
}