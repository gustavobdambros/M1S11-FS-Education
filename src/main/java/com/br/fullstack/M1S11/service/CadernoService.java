package com.br.fullstack.M1S11.service;

import com.br.fullstack.M1S11.controller.dto.request.InserirCadernoRequest;
import com.br.fullstack.M1S11.controller.dto.response.CadernoResponse;
import com.br.fullstack.M1S11.datasource.entity.CadernoEntity;
import com.br.fullstack.M1S11.datasource.entity.UsuarioEntity;
import com.br.fullstack.M1S11.datasource.repository.CadernoRepository;
import com.br.fullstack.M1S11.datasource.repository.UsuarioRepository;
import com.br.fullstack.M1S11.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CadernoService {
    private final CadernoRepository cadernoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtDecoder jwtDecoder;

    private Long extractUserId(String token) {
        return Long.valueOf(jwtDecoder.decode(token).getClaims().get("sub").toString());
    }

    public List<CadernoResponse> retornarCadernos(String token){

        Long idUsuario = extractUserId(token);

        List<CadernoEntity> cadernoEntities = cadernoRepository.findAllByUsuarioId(idUsuario);
        List<CadernoResponse> cadernoResponseList = new ArrayList<>();

        cadernoEntities.forEach( t-> cadernoResponseList.add(
                new CadernoResponse(t.getId(), t.getNome())
        ));

        return cadernoResponseList;

    }

    public CadernoResponse salvarCaderno(InserirCadernoRequest incluiCadernoRequest, String token) {
        Long idUsuario = extractUserId(token);

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).orElseThrow();

        CadernoEntity cadernoEntity = new CadernoEntity();
        cadernoEntity.setUsuario(usuario);
        cadernoEntity.setNome(incluiCadernoRequest.nome());

        CadernoEntity cadernoSalva = cadernoRepository.save(cadernoEntity);
        return new
                CadernoResponse(cadernoSalva.getId(), cadernoSalva.getNome());

    }

    public CadernoResponse buscarCadernoPorId(Long id, String token) {
        Long idUsuario = extractUserId(token);

        CadernoEntity cadernoEntity = cadernoRepository.findByUsuarioIdAndId(idUsuario, id)
                .orElseThrow(() -> new NotFoundException("Caderno com o id " + id + " não encontrada"));

        return new CadernoResponse(cadernoEntity.getId(), cadernoEntity.getNome());
    }

    public CadernoResponse atualizarCaderno(Long id, InserirCadernoRequest incluiCadernoRequest, String token) {
        Long idUsuario = extractUserId(token);

        CadernoEntity cadernoExistente = cadernoRepository.findByUsuarioIdAndId(idUsuario, id)
                .orElseThrow(() -> new NotFoundException("Caderno com o ID " + id + " não encontrada"));

        // Atualiza os campos da caderno com base nos dados da requisição
        cadernoExistente.setNome(incluiCadernoRequest.nome());

        // Salva a caderno atualizada no banco de dados
        CadernoEntity cadernoAtualizada = cadernoRepository.save(cadernoExistente);

        // Retorna uma instância de CadernoResponse com os dados da caderno atualizada
        return new CadernoResponse(cadernoAtualizada.getId(), cadernoAtualizada.getNome());
    }

    public void excluirCaderno(Long id, String token) {
        Long idUsuario = extractUserId(token);

        // Verificar se o caderno pertence ao usuário antes de excluí-lo
        CadernoEntity caderno = cadernoRepository.findByUsuarioIdAndId(idUsuario, id)
                .orElseThrow(() -> new NotFoundException("Caderno com o ID " + id + " não encontrado"));

        // Excluir o caderno
        cadernoRepository.delete(caderno);
    }
}

