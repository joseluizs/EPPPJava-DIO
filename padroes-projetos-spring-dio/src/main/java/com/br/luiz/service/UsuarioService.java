package com.br.luiz.service;

import com.br.luiz.model.Usuario;

public interface UsuarioService {

    Iterable<Usuario> buscarTodos();

    Usuario buscarPorId(Long id);

    void inserirUsuario(Usuario usuario);

    void atualizarUsuario(Long id, Usuario usuario);

    void deletarUsuario(Long id);

    boolean autenticarUsuario(String email, String senha); // Adicionado método de autenticação
}
