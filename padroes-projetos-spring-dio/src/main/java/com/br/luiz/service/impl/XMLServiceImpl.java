package com.br.luiz.service.impl;

import com.br.luiz.model.XML;
import com.br.luiz.repository.XMLRepository;
import com.br.luiz.service.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class XMLServiceImpl implements XMLService {

    private final XMLRepository xmlRepository;

    @Autowired
    public XMLServiceImpl(XMLRepository xmlRepository) {
        this.xmlRepository = xmlRepository;
    }

    @Override
    public String abrirArquivoXML(String caminhoArquivo) throws IOException {
        // Implemente a lógica para abrir o arquivo XML aqui
        try {
            File arquivo = new File(caminhoArquivo);

            // Verifique se o arquivo existe
            if (!arquivo.exists()) {
                throw new FileNotFoundException("Arquivo não encontrado: " + caminhoArquivo);
            }

            // Use um parser DOM para ler o arquivo XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(arquivo);
            doc.getDocumentElement().normalize();

            // Agora você pode percorrer todas as tags do XML e mostrar o conteúdo delas
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            StringBuilder conteudoXML = new StringBuilder();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                // Verifique se o nó é do tipo ELEMENT_NODE (uma tag)
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;
                    String tagName = elemento.getTagName();
                    String conteudo = elemento.getTextContent();

                    // Aqui, você pode fazer o que quiser com a tag e seu conteúdo
                    // Neste exemplo, estou apenas adicionando ao StringBuilder
                    conteudoXML.append("Tag: ").append(tagName).append("\n");
                    conteudoXML.append("Conteúdo: ").append(conteudo).append("\n");
                }
            }

            return conteudoXML.toString();
        } catch (Exception e) {
            throw new IOException("Erro ao abrir o arquivo XML", e);
        }
    }

    @Override
    public void salvarResultado(String conteudoResultado) {
        XML resultado = new XML(conteudoResultado);
        xmlRepository.save(resultado);
    }

    // Novo método para salvar conteúdo pesquisado
    @Override
    public void salvarConteudoPesquisado(String conteudo) {
        // Crie uma instância de AbrirXML com o conteúdo pesquisado
        XML abrirXML = new XML(conteudo);

        // Salve no banco de dados
        xmlRepository.save(abrirXML);
    }

    @Override
    public List<String> recuperarResultadosDoBancoDeDados() {
        // Implemente a lógica para recuperar os resultados do banco de dados usando o repositório
        Collection<XML> resultados = (Collection<XML>) xmlRepository.findAll();
        // Converta os resultados para uma lista de strings
        return resultados.stream()
                .map(XML::getConteudoXML)
                .collect(Collectors.toList());
    }


    @Override
    public String buscarResultado(Long id) {
        Optional<XML> resultadoOptional = xmlRepository.findById(id);
        return resultadoOptional.map(XML::getConteudoXML).orElse(null);
    }
}
