package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pessoa", nullable = false, unique = true)
    Pessoa pessoa;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "cod_discente", nullable = false, unique = true, length = 30)
    private String codDiscente;

    public String getCincoUltimosCaracteresCodDiscente(){
        if (codDiscente.length() >= 5) {
           return codDiscente.substring(codDiscente.length() - 5);
        }
        return null;
    }

}