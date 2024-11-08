package org.campusconnect.estudafacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.campusconnect.estudafacil.entity.Pessoa;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscenteDTO {
    private long id;
    private PessoaDTO pessoa;
    private String codDiscente;

    public DiscenteDTO(long id, Pessoa pessoa, String codDiscente) {
    }

    public DiscenteDTO(long id, String codDiscente) {
    }
}