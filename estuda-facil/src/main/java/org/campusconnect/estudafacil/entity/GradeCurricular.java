package org.campusconnect.estudafacil.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grade_curricular")
public class GradeCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "grade_unidade", joinColumns = @JoinColumn(name = "id_grade"), inverseJoinColumns = @JoinColumn(name = "id_unidade_curricular"))
    private List<UnidadeCurricular> unidadesCurriculares;

}