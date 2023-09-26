package com.br.luiz.service;

import java.io.IOException;
import java.util.List;

public interface XMLService {

    String abrirArquivoXML(String caminhoArquivo) throws IOException;

    void salvarResultado(String conteudoResultados);

    void salvarConteudoPesquisado(String conteudo);

    List<String> recuperarResultadosDoBancoDeDados();

    String buscarResultado(Long id);
}
