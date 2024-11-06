package org.campusconnect.estudafacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaCursoDTO {
    private long id;
    private CursoDTO curso;
    private int periodo;
}