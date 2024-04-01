package com.br.fullstack.M1S11.datasource.repository;

import com.br.fullstack.M1S11.datasource.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    List<NotaEntity> findAllByUsuarioId(Long idUsuario);

    Optional<NotaEntity> findByUsuarioIdAndId(Long usuarioId, Long id);
}