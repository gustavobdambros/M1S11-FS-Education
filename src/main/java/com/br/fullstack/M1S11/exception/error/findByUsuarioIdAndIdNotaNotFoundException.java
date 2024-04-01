package com.br.fullstack.M1S11.exception.error;

import com.br.fullstack.M1S11.exception.NotFoundException;

//NotFoundException para Notas
public class findByUsuarioIdAndIdNotaNotFoundException extends NotFoundException {
    public findByUsuarioIdAndIdNotaNotFoundException(Long id) {
        super("Nota não encontrado com id " + id + " neste usuário. Verifique seu Token!");
    }
}
