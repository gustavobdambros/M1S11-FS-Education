package com.br.fullstack.M1S11.datasource.repository;

import com.br.fullstack.M1S11.datasource.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
    Optional<PerfilEntity> findByNomePerfil(String nomePerfil); //query que busca os usuarios pelo nome de usuario
}
