package org.campusconnect.estudafacil.repository;

import org.campusconnect.estudafacil.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String usuario);

    @Query("SELECT u FROM Usuario u JOIN u.pessoa p JOIN u.papeis pp WHERE u.usuario = :usuario")
    Optional<Usuario> findByUsuarioWithPessoaAndPapel(@Param("usuario") String usuario);

}