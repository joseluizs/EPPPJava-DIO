package com.br.luiz.service.impl;

import com.br.luiz.model.Endereco;
import com.br.luiz.model.Usuario;
import com.br.luiz.repository.EnderecoRepository;
import com.br.luiz.repository.UsuarioRepository;
import com.br.luiz.service.UsuarioService;
import com.br.luiz.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Iterable<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    @Override
    public void inserirUsuario(Usuario usuario) {
        salvarUsuarioComCep(usuario);
    }

    @Override
    public void atualizarUsuario(Long id, Usuario usuario) {
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
        if (usuarioSalvo.isPresent()) {
            salvarUsuarioComCep(usuario);
        }
    }

    @Override
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean autenticarUsuario(String email, String senha) {
        // Encontre o usuário pelo email no banco de dados
        Usuario usuario = usuarioRepository.findByEmail(email);

        // Verifique se o usuário existe e a senha está correta
        return usuario != null && passwordEncoder.matches(senha, usuario.getSenha());
    }

    private void salvarUsuarioComCep(Usuario usuario) {
        if (usuario != null && usuario.getEndereco() != null) {
            String cep = usuario.getEndereco().getCep();
            if (cep != null) {
                Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
                    Endereco novoEndereco = viaCepService.consultarCep(cep);
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });
                usuario.setEndereco(endereco);
                usuarioRepository.save(usuario);
            } else {
                throw new IllegalArgumentException("O CEP não pode ser nulo.");
            }
        } else {
            throw new IllegalArgumentException("O usuário ou o endereço não podem ser nulos.");
        }
    }
    @PostMapping("/cadastro")
    public String cadastrarNovoUsuario(@ModelAttribute Usuario usuario) {
        if (usuario != null) {
            salvarUsuarioComCep(usuario);
            // Após o cadastro bem-sucedido, redirecione para a página de login ou outra página de destino
            return "redirect:/login";
        } else {
            // Trate o caso em que o usuário seja nulo
            // Pode ser necessário redirecionar para uma página de erro
            return "erro"; // Substitua 'erro' pelo nome da sua página de erro
        }
    }

}
