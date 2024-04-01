package com.br.fullstack.M1S11.exception.error;

import com.br.fullstack.M1S11.exception.NotFoundException;

//NotFoundException para Get Notas por ID
public class findByUsuarioIdNotaNotFoundException extends NotFoundException {
    public findByUsuarioIdNotaNotFoundException(Long id) {
        super("Usuário não válido/encontrado para a criação de notas. Verifique seu Token!");
    }
}
