package com.br.fullstack.M1S11.datasource.repository;

import com.br.fullstack.M1S11.datasource.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByNomeUsuario(String nomeUsuario); //query que busca os usuarios pelo nome de usuario
}
