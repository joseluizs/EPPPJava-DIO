package com.br.dasafiodio.service.Impl;

import com.br.dasafiodio.model.Endereco;
import com.br.dasafiodio.model.Usuario;
import com.br.dasafiodio.repository.EnderecoRepository;
import com.br.dasafiodio.repository.UsuarioRepository;
import com.br.dasafiodio.service.UsuarioService;
import com.br.dasafiodio.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Iterable<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.orElse(null);
    }

    @Override
    public void inserirUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void atualizarUsuario(Long id, Usuario usuario) {
        // Implemente a lógica para atualizar um usuário com o ID especificado
        // Isso pode envolver encontrar o usuário pelo ID, atualizar seus campos e salvar novamente no repositório
    }

    @Override
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Outros métodos e lógica de negócios podem ser adicionados aqui
    private void salvarUsuarioComCep(Usuario usuario){
        String cep = usuario.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com ViaCep e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });

        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);
    }
}
