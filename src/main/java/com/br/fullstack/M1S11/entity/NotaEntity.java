package com.br.fullstack.M1S11.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "notas")
@Entity
@Data
public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caderno_id", nullable = false)
    private NotaEntity notaEntity;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuarioEntity;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

}
