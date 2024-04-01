package com.br.fullstack.M1S11.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Table(name = "nota")
@Entity
@Data
public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caderno_id", nullable = false)
    private CadernoEntity caderno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    private String titulo;
    private String conteudo;

    @CreationTimestamp // indica o momento de criação
    private Instant creationTimestamp;
}
