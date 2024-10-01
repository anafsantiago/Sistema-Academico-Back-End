package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 11)
    private String contato;

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    public String getDataNascimentoFormatada() {
        if (dataNascimento != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            return dataNascimento.format(formatter);
        }
        return null;
    }

    public String getDiaMesDataNascimento() {
        if (dataNascimento != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMM");
            return dataNascimento.format(formatter);
        }
        return null;
    }

    public String getAnoDataCadastro() {
        if (dataCadastro != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            return dataCadastro.format(formatter);
        }
        return null;
    }

    public String getTresPrimeirosDigitosCpf() {
        if (cpf != null)
            return cpf.substring(0, 3);
        return null;
    }

    public String getCincoUltimosDigitosCpf() {
        if (cpf != null && cpf.length() >= 5) {
            return cpf.substring(cpf.length() - 5);
        }
        return null;
    }

}