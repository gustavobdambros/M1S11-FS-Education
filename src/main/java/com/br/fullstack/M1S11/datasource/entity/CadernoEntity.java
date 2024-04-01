package com.br.fullstack.M1S11.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Table(name = "caderno")
@Entity
@Data
public class CadernoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    private String nome;

    @CreationTimestamp // indica o momento de criação
    private Instant creationTimestamp;
}
