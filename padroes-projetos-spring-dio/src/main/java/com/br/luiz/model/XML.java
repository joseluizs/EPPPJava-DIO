package com.br.luiz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class XML {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conteudoXML;

    public XML() { }

    public XML(String conteudoXML) {
        this.conteudoXML = conteudoXML;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudoXML() {
        return conteudoXML;
    }

    public void setConteudoXML(String conteudoXML) {
        this.conteudoXML = conteudoXML;
    }


}
