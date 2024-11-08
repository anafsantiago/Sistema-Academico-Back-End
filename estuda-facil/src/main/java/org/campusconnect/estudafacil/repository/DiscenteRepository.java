package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscenteRepository extends JpaRepository<Discente, Long> {

    Optional<Discente> findByPessoaCpf(String cpf);

}