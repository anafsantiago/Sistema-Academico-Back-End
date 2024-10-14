package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Papel implements GrantedAuthority {

    public static final String PAPEL_ADMIN = "ADMIN";
    public static final String PAPEL_DISCENTE = "DISCENTE";
    public static final String PAPEL_DOCENTE = "DOCENTE";
    public static final String PAPEL_GESTOR = "GESTOR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 10)
    private String descricao;

    @Override
    public String getAuthority() {
        return descricao;
    }

}