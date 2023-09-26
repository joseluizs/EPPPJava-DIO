package com.br.luiz.controller;

import com.br.luiz.service.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/xml")
public class XMLController {

    private final XMLService xmlService;

    @Autowired
    public XMLController(XMLService xmlService) {
        this.xmlService = xmlService;
    }

    // Endpoint para abrir um arquivo XML e exibir seu conteúdo
    @PostMapping("/abrir")
    public ResponseEntity<String> abrirArquivoXML(@RequestParam("caminhoArquivo") String caminhoArquivo) {
        try {
            String conteudoXML = xmlService.abrirArquivoXML(caminhoArquivo);
            return new ResponseEntity<>(conteudoXML, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao abrir o arquivo XML: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para salvar o conteúdo pesquisado no banco de dados
    @PostMapping("/salvar")
    public ResponseEntity<Void> salvarConteudoPesquisado(@RequestBody String conteudo) {
        xmlService.salvarConteudoPesquisado(conteudo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Endpoint para recuperar todos os resultados do banco de dados
    @GetMapping("/resultados")
    public ResponseEntity<Iterable<String>> recuperarResultadosDoBancoDeDados() {
        Iterable<String> resultados = xmlService.recuperarResultadosDoBancoDeDados();
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }

    // Endpoint para buscar um resultado pelo ID
    @GetMapping("/resultados/{id}")
    public ResponseEntity<String> buscarResultadoPorId(@PathVariable Long id) {
        String resultado = xmlService.buscarResultado(id);
        if (resultado != null) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
