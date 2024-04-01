package com.br.fullstack.M1S11.datasource.entity;

import com.br.fullstack.M1S11.controller.dto.request.LoginRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(unique = true)
    private String nomeUsuario;
    private String senha;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_papeis",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<PerfilEntity> perfis;

    public boolean senhaValida(LoginRequest loginRequest, BCryptPasswordEncoder bCryptEncoder) {
        return bCryptEncoder.matches(
                loginRequest.senha(),
                this.senha
        );
    }
}
