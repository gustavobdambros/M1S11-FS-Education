package com.br.fullstack.M1S11.exception.error;

import com.br.fullstack.M1S11.exception.NotFoundException;

//NotFoundException para Get Cadernos por ID
public class findByUsuarioIdCadernoNotFoundException extends NotFoundException {
    public findByUsuarioIdCadernoNotFoundException(Long id) {
        super("Usuário não válido/encontrado para a criação de caderno. Verifique seu Token!");
    }
}
