package com.br.fullstack.M1S11.controller;

import com.br.fullstack.M1S11.controller.dto.request.InserirNotaRequest;
import com.br.fullstack.M1S11.controller.dto.response.NotaResponse;
import com.br.fullstack.M1S11.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("nota")
public class NotaController {

    private final NotaService notaService;

    @GetMapping
    public ResponseEntity<List<NotaResponse>> retornarNotas(
            @RequestHeader(name = "Authorization") String token
    ){
        String tokenReal = token.split(" ")[1];
        return ResponseEntity.ok(notaService.retornarNotas(token.substring(7)));
    }

    @GetMapping("{id}")
    public ResponseEntity<NotaResponse> buscarNotaPorId(@PathVariable Long id,
                                                        @RequestHeader(name = "Authorization") String token
                                                        ){
        String tokenReal = token.split(" ")[1];
        return ResponseEntity.ok(notaService.buscarNotaPorId(id, token.substring(7)));
    }

    @PostMapping
    public ResponseEntity<NotaResponse> salvarNotas(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody InserirNotaRequest incluirNotaRequest
    ){
        return ResponseEntity.ok(notaService.salvarNota(incluirNotaRequest,token.substring(7)));
    }

    @PutMapping("{id}")
    public ResponseEntity<NotaResponse> atualizarNota(@RequestHeader(name = "Authorization") String token,
                                                     @PathVariable Long id,
                                                     @RequestBody InserirNotaRequest incluirNotaRequest) {
        return ResponseEntity.ok(notaService.atualizarNota(id, incluirNotaRequest, token.substring(7)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirNota(@PathVariable Long id,
                                            @RequestHeader(name = "Authorization") String token
                                       ){
        notaService.excluirNota(id, token.substring(7));
        return ResponseEntity.noContent().build();
    }
}
