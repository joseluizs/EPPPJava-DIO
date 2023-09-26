package com.br.dasafiodio.service;

import java.io.IOException;
import java.util.List;

public interface AbrirXMLService {

    String abrirArquivoXML(String caminhoArquivo) throws IOException;

    // Adicione métodos para pesquisa, salvamento e busca de resultados
    void salvarResultado(String conteudoResultados);

    void salvarConteudoPesquisado(String conteudo);

    List<String> recuperarResultadosDoBancoDeDados();

    String buscarResultado(Long id);
}

