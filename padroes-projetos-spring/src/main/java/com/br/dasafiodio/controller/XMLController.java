package com.br.dasafiodio.controller;

import com.br.dasafiodio.model.AbrirXML;
import com.br.dasafiodio.service.AbrirXMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
public class XMLController {

    private final AbrirXMLService abrirXMLService;

    @Autowired
    public XMLController(AbrirXMLService abrirXMLService) {
        this.abrirXMLService = abrirXMLService;
    }
    @GetMapping("/upload-xml")
    public String showUploadForm() {
        return "upload-xml"; // Retorna a página HTML para exibir o formulário de upload
    }

    @PostMapping("/upload-xml")
    public String uploadXMLFile(@RequestParam("file") MultipartFile file,
                                @RequestParam("tags") String tags,
                                Model model) {
        try {
            // Salvar o arquivo XML no servidor ou processá-lo diretamente
            // Certifique-se de validar o arquivo e processá-lo adequadamente

            // Processar o arquivo XML com base nas tags fornecidas
            String[] tagArray = tags.split(",");
            List<String> resultados = processarArquivoXML(file, tagArray);

            // Salvar os resultados no banco de dados usando o serviço AbrirXMLService
            abrirXMLService.salvarResultado(resultados.toString());

            model.addAttribute("resultados", resultados);
            return "show-results"; // Retorna a página HTML para exibir os resultados
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao processar o arquivo XML.");
            return "error-page"; // Retorna a página HTML para exibir erros
        }
    }


    @PostMapping("/salvar-resultado")
    public String salvarResultado(@RequestParam("conteudoResultado") String conteudoResultado,
                                  Model model) {
        try {
            // Use o serviço para salvar o conteúdo no banco de dados
            abrirXMLService.salvarResultado(conteudoResultado);

            // Redirecionar para uma página de sucesso ou faça qualquer outra coisa que você precise aqui
            return "sucesso";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao salvar resultado.");
            return "error-page";
        }
    }

    @GetMapping("/show-results")
    public String showResults(Model model) {
        // Recuperar os resultados salvos no banco de dados usando a classe AbrirXML
        AbrirXML abrirXML = new AbrirXML();
        List<String> resultados = abrirXMLService.recuperarResultadosDoBancoDeDados();

        model.addAttribute("resultados", resultados);
        return "show-results"; // Retorna a página HTML para exibir os resultados
    }

    private List<String> processarArquivoXML(MultipartFile file, String[] tags) {
        // Implemente a lógica para processar o arquivo XML com base nas tags fornecidas
        // Retorne os resultados como uma lista de strings
        // Certifique-se de manipular exceções adequadamente
        return null;
    }
}
