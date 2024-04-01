package com.br.fullstack.M1S11.controller;

import com.br.fullstack.M1S11.controller.dto.request.InserirCadernoRequest;
import com.br.fullstack.M1S11.controller.dto.response.CadernoResponse;
import com.br.fullstack.M1S11.datasource.entity.CadernoEntity;
import com.br.fullstack.M1S11.service.CadernoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("caderno")
public class CadernoController {

    private final CadernoService cadernoService;

    @GetMapping
    public ResponseEntity<List<CadernoResponse>> retornarCadernos(
            @RequestHeader(name = "Authorization") String token
    ){
        String tokenReal = token.split(" ")[1];
        return ResponseEntity.ok(cadernoService.retornarCadernos(token.substring(7)));
    }

    @GetMapping("{id}")
    public ResponseEntity<CadernoResponse> buscarCadernoPorId(@PathVariable Long id,
                                                        @RequestHeader(name = "Authorization") String token
    ){
        String tokenReal = token.split(" ")[1];
        return ResponseEntity.ok(cadernoService.buscarCadernoPorId(id, token.substring(7)));
    }

    @PostMapping
    public ResponseEntity<CadernoResponse> salvarCadernos(
            @RequestBody InserirCadernoRequest incluirCadernoRequest,
            @RequestHeader(name = "Authorization") String token

    ){
        return ResponseEntity.ok(cadernoService.salvarCaderno(incluirCadernoRequest,token.substring(7)));
    }

    @PutMapping("{id}")
    public ResponseEntity<CadernoResponse> atualizarCaderno(@PathVariable Long id,
                                                            @RequestBody InserirCadernoRequest incluirCadernoRequest,
                                                            @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(cadernoService.atualizarCaderno(id, incluirCadernoRequest, token.substring(7)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirCaderno(@PathVariable Long id,
                                               @RequestHeader(name = "Authorization") String token
                                       ){
        cadernoService.excluirCaderno(id, token.substring(7));
        return ResponseEntity.noContent().build();
    }
}