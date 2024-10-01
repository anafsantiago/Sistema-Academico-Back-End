package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {

    Optional<Set<Papel>> findByDescricaoIn(List<String> descricoes);

}