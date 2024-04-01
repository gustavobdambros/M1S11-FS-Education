package com.br.fullstack.M1S11.service;

import com.br.fullstack.M1S11.controller.dto.request.InserirNotaRequest;
import com.br.fullstack.M1S11.controller.dto.response.NotaResponse;
import com.br.fullstack.M1S11.datasource.entity.CadernoEntity;
import com.br.fullstack.M1S11.datasource.entity.NotaEntity;
import com.br.fullstack.M1S11.datasource.entity.UsuarioEntity;
import com.br.fullstack.M1S11.datasource.repository.CadernoRepository;
import com.br.fullstack.M1S11.datasource.repository.NotaRepository;
import com.br.fullstack.M1S11.datasource.repository.UsuarioRepository;
import com.br.fullstack.M1S11.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaService {
    private final NotaRepository notaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CadernoRepository cadernoRepository;
    private final JwtDecoder jwtDecoder;

    private Long extractUserId(String token) {
        return Long.valueOf(jwtDecoder.decode(token).getClaims().get("sub").toString());
    }

    public List<NotaResponse> retornarNotas(String token){

        Long idUsuario = extractUserId(token);

        List<NotaEntity> notaEntities = notaRepository.findAllByUsuarioId(idUsuario);
        List<NotaResponse> notaResponseList = new ArrayList<>();

        notaEntities.forEach( t-> notaResponseList.add(
                new NotaResponse(t.getId(), t.getTitulo(), t.getConteudo())
        ));

        return notaResponseList;

    }

    public NotaResponse salvarNota(InserirNotaRequest incluiNotaRequest, String token) {
        Long idUsuario = extractUserId(token);

        // Obtenha o usuário com base no ID do token
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        // Obtenha o caderno com o qual a nota está associada
        CadernoEntity caderno = cadernoRepository.findFirstByUsuarioIdOrderByCreationTimestampAsc(idUsuario)
                .orElseThrow(() -> new NotFoundException("Caderno não encontrado para o usuário"));

        NotaEntity notaEntity = new NotaEntity();
        notaEntity.setUsuario(usuario);
        notaEntity.setCaderno(caderno); // Defina o caderno associado à nota
        notaEntity.setTitulo(incluiNotaRequest.titulo());
        notaEntity.setConteudo(incluiNotaRequest.conteudo());

        NotaEntity notaSalva = notaRepository.save(notaEntity);
        return new NotaResponse(notaSalva.getId(), notaSalva.getTitulo(), notaSalva.getConteudo());
    }

    public NotaResponse buscarNotaPorId(Long id, String token) {
        Long idUsuario = extractUserId(token);

        NotaEntity notaEntity = notaRepository.findByUsuarioIdAndId(idUsuario, id)
                .orElseThrow(() -> new NotFoundException("Nota com o id " + id + " não encontrada"));

        return new NotaResponse(notaEntity.getId(), notaEntity.getTitulo(), notaEntity.getConteudo());
    }

    public NotaResponse atualizarNota(Long id, InserirNotaRequest incluiNotaRequest, String token) {
        Long idUsuario = extractUserId(token);

        NotaEntity notaExistente = notaRepository.findByUsuarioIdAndId(idUsuario, id)
                .orElseThrow(() -> new NotFoundException("Nota com o ID " + id + " não encontrada"));

        // Atualiza os campos da nota com base nos dados da requisição
        notaExistente.setTitulo(incluiNotaRequest.titulo());
        notaExistente.setConteudo(incluiNotaRequest.conteudo());

        // Salva a nota atualizada no banco de dados
        NotaEntity notaAtualizada = notaRepository.save(notaExistente);

        // Retorna uma instância de NotaResponse com os dados da nota atualizada
        return new NotaResponse(notaAtualizada.getId(), notaAtualizada.getTitulo(), notaAtualizada.getConteudo());
    }

    public void excluirNota(Long id, String token) {
        Long idUsuario = extractUserId(token);

        // Verificar se o nota pertence ao usuário antes de excluí-lo
        NotaEntity nota = notaRepository.findByUsuarioIdAndId(idUsuario, id)
                .orElseThrow(() -> new NotFoundException("Nota com o ID " + id + " não encontrado"));

        // Excluir o nota
        notaRepository.delete(nota);
    }
}

