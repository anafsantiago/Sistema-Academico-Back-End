package org.campusconnect.estudafacil.entity;

import org.campusconnect.estudafacil.util.StringUtil;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome_curso", nullable = false, length = 25)
    private String nomeCurso;

    @Column(name = "sigla_curso", nullable = false, length = 9)
    private String siglaCurso;

    @Column(name = "carga_horaria_total", nullable = false)
    private int cargaHorariaTotal;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grade_curricular", nullable = false, unique = true)
    private GradeCurricular gradeCurricular;

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
        this.siglaCurso = StringUtil.gerarSigla(nomeCurso);
    }

}