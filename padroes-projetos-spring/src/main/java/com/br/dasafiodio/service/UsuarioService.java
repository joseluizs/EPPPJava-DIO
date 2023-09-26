package com.br.dasafiodio.service;

import com.br.dasafiodio.model.Usuario;

public interface UsuarioService {

    Iterable<Usuario> buscarTodos();

    Usuario buscarPorId(Long id);

    void inserirUsuario(Usuario usuario);
    void atualizarUsuario(Long id, Usuario usuario);
    void deletarUsuario(Long id);
}
