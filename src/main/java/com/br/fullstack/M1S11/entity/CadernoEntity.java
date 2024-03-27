package com.br.fullstack.M1S11.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "cadernos")
@Entity
@Data
public class CadernoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuarioEntity;

    @Column(nullable = false)
    private String nome;
}
