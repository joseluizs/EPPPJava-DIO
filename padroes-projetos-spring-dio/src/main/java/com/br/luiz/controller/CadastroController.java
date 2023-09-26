package com.br.luiz.controller;

import com.br.luiz.model.Usuario;
import com.br.luiz.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CadastroController {

    private final UsuarioServiceImpl usuarioService;

    @Autowired
    public CadastroController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarNovoUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.inserirUsuario(usuario);
        // Normalmente, você validaria os dados antes de salvar
        // Após o cadastro bem-sucedido, redirecione para a página de login ou outra página de destino
        return "redirect:/login";
    }
}
