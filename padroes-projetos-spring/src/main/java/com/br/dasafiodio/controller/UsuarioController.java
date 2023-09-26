package com.br.dasafiodio.controller;

import com.br.dasafiodio.model.Usuario;
import com.br.dasafiodio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController // Anotação que indica que esta classe é um controlador Spring MVC
@RequestMapping("/usuarios") // Mapeia todos os endpoints deste controlador para '/usuarios'
public class UsuarioController {

    private final UsuarioService usuarioService; // Injeção de dependência do serviço UsuarioService

    @Autowired // Anotação que indica que a injeção de dependência deve ser feita por Spring
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService; // Inicializa o serviço UsuarioService
    }

    @GetMapping // Mapeia solicitações HTTP GET para a raiz '/usuarios'
    public String listarUsuarios(Model model) { // Método para listar todos os usuários
        Iterable<Usuario> usuarios = usuarioService.buscarTodos(); // Chama o serviço para buscar todos os usuários
        model.addAttribute("usuarios", usuarios); // Adiciona a lista de usuários ao modelo
        return "lista-usuarios"; // Retorna a página HTML 'lista-usuarios' para exibir a lista de usuários
    }

    @GetMapping("/{id}") // Mapeia solicitações HTTP GET com um ID específico, por exemplo, '/usuarios/1'
    public String verUsuario(@PathVariable Long id, Model model) { // Método para visualizar detalhes de um usuário
        Usuario usuario = usuarioService.buscarPorId(id); // Chama o serviço para buscar um usuário pelo ID
        model.addAttribute("usuario", usuario); // Adiciona o usuário ao modelo
        return "detalhes-usuario"; // Retorna a página HTML 'detalhes-usuario' para exibir os detalhes do usuário
    }

    @GetMapping("/novo") // Mapeia solicitações HTTP GET para '/usuarios/novo' para exibir o formulário de criação
    public String exibirFormularioCriacao(Model model) { // Método para exibir o formulário de criação de usuário
        model.addAttribute("usuario", new Usuario()); // Cria um novo objeto de usuário e o adiciona ao modelo
        return "formulario-usuario"; // Retorna a página HTML 'formulario-usuario' com o formulário de criação
    }

    @PostMapping("/novo") // Mapeia solicitações HTTP POST para '/usuarios/novo' para criar um novo usuário
    public String criarUsuario(@ModelAttribute Usuario usuario) { // Método para criar um novo usuário
        usuarioService.inserirUsuario(usuario); // Chama o serviço para inserir o usuário no banco de dados
        return "redirect:/usuarios"; // Redireciona de volta para a lista de usuários após a criação
    }

    @PostMapping("/novoComCep") // Mapeia solicitações HTTP POST para '/usuarios/novo' para criar um novo usuário
    public String criarUsuarioComCep(@ModelAttribute Usuario usuario) { // Método para criar um novo usuário
        usuarioService.inserirUsuario(usuario); // Chama o serviço para inserir o usuário no banco de dados
        return "redirect:/usuarios"; // Redireciona de volta para a lista de usuários após a criação
    }

    // Outros métodos para atualizar e excluir usuários podem ser adicionados aqui
}
