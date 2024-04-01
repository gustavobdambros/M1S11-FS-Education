package com.br.fullstack.M1S11.datasource.repository;

import com.br.fullstack.M1S11.datasource.entity.CadernoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CadernoRepository extends JpaRepository<CadernoEntity, Long> {
    List<CadernoEntity> findAllByUsuarioId(Long idUsuario);

    Optional<CadernoEntity> findByUsuarioIdAndId(Long usuarioId, Long id);

    Optional<CadernoEntity> findFirstByUsuarioIdOrderByCreationTimestampAsc(Long usuarioId);
}