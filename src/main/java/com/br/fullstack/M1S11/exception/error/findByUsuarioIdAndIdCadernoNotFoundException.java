package com.br.fullstack.M1S11.exception.error;

import com.br.fullstack.M1S11.exception.NotFoundException;

//NotFoundException para Cadernos
public class findByUsuarioIdAndIdCadernoNotFoundException extends NotFoundException {
    public findByUsuarioIdAndIdCadernoNotFoundException(Long id) {
        super("Caderno não encontrado com id " + id + " neste usuário. Verifique seu Token!");
    }
}
