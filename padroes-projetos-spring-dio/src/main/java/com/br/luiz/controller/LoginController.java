package com.br.luiz.controller;

import com.br.luiz.model.Usuario;
import com.br.luiz.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UsuarioService usuarioService; // Injete o serviço de usuário

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "index"; // Renderize a página de login (index.html)
    }

    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha, Model model) {
        if (autenticarUsuario(email, senha)) {
            return "redirect:/dashboard"; // Autenticação bem-sucedida, redireciona para o dashboard
        } else {
            model.addAttribute("mensagemErro", "Credenciais inválidas");
            return "login";
        }
    }

    private boolean autenticarUsuario(String email, String senha) {
        // Implemente a lógica de autenticação no serviço de usuário
        return usuarioService.autenticarUsuario(email, senha);
    }
}
